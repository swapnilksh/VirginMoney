package com.task.virginmoney.domain.repository

import VirginMoneyApi
import Repository
import com.task.virginmoney.presentation.contact.model.ContactItem
import com.task.virginmoney.presentation.room.model.RoomItem

class RepositoryImpl(
    private val virginMoneyApi: VirginMoneyApi,
) : Repository {
    override suspend fun getPeople(): List<ContactItem> {
        return virginMoneyApi.getPeopleList()
    }

    override suspend fun getRooms(): List<RoomItem> {
        return virginMoneyApi.getRooms()
    }

    override suspend fun getPeopleDetails(id: String): ContactItem {
        return virginMoneyApi.getPeopleList(id)
    }
}