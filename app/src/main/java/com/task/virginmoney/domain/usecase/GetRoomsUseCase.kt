package com.task.virginmoney.domain.usecase
import Repository
import com.task.virginmoney.presentation.room.model.RoomItem


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetRoomsUseCase(
    private val repository: Repository
)  {
    operator fun invoke(): Flow<UseCaseResponse<List<RoomItem>>> = flow {
        try {
            val response = repository.getRooms()
            emit(UseCaseResponse(response,null))
        } catch(e: HttpException) {
            UseCaseResponse(null,e)
        } catch(e: IOException) {
            UseCaseResponse(null,e)
        }
    }
}
