package com.lduboscq.appkickstarter

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey

class User: RealmObject {
    @PrimaryKey
    var _id: String = RealmUUID.random().toString()
    var name: String = ""
    var age: Int = 0
    var species: String = ""
    var owner: String = ""
}


data class UserData(
    var id: String? = null,
    var name: String = "",
    var age: Int = 0,
    var species: String = "",
    var owner: String = "",
    var user: User?)
