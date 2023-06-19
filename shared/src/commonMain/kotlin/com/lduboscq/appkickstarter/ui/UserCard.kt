@file:OptIn(ExperimentalMaterialApi::class)

package com.lduboscq.appkickstarter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun UserCard(userData:UserData?) {
    Card(
        modifier = Modifier.size(width = 180.dp, height = 100.dp)
            .clip(MaterialTheme.shapes.small)
    ) {
        if (userData != null) {
            Column {
                Text("Name : " + userData?.name)
                Text("Age : " + userData?.age)
                Text("Species : " + userData?.species)
                Text("owner : " + userData?.owner)
            }
        } else {
            Text("No User result")
        }
    }
}