package com.task.virginmoney.data.module


import com.task.virginmoney.domain.usecase.GetContactDetailsUseCase
import com.task.virginmoney.domain.usecase.GetContactsUseCase
import com.task.virginmoney.domain.usecase.GetRoomsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetContactsUseCase(get()) }
    factory { GetRoomsUseCase(get()) }
    factory { GetContactDetailsUseCase(get()) }
}