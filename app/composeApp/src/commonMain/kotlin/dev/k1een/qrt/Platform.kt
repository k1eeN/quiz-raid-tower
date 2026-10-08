package dev.k1een.qrt

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform