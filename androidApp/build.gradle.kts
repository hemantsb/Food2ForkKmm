import org.jetbrains.kotlin.config.JvmAnalysisFlags.useIR

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin(KotlinPlugins.kapt)
    id(Plugins.hilt)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "bit.hemant.kmmfood2fork.android"
        minSdk = 22
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Accompanist.coil)
    implementation(AndroidX.appCompat)

    implementation(Compose.runtime)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiTooling)
    implementation(Compose.foundation)
    implementation(Compose.compiler)
    implementation(Compose.constraintLayout)
    implementation(Compose.activity)
    implementation(Compose.navigation)

    implementation(Google.material)

    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltNavigation)
    kapt(Hilt.hiltCompiler)

    implementation(Kotlinx.datetime)
    implementation(Ktor.android)

    debugImplementation(SquareUp.leakCanary)
}