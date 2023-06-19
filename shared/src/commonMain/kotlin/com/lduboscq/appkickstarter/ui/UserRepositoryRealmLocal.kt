package com.lduboscq.appkickstarter

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class UserRepositoryRealmLocal() : UserRepositoryRealm() {

    override suspend fun setupRealmSync() {
        val config = RealmConfiguration.Builder(setOf(User::class))
//            .inMemory()
            .build()
        realm = Realm.open(config)
    }
}
