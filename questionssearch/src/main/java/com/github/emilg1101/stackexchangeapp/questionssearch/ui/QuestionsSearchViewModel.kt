package com.github.emilg1101.stackexchangeapp.questionssearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseViewModel
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import com.github.emilg1101.stackexchangeapp.questionssearch.model.QuestionItemModel
import com.github.emilg1101.stackexchangeapp.questionssearch.model.TagItemModel
import com.github.emilg1101.stackexchangeapp.questionssearch.model.TagItemModelsMapper
import com.github.emilg1101.stackexchangeapp.questionssearch.paging.DataSourceStateCallback
import com.github.emilg1101.stackexchangeapp.questionssearch.paging.QuestionsDataSourceFactory
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.Executors

class QuestionsSearchViewModel internal constructor(
    questionsRepository: QuestionsRepository,
    tagsRepository: TagsRepository,
    private val questionsSearchNavigation: QuestionsSearchNavigation
) : BaseViewModel(), DataSourceStateCallback {

    private val tags: ArrayList<String> = arrayListOf()
    private var sort = HotSort

    private val _progress = MutableLiveData<Boolean>(false)

    val progress: LiveData<Boolean>
        get() = _progress

    private val _empty = MutableLiveData<Boolean>(false)

    val empty: LiveData<Boolean>
        get() = _empty

    private val questionsDataSourceFactory =
        QuestionsDataSourceFactory(questionsRepository, viewModelScope, sort, tags, this)

    private var config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private val livePagedListBuilder = LivePagedListBuilder(questionsDataSourceFactory, config)
        .setFetchExecutor(Executors.newSingleThreadExecutor())

    var pagedListLiveData = livePagedListBuilder.build()

    private val tagsChannel = ConflatedBroadcastChannel(listOf<TagItemModel>())

    private val _tagsFlow = tagsChannel.asFlow()
        .combine(flow { emit(tags) }) { t1, t2 ->
            t1.map { tag ->
                tag.copy(selected = t2.contains(tag.title))
            }
        }

    val tagsLiveData
        get() = _tagsFlow.asLiveData()

    init {
        tagsRepository.getPopularTags()
            .map(TagItemModelsMapper)
            .catch { _snackbar.value = it.message }
            .onEach { tagsChannel.offer(it) }
            .launchIn(viewModelScope)
    }

    fun changeSort(sort: Sort) {
        this.sort = sort
        pagedListLiveData = livePagedListBuilder.build()
    }

    fun addTag(tag: String) {
        if (tags.contains(tag)) {
            tags.remove(tag)
        } else {
            tags.add(tag)
        }
        pagedListLiveData = livePagedListBuilder.build()
    }

    override suspend fun onDataLoading() {
        _empty.value = false
        _progress.value = true
    }

    override suspend fun onDataLoaded() {
        _progress.value = false
    }

    override suspend fun onDataEmpty() {
        _empty.value = true
    }

    override suspend fun onError(t: Throwable) {
        _snackbar.value = t.message
    }

    fun openQuestion(model: QuestionItemModel) {
        questionsSearchNavigation.openQuestionDetails(model.questionId)
    }
}
