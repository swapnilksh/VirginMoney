
import com.task.virginmoney.presentation.contact.model.ContactItem
import com.task.virginmoney.presentation.room.model.RoomItem
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for defining api calls using retrofit
 */
interface VirginMoneyApi {
    @GET("people")
    suspend fun getPeopleList() : List<ContactItem>

    @GET("rooms")
    suspend fun getRooms() : List<RoomItem>

    @GET("people/{id}")
    suspend fun getPeopleList(@Path("id")id: String) : ContactItem
}