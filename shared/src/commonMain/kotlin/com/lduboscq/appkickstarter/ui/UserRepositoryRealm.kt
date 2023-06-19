package com.lduboscq.appkickstarter

import com.lduboscq.appkickstarter.ui.UserRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.types.RealmUUID

abstract class UserRepositoryRealm : UserRepository {
    lateinit var realm: Realm

    abstract suspend fun setupRealmSync()

    suspend fun convertToUserData(user: User?): UserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }

        var userData: UserData? = null
        realm.write {
            if (user != null) {
                userData = UserData(
                    id = findLatest(user)!!._id,
                    name = findLatest(user)!!.name,
                    age = findLatest(user)!!.age,
                    species = findLatest(user)!!.species,
                    owner = findLatest(user)!!.owner,
                    user = user
                )
            }
        }
        return userData
    }

    private fun closeRealmSync() {
        realm.close()
    }

    override suspend fun addUser(userData: UserData): UserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }

        var user2: User? = null
        realm.write {
            // create a user object in the realm
            user2 = this.copyToRealm(User().apply {
                _id = userData.id ?: RealmUUID.random().toString()
                name = userData.name
                age = userData.age
                species = userData.species
                owner = userData.owner
            })
        }
        return convertToUserData(user2)
    }


    override suspend fun getUser(UserName: String): UserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }

        // Search equality on the primary key field name
        val user: User? = realm.query<User>(User::class, "name = \"$UserName\"").first().find()
        return convertToUserData(user)
    }

    override suspend fun updateUser(UserName: String,age : Int): UserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        var user2: UserData? = null
        try {
            // Search equality on the primary key field name
            val user: User? =
                realm.query<User>(User::class, "name = \"$UserName\"").first().find()

            // delete one object synchronously
            realm.write {
                if (user != null) {
                    findLatest(user)!!.age = age
                }
            }
            user2 = convertToUserData(user)
        } catch (e: Exception) {
            println(e.message)
        }

        return user2
    }

    override suspend fun deleteUser(userName: String): UserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        var user2: UserData? = null
        try {
            // Search for first match on the name field
            val user: User? =
                realm.query<User>(User::class, "name = \"$userName\"").first().find()

            realm.write {
                if (user != null) {

                    user2 = UserData(
                        id = findLatest(user)!!._id,
                        name = findLatest(user)!!.name,
                        age = findLatest(user)!!.age,
                        species = findLatest(user)!!.species,
                        owner = findLatest(user)!!.owner,
                        user = null)
                    findLatest(user)
                        ?.also { delete(it) }
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }

        return user2
    }
}