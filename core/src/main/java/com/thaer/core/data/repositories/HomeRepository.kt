package com.thaer.core.data.repositories

import android.util.Log
import arrow.core.Either
import com.thaer.core.data.models.local.User
import com.thaer.core.data.models.remote.Hero
import com.thaer.core.data.room.UserDao
import com.thaer.core.domain.repository.IHomeRepository
import com.thaer.core.network.ApiService
import com.thaer.core.utils.launchFastScope
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : IHomeRepository {

    override suspend fun getAllMarvelHeroes(): List<Hero> {
        return apiService.getAllMarvelHeroes()
    }

    override suspend fun getAllUsers(): List<User> = userDao.getAllUsers()

    override suspend fun getAllMarvelHeroesAsEither(): GenericReturnType<List<Hero>> {
        return wrapWithEither {
            apiService.getAllMarvelHeroes()
        }
    }
}

typealias GenericReturnType<T> = Either<AppError, T>

private suspend inline fun <T> wrapWithEither(
    crossinline action: suspend () -> T
): Either<AppError, T> {
    return Either.catch { action() }
        .mapLeft { cause ->
            when (cause) {
                is IOException -> AppError.NetworkError(cause)
                is HttpException -> AppError.ApiError(cause.message())
                else -> AppError.UnknownError(cause)
            }
        }
}

sealed class AppError {
    data class NetworkError(val cause: Throwable) : AppError()
    data class ApiError(val message: String) : AppError()
    data class UnknownError(val cause: Throwable?) : AppError()
}
