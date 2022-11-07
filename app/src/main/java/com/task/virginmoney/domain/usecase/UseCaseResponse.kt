package com.task.virginmoney.domain.usecase

class UseCaseResponse<T>(val data: T? = null, val error: Throwable? = null)