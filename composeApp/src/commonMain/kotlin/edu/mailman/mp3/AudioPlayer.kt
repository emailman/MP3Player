package edu.mailman.mp3

interface AudioPlayer {
    fun play()
    fun stop()
    fun isPlaying(): Boolean
    fun dispose()
}