package com.lduboscq.appkickstarter.ui

import com.lduboscq.appkickstarter.UserData

interface UserRepository {
    suspend fun getUser(UserName : String): UserData?
    suspend fun addUser(userData : UserData): UserData?
    suspend fun updateUser(UserName: String,age : Int): UserData?
    suspend fun deleteUser(userName : String): UserData?
}