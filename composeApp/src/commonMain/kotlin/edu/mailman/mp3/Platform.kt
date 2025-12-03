package edu.mailman.mp3

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform