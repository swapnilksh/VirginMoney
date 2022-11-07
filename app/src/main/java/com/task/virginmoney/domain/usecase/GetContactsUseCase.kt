package com.task.virginmoney.domain.usecase
import Repository
import com.task.virginmoney.presentation.contact.model.ContactItem


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetContactsUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<UseCaseResponse<List<ContactItem>>> = flow {
        try {
            val response = repository.getPeople()
            emit(UseCaseResponse(response,null))
        } catch(e: HttpException) {
            UseCaseResponse(null,e)
        } catch(e: IOException) {
            UseCaseResponse(null,e)
        }
    }
}
