
package com.lduboscq.appkickstarter

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
//import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource
import kotlin.random.Random
import androidx.compose.foundation.Image
import androidx.compose.ui.text.input.PasswordVisualTransformation
import dev.icerock.moko.resources.ImageResource

//import org.jetbrains.compose.resources.painterResource
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainPage(
    onHomeClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    onAboutClicked: () -> Unit
) {
    var backgroundColor by remember { mutableStateOf(Color.White) }

    val colors = MaterialTheme.colors.copy(primary = Color.Red)

    MaterialTheme(colors = colors) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("UrgentAid", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) },
                    actions = {
                        IconButton(onClick = {
                            val randomColor = Color(
                                red = Random.nextFloat(),
                                green = Random.nextFloat(),
                                blue = Random.nextFloat(),
                                alpha = 1f
                            )
                            backgroundColor = randomColor
                        }) {
                            Icon(Icons.Filled.Face, contentDescription = "Change color")
                        }
                    }
                )
            },
            bottomBar = { BottomAppBar { Text("Copyright (c) 2023 Khaled", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) } },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var userName by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

//
//                    Image(
//                        painter = ImageResource("logo.jpg"), null,
//                        modifier = Modifier.size(200.dp)
//                    )



                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = { Text(text = "Please enter your name") },

                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Please enter your password") },
                        visualTransformation = PasswordVisualTransformation(),

                    )

                    Text(
                        text = "Welcome $userName",
                        style = MaterialTheme.typography.h3
                    )

                    Row {
                        Button(onClick = onHomeClicked) {
                            Text("Login")
                        }
                        Spacer(Modifier.width(8.dp))
                        Button(onClick = onRegisterClicked) {
                            Text("Signup")
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = onHomeClicked) {
                            Text("Home")
                        }

                        Spacer(Modifier.width(8.dp))

                        Button(onClick = onAboutClicked) {
                            Text("About")
                        }
                    }
                }

                Text(
                    text = "Your reliable partner in emergencies",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                )
            }
        )
    }
}