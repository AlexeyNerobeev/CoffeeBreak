import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.21"
//    kotlin("kapt")
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.cofeebreak"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.cofeebreak"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "SUPABASE_URL",
            "\"${getLocalProperty("SUPABASE_URL")}\""
        )

        buildConfigField(
            "String",
            "SUPABASE_KEY",
            "\"${getLocalProperty("SUPABASE_KEY")}\""
        )

        buildConfigField(
            "String",
            "GOOGLE_SERVER_CLIENT_ID",
            "\"${getLocalProperty("GOOGLE_SERVER_CLIENT_ID")}\""
        )

        buildConfigField(
            "String",
            "MAPKIT_API_KEY",
            "\"${getLocalProperty("MAPKIT_API_KEY")}\""
        )
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
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    //lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.ui.geometry)

    // Test dependencies
//    testImplementation ("junit:junit:4.13.2")
//    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // Для Android тестов
//    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.5.4")
//    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.5.4")

    //coil
    implementation(libs.coil.compose)
    //implementation("io.coil-kt.coil3:coil-compose-core:3.2.0")
    implementation(libs.coil.network.okhttp)

    //qr
    implementation(libs.core)

    //koin
//    implementation(libs.io.koin.compose)
//    implementation(libs.io.koin.core)
//    implementation(libs.io.koin.android)

    //hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.navigation.testing)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation)

    androidTestImplementation (libs.hilt.android.testing)
    androidTestImplementation (libs.androidx.core)
    androidTestImplementation (libs.androidx.runner)
    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.ui.test.junit4)


    //ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)

    //supabase
    implementation(platform(libs.bom))
    implementation(libs.postgrest.kt)
    implementation(libs.auth.kt)
    implementation(libs.realtime.kt)
    implementation(libs.storage.kt)
    implementation(libs.compose.auth)

    //navigation
    implementation (libs.androidx.navigation.compose)

    //карта
    implementation(libs.maps.mobile)

    //google
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    //tensorflow lite
    implementation(libs.tensorflow.lite)
    implementation(libs.tensorflow.lite.task.vision)
    implementation (libs.tensorflow.lite.support)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

fun getLocalProperty(key: String): String {
    val properties = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")

    return if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { properties.load(it) }
        properties.getProperty(key, "").trim()
    } else {
        ""
    }
}


//// Функция для безопасного чтения ключа из local.properties
//fun getMapkitApiKey(): String {
//    val properties = Properties()
//    val localPropertiesFile = project.rootProject.file("local.properties")
//
//    return if (localPropertiesFile.exists()) {
//        localPropertiesFile.inputStream().use { properties.load(it) }
//        properties.getProperty("MAPKIT_API_KEY", "").trim()
//    } else {
//        "" // Или можно выбросить исключение: error("local.properties not found")
//    }
//}