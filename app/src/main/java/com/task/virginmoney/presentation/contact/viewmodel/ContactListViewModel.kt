package com.task.virginmoney.presentation.contact.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.virginmoney.domain.usecase.GetContactsUseCase
import com.task.virginmoney.presentation.contact.model.ContactItem
import com.task.virginmoney.presentation.room.state.ResponseState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ContactListViewModel(private val getContactsUseCase: GetContactsUseCase) : ViewModel() {

    private val _contactsLiveData by lazy {   MutableLiveData<ResponseState<List<ContactItem>>>() }
    val contactsLiveData: LiveData<ResponseState<List<ContactItem>>>
        get() = _contactsLiveData

    init {
        _contactsLiveData.postValue(ResponseState.Loading)
        getContactList()
    }

    private fun getContactList() {
        getContactsUseCase.invoke().onEach {
            it.data?.let {
                _contactsLiveData.postValue(ResponseState.Success(it.sortedBy {
                    it.firstName
                }))
            }
            it.error?.let {
                _contactsLiveData.postValue(ResponseState.Error(it))
            }
        }.launchIn(viewModelScope)
    }
}