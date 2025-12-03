package edu.mailman.mp3

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MP3 Player",
    ) {
        AudioApp { DesktopAudioPlayer() }
    }
}