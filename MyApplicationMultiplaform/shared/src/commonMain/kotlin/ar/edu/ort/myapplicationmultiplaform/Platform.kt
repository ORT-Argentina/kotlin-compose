package ar.edu.ort.myapplicationmultiplaform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform