package com.lduboscq.appkickstarter

import io.realm.kotlin.Realm
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.exceptions.SyncException
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.mongodb.sync.SyncSession

class UserRepositoryRealmSync() : UserRepositoryRealm() {

    private val appServiceInstance by lazy {
        val configuration =
            AppConfiguration.Builder("application-0-mlnfl").log(LogLevel.ALL)
                .build()
        App.create(configuration)
    }

    override suspend fun setupRealmSync() {
        val user =
            appServiceInstance.login(Credentials.apiKey("CrEo4Y1hZK4657vufiWUXG2uigtHTFcbGDTb51eACLKn1lxrLepHDKCZoGcPakHR"))

        println("Got Here")
        val config = SyncConfiguration.Builder(user, setOf(User::class))
            .initialSubscriptions { realm ->
                add(
                    realm.query<User>(
                        User::class,
                        "_id == $0",
                        user.id
                    ),
                    name = "userSub",
                    updateExisting = true
                )
            }
            .errorHandler { session: SyncSession, error: SyncException ->
                println("*************\n" + error)
            }
            .build()
        realm = Realm.open(config)
    }

}