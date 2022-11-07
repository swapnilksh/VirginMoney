package com.task.virginmoney.presentation.contact.state

import com.task.virginmoney.presentation.contact.model.ContactItem

data class ContactScreenState (
    val isLoading: Boolean = false,
    val contactItem: ContactItem? = null,
    val error: String = ""
)


/*
data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)*/
