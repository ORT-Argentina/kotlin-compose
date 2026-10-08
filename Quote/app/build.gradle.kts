plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.hilt)
    alias(libs.plugins.google.services)
}

android {
    namespace = "ar.edu.ort.frases"
    compileSdk = 36

    defaultConfig {
        applicationId = "ar.edu.ort.frases"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Alinea en una sola versión todas las librerías de Compose.
    val composeBom = platform(libs.androidx.compose.bom)
    // Aplica esa versión a las librerías de Compose de la app.
    implementation(composeBom)

    // Extensiones de Android en Kotlin.
    implementation(libs.androidx.core.ktx)
    // Sigue el ciclo de vida de la pantalla.
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Conecta la Activity con Compose.
    implementation(libs.androidx.activity.compose)
    // Componentes base para dibujar la interfaz.
    implementation(libs.androidx.ui)
    // Colores, formas y dibujo.
    implementation(libs.androidx.ui.graphics)
    // Muestra previews de Compose en el IDE.
    implementation(libs.androidx.ui.tooling.preview)
    // Botones, textos y demás componentes de Material 3.
    implementation(libs.androidx.material3)
    // Navega entre pantallas.
    implementation(libs.androidx.navigation.compose)

    // Llama a la API de frases.
    implementation(libs.retrofit)
    // Convierte el JSON de la API en objetos.
    implementation(libs.converter.gson)
    // Cliente HTTP que usa Retrofit.
    implementation(libs.okhttp)
    // Escribe en el log cada petición y respuesta.
    implementation(libs.logging.interceptor)

    // Guarda el estado de la pantalla al rotar.
    implementation(libs.androidx.lifecycle.viewmodel)
    // Base compartida de Lifecycle.
    implementation(libs.androidx.lifecycle.common)

    // Inyecta las dependencias de la app.
    implementation(libs.hilt.android)
    // Genera el código de Hilt al compilar.
    ksp(libs.hilt.compiler)
    // Crea ViewModels desde la navegación.
    implementation(libs.androidx.hilt.navigation.compose)

    // Alinea las versiones de Firebase.
    implementation(platform(libs.firebase.bom))
    // Login y sesión del usuario.
    implementation(libs.firebase.auth)
    // Usa las tareas de Google con corrutinas.
    implementation(libs.kotlinx.coroutines.play.services)

    // Pide credenciales al sistema.
    implementation(libs.androidx.credentials)
    // Abre el selector de cuentas de Google.
    implementation(libs.androidx.credentials.play.services.auth)
    // Lee el token de identidad de Google.
    implementation(libs.googleid)

    // Base local donde se guardan los favoritos.
    implementation(libs.androidx.room.runtime)
    // Usa Room con corrutinas.
    implementation(libs.androidx.room.ktx)
    // Genera el código de la base al compilar.
    ksp(libs.androidx.room.compiler)

    // Tests locales, sin dispositivo.
    testImplementation(libs.junit)
    // Tests que corren en un dispositivo.
    androidTestImplementation(libs.androidx.junit)
    // Interactúa con la interfaz en esos tests.
    androidTestImplementation(libs.androidx.espresso.core)
    // Misma versión de Compose para los tests.
    androidTestImplementation(composeBom)
    // Prueba pantallas de Compose.
    androidTestImplementation(libs.androidx.ui.test.junit4)
    // Inspector de Compose, solo en debug.
    debugImplementation(libs.androidx.ui.tooling)
    // Manifiesto de tests de Compose, solo en debug.
    debugImplementation(libs.androidx.ui.test.manifest)
}
