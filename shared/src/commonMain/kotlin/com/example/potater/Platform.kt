package com.example.potater

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform