package com.thaer.core.domain.usecase

import com.thaer.core.data.models.local.User
import com.thaer.core.data.models.remote.Hero

interface GetUsersUseCase {
    suspend operator fun invoke(): List<User>
}