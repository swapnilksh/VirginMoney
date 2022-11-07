package com.task.virginmoney.presentation.contact.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.virginmoney.domain.usecase.GetContactDetailsUseCase
import com.task.virginmoney.presentation.contact.model.ContactItem
import com.task.virginmoney.presentation.room.state.ResponseState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ContactDetailsViewModel(
    private val getContactDetailsUseCase: GetContactDetailsUseCase
    ) : ViewModel() {

    private val _contactDetailsStateLiveData = MutableLiveData<ResponseState<ContactItem>>()
    val contactDetailsStateLiveData: LiveData<ResponseState<ContactItem>>
        get() = _contactDetailsStateLiveData

    init {
        _contactDetailsStateLiveData.postValue(ResponseState.Loading)
    }

    fun getContactDetails(id: String) {
        getContactDetailsUseCase.invoke(id).onEach {
            it.data?.let {
                _contactDetailsStateLiveData.postValue(ResponseState.Success(it))
            }
            it.error?.let {
                _contactDetailsStateLiveData.postValue(ResponseState.Error(it))
            }
        }.launchIn(viewModelScope)
    }
}