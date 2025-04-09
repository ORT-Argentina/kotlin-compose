package ar.edu.ort.myapplicationmultiplaform

import kotlin.random.Random

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        val firstWord = if (Random.nextBoolean()) "Hi!" else "Hello!"

        return "${firstWord}, ${platform.name}!"
    }
}