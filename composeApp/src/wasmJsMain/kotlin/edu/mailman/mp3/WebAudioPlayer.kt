package edu.mailman.mp3

import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeWindow

class WebAudioPlayer : AudioPlayer {
    private val audio: HTMLAudioElement by lazy {
        val a = document.createElement("audio") as HTMLAudioElement
        a.src = "audio/sample.mp3"
        a.loop = true
        a
    }
    private var playing = false

    @OptIn(ExperimentalWasmJsInterop::class)
    override fun play() { audio.play(); playing = true }
    override fun stop() { audio.pause(); audio.currentTime = 0.0; playing = false }
    override fun isPlaying(): Boolean = playing
    override fun dispose() { audio.pause(); audio.src = ""; playing = false }
}

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeWindow().setContent {
        AudioApp { WebAudioPlayer() }
    }
}