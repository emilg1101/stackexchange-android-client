package com.github.emilg1101.stackexchangeapp.questions.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.emilg1101.stackexchangeapp.core.extensions.combine
import com.github.emilg1101.stackexchangeapp.core.extensions.setValueIfNew
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseViewModel
import com.github.emilg1101.stackexchangeapp.core.util.ListLiveData
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import com.github.emilg1101.stackexchangeapp.questions.model.QuestionItemModel
import com.github.emilg1101.stackexchangeapp.questions.model.TagItemModel
import com.github.emilg1101.stackexchangeapp.questions.model.TagItemModelsMapper
import com.github.emilg1101.stackexchangeapp.questions.paging.DataSourceStateCallback
import com.github.emilg1101.stackexchangeapp.questions.paging.QuestionsDataSourceFactory
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.Executors

class QuestionsViewModel internal constructor(
    private val questionsRepository: QuestionsRepository,
    private val tagsRepository: TagsRepository,
    private val questionsNavigation: QuestionsNavigation
) : BaseViewModel(), DataSourceStateCallback {

    private val _progress = MutableLiveData<Boolean>(false)
    val progress: LiveData<Boolean>
        get() = _progress

    private val _empty = MutableLiveData<Boolean>(false)
    val empty: LiveData<Boolean>
        get() = _empty

    private val tagsChannel = ConflatedBroadcastChannel(listOf<TagItemModel>())

    private val _sortLiveData = MutableLiveData<Sort>(HotSort)
    val sortLiveData: LiveData<Sort>
        get() = _sortLiveData

    private val _selectedTagsLiveData = ListLiveData<String>(listOf())
    val selectedTagsLiveData: LiveData<List<String>>
        get() = _selectedTagsLiveData

    val tagsLiveData
        get() = _selectedTagsLiveData.switchMap {
            tagsChannel.asFlow()
                .combine(flow { emit(it) }) { t1, t2 ->
                    t1.map { tag ->
                        tag.copy(selected = t2.contains(tag.title))
                    }
                }.asLiveData()
        }

    private val questionsDataSourceFactory
        get() = QuestionsDataSourceFactory(
            questionsRepository,
            viewModelScope,
            _sortLiveData.value ?: HotSort,
            _selectedTagsLiveData.value ?: listOf(),
            this
        )

    private var config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()

    private val livePagedListBuilder
        get() = LivePagedListBuilder(questionsDataSourceFactory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())

    private val _pagedListLiveData = MutableLiveData(livePagedListBuilder.build())
    var pagedListLiveData = _sortLiveData.combine(_selectedTagsLiveData).switchMap {
        _pagedListLiveData.value = livePagedListBuilder.build()
        _pagedListLiveData
    }

    init {
        tagsRepository.getPopularTags()
            .map(TagItemModelsMapper)
            .catch { _snackbar.value = it.message }
            .onEach { tagsChannel.offer(it) }
            .launchIn(viewModelScope)
    }

    fun changeSort(sort: Sort) {
        _sortLiveData.setValueIfNew(sort)
    }

    fun addTag(tag: String) {
        if (_selectedTagsLiveData.value?.contains(tag) == true) {
            _selectedTagsLiveData - tag
        } else {
            _selectedTagsLiveData + tag
        }
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
        questionsNavigation.openQuestionDetails(model.questionId)
    }
}
