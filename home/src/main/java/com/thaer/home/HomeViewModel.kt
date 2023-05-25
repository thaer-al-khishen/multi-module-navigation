package com.thaer.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thaer.core.data.models.local.User
import com.thaer.core.data.models.remote.Hero
import com.thaer.core.domain.usecase.GetMarvelHeroesUseCase
import com.thaer.core.domain.usecase.GetUsersUseCase
import com.thaer.core.domain.usecase.either.GetMarvelHeroesEitherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class HomeViewModel @Inject constructor(
    @Named("HomeModule") private val getMarvelHeroesUseCase: GetMarvelHeroesUseCase,
    @Named("HomeModule") private val getMarvelHeroesEitherUseCase: GetMarvelHeroesEitherUseCase,
    @Named("HomeModule") private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _heroesListLiveData = MutableLiveData<List<Hero>?>()
    val heroesListLiveData = _heroesListLiveData as LiveData<List<Hero>?>

    private val _usersListLiveData = MutableLiveData<List<User>?>()
    val usersListLiveData = _usersListLiveData as LiveData<List<User>?>

    suspend fun getMarvelHeroes() {
        _heroesListLiveData.postValue(getMarvelHeroesUseCase())
    }

    suspend fun getUsers() {
        viewModelScope.launch {
            _usersListLiveData.postValue(getUsersUseCase())
        }
    }

//    suspend fun getUsers() = getUsersUseCase()

    fun getMarvelHeroesAsEither() {
        viewModelScope.launch {
            getMarvelHeroesEitherUseCase().mapLeft {
                Log.d("ThaerOutput", "$it")
            }.map {
                _heroesListLiveData.postValue(it)
            }
        }
    }

}
