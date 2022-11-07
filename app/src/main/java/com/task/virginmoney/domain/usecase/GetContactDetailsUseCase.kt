package com.task.virginmoney.domain.usecase


import Repository
import com.task.virginmoney.presentation.contact.model.ContactItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetContactDetailsUseCase(
    private val repository: Repository
) {
    operator fun invoke(id: String): Flow<UseCaseResponse<ContactItem>> = flow {
        try {
            val response = repository.getPeopleDetails(id)
            emit(UseCaseResponse(response, null))
        } catch (e: HttpException) {
            UseCaseResponse(null, e)
        } catch (e: IOException) {
            UseCaseResponse(null, e)
        }
    }
}
