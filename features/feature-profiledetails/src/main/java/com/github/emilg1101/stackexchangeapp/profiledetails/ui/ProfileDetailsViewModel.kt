package com.github.emilg1101.stackexchangeapp.profiledetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.github.emilg1101.stackexchangeapp.core.extensions.setValueIfNew
import com.github.emilg1101.stackexchangeapp.core.ui.base.BaseViewModel
import com.github.emilg1101.stackexchangeapp.domain.usecase.users.GetUserUseCase
import com.github.emilg1101.stackexchangeapp.profiledetails.model.ProfileDetailsModel
import com.github.emilg1101.stackexchangeapp.profiledetails.model.ProfileDetailsModelMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ProfileDetailsViewModel(private val getUserUseCase: GetUserUseCase) : BaseViewModel() {

    private val _progress = MutableLiveData<Boolean>(false)
    val progress: LiveData<Boolean>
        get() = _progress

    private val _userId = MutableLiveData<Int>()

    val userDetails: LiveData<ProfileDetailsModel> = _userId.switchMap { userId ->
        viewModelScope.async {
            getUserUseCase(GetUserUseCase.Params(userId))
                .map(ProfileDetailsModelMapper)
                .catch { throwable -> _snackbar.value = throwable.message }
                .asLiveData()
        }.getCompleted()
    }

    fun setUserId(userId: Int) {
        _userId.setValueIfNew(userId)
    }
}

class ProfileDetailsViewModelFactory internal constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ProfileDetailsViewModel(getUserUseCase) as T
}
