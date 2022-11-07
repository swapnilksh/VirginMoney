

import com.task.virginmoney.presentation.contact.model.ContactItem
import com.task.virginmoney.presentation.room.model.RoomItem

interface Repository {
    suspend fun getPeople(): List<ContactItem>
    suspend fun getRooms(): List<RoomItem>
    suspend fun getPeopleDetails(id: String): ContactItem
}