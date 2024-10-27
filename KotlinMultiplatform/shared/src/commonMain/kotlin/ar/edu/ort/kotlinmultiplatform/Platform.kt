package ar.edu.ort.kotlinmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform