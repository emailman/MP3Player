package edu.mailman.mp3

import javafx.embed.swing.JFXPanel
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer

class DesktopAudioPlayer : AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    init {
        // Initialize JavaFX toolkit
        try {
            JFXPanel()
        } catch (e: Exception) {
            println("JavaFX initialization: ${e.message}")
        }
        
        // Get the audio file path from resources
        val resource = Thread.currentThread().contextClassLoader.getResource("audio/sample.mp3")
            ?: error("audio/sample.mp3 not found in resources")
        
        println("Loading audio from: ${resource.toExternalForm()}")
        
        // Create a media player
        try {
            val media = Media(resource.toExternalForm())
            mediaPlayer = MediaPlayer(media).apply {
                setOnError {
                    println("MediaPlayer error: ${this.error?.message}")
                }
                setOnReady {
                    println("MediaPlayer ready. Duration: ${media.duration}")
                }
            }
        } catch (e: Exception) {
            println("Error creating MediaPlayer: ${e.message}")
            e.printStackTrace()
        }
    }

    override fun play() {
        println("Play called")
        mediaPlayer?.play()
    }

    override fun stop() {
        println("Stop called")
        mediaPlayer?.let {
            it.stop()
            it.seek(javafx.util.Duration.ZERO)
        }
    }

    override fun isPlaying(): Boolean {
        val playing = mediaPlayer?.status == MediaPlayer.Status.PLAYING
        println("isPlaying: $playing")
        return playing
    }

    override fun dispose() {
        println("Dispose called")
        mediaPlayer?.dispose()
        mediaPlayer = null
    }
}