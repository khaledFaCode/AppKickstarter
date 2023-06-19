package com.lduboscq.appkickstarter

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.lduboscq.appkickstarter.ui.UserScreenModel

class UserScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel() { UserScreenModel(UserRepositoryRealmLocal()) }
        val state by screenModel.state.collectAsState()
        var UserName by remember { mutableStateOf("") }

        Column {
            when (val result = state) {
                is UserScreenModel.State.Init -> Text("Just initialized")
                is UserScreenModel.State.Loading -> Text("Loading")
                is UserScreenModel.State.Result -> {
                    Text("Success")
                }

            }

            Text("Please enter the name of the user to add/get/update/delete")
            TextField (value = UserName, onValueChange={UserName = it})

//            if (!UserName.isNullOrEmpty()) {
//                /* This should be extended in a composable to ask for all the frog information */
//                Button(onClick = { screenModel.addUser(UserName) }) {
//                    Text("Add User")
//                }
//            }
            if (!UserName.isNullOrEmpty()) {
                Button(onClick = { screenModel.getUser(UserName) }) {
                    Text("Get User")
                }
            }
            if (!UserName.isNullOrEmpty()) {
                Button(onClick = { screenModel.updateUser(UserName) }) {
                    Text("Update User")
                }
                Button(onClick = { screenModel.deleteUser(UserName) }) {
                    Text("Delete User")
                }
            }
            if (state is UserScreenModel.State.Result.SingleResult) {
                if ((state as UserScreenModel.State.Result.SingleResult).userData == null) {
                    Text("Not found.  Please try again.")
                } else {
                    Text("The results of the action are:")
                    UserCard(userData = (state as UserScreenModel.State.Result.SingleResult).userData)
                }
            }

        }
    }
}