package com.lduboscq.appkickstarter

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.lduboscq.appkickstarter.ui.Screen
import com.lduboscq.appkickstarter.ui.UserScreenModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    when (currentScreen) {
        is Screen.Home -> {
            MainPage(
                onHomeClicked = { currentScreen = Screen.Home },
                onRegisterClicked = { currentScreen = Screen.Register },
                onAboutClicked = { currentScreen = Screen.About }
            )
        }
        is Screen.About -> {
            AboutScreen(onBackClicked = { currentScreen = Screen.Home })
        }
        is Screen.Register -> {
            UserScreen(onBackClicked = { currentScreen = Screen.Home })
        }
    }
}

@Composable
fun AboutScreen(onBackClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "UrgentAid is a reliable partner in emergencies. With our application, you can easily connect with medical professionals and get urgent assistance whenever and wherever you need it.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
        }

        Button(
            onClick = onBackClicked,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Back", color = Color.White)
        }
    }
}



@Composable
fun UserScreen(onBackClicked: () -> Unit) {
    val screenModel = remember { UserScreenModel(UserRepositoryRealmLocal()) }
    val state by screenModel.state.collectAsState()

    var userName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val result = state) {
            is UserScreenModel.State.Init -> Text("")
            is UserScreenModel.State.Loading -> Text("Loading")
            is UserScreenModel.State.Result -> Text("Success")
        }

        Text("Please enter the details to Register")

        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.LightGray
            )
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.LightGray
            )
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.LightGray
            )
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.LightGray
            )
        )

        if (!userName.isNullOrEmpty() && !age.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            val userAge = age.toIntOrNull()
            if (userAge != null) {
                Button(
                    onClick = { screenModel.addUser(userName, userAge, email, password) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("Add User", color = Color.White)
                }
                Button(
                    onClick = { screenModel.getUser(userName) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("Get User", color = Color.White)
                }
                Button(
                    onClick = { screenModel.updateUser(userName,age) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("Update User", color = Color.White)
                }
                Button(
                    onClick = { screenModel.deleteUser(userName) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("Delete User", color = Color.White)
                }
            } else {
                Text("Please enter a valid number for age")
            }
        }

        if (state is UserScreenModel.State.Result.SingleResult) {
            if ((state as UserScreenModel.State.Result.SingleResult).userData == null) {
                Text("Not found. Please try again.")
            } else {
                Text("The results of the action are:")
                UserCard(userData = (state as UserScreenModel.State.Result.SingleResult).userData)
            }
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onBackClicked,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
        ) {
            Text("Back", color = Color.White)
        }
    }
}
