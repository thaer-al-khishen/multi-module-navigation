package com.thaer.core.data.repositories

import android.accounts.NetworkErrorException
import android.view.LayoutInflater
import android.view.ViewGroup
import arrow.core.Either
import arrow.core.right
import arrow.core.rightIfNotNull
import com.thaer.core.data.models.Hero
import com.thaer.core.domain.repository.IHomeRepository
import com.thaer.core.network.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val apiService: ApiService): IHomeRepository {

    override suspend fun getAllMarvelHeroes(): List<Hero> {
        return apiService.getAllMarvelHeroes()
    }

    override suspend fun getAllMarvelHeroesAsEither(): GenericReturnType<List<Hero>> = wrapWithEither {
        apiService.getAllMarvelHeroes()
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
