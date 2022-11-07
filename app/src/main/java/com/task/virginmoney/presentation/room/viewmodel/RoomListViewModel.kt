package com.task.virginmoney.presentation.room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.virginmoney.domain.usecase.GetRoomsUseCase
import com.task.virginmoney.presentation.room.model.RoomItem
import com.task.virginmoney.presentation.room.state.ResponseState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class RoomListViewModel(private val getRoomsUseCase: GetRoomsUseCase) : ViewModel() {

    private val _roomListLiveData by lazy { MutableLiveData<ResponseState<List<RoomItem>>>() }
    val roomListLiveData: LiveData<ResponseState<List<RoomItem>>>
        get() = _roomListLiveData

    init {
        _roomListLiveData.postValue(ResponseState.Loading)
        getRoomsList()
    }

    private fun getRoomsList() {
        getRoomsUseCase.invoke().onEach {
            it.data?.let {
                _roomListLiveData.postValue(ResponseState.Success(it))
            }
            it.error?.let {
                _roomListLiveData.postValue(ResponseState.Error(it))
            }
        }.launchIn(viewModelScope)
    }
}