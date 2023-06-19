package com.lduboscq.appkickstarter.ui

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.lduboscq.appkickstarter.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserScreenModel(private val repository: UserRepository)
    : StateScreenModel<UserScreenModel.State>(State.Init) {

    sealed class State {
        object Init : State()
        object Loading : State()
        sealed class Result: State() {
            class SingleResult(val userData: UserData?) : Result()
            class MultipleResult(val userDatas: Flow<UserData>?) : Result()
        }
    }

    fun getUser(userName : String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.SingleResult(repository.getUser(userName))
        }
    }


    fun addUser(userName: String, age: Int, species: String, owner: String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            val userData = UserData(
                name = userName,
                age = age,
                species = species,
                owner = owner,
                user = null
            )
            mutableState.value = State.Result.SingleResult(repository.addUser(userData))
        }
    }

    fun updateUser(userName : String,age: Int) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.SingleResult(repository.updateUser(userName, age))
        }
    }

    fun deleteUser(userName : String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.SingleResult(repository.deleteUser(userName))
        }
    }
}