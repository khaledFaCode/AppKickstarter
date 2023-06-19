package com.lduboscq.appkickstarter.ui

sealed class Screen {
    object Home : Screen()
    object Register : Screen()
    object About : Screen()
}
