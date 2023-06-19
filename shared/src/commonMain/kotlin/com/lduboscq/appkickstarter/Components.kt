package com.lduboscq.appkickstarter

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBanner() {
    // Customize the top banner layout and content based on your needs
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Application Name",
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
fun BottomBanner(
    onHomeClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    onAboutClicked: () -> Unit
) {
    // Customize the bottom banner layout and content based on your needs
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onHomeClicked) {
            Text("Home")
        }
        TextButton(onClick = onRegisterClicked) {
            Text("Register")
        }
        TextButton(onClick = onAboutClicked) {
            Text("About Us")
        }
    }
}
