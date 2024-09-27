// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // a Partir de Kotlin 1.9.3 y en epsacial Kotlin 2.0.0, usar Plugin de Compose Compiler.
    alias(libs.plugins.compose.compiler) apply false
}