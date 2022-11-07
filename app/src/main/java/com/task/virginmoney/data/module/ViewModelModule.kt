package com.task.virginmoney.data.module

import com.task.virginmoney.presentation.contact.viewmodel.ContactDetailsViewModel
import com.task.virginmoney.presentation.contact.viewmodel.ContactListViewModel
import com.task.virginmoney.presentation.room.viewmodel.RoomListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ContactListViewModel(get()) }
    viewModel { RoomListViewModel(get()) }
    viewModel { ContactDetailsViewModel(get()) }
}