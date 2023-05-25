package com.thaer.core.domain.usecase

import com.thaer.core.data.models.local.User
import com.thaer.core.domain.repository.IHomeRepository
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(private val repository: IHomeRepository): GetUsersUseCase {
    override suspend fun invoke(): List<User> = repository.getAllUsers()
}
