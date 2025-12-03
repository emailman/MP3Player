package edu.mailman.mp3

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp

@Composable
fun AudioApp(audioPlayerFactory: () -> AudioPlayer) {
    val player = remember { audioPlayerFactory() }
    var playing by remember { mutableStateOf(false) }

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = {
                    if (playing) {
                        player.stop()
                    } else {
                        player.play()
                    }
                    playing = !playing
                }) {
                    Text(if (playing) "STOP" else "PLAY")
                }

                Text(
                    text = if (playing) "🎵 Now Playing..." else "⏹ Stopped",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose { player.dispose() }
    }
}