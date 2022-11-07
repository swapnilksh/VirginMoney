package com.task.virginmoney.presentation

import com.task.virginmoney.presentation.contact.model.ContactItem

sealed class ContactDetailState {
    abstract val classItems: ContactItem?
    abstract val error: Throwable?
    abstract val loading: Boolean
}

data class SuccessState(
    override val classItems: ContactItem?,
    override val error: Throwable?, override val loading: Boolean
) : ContactDetailState()

data class LoadingState(
    override val classItems: ContactItem?,
    override val error: Throwable?, override val loading: Boolean
) : ContactDetailState()

data class ErrorState(
    override val classItems: ContactItem?,
    override val error: Throwable?, override val loading: Boolean
) : ContactDetailState()

