plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.androidLint)
}

kotlin {
    android {
        namespace = "dev.k1een.qrt.feature.home.api"

        compileSdk {
            version = release(37)
        }

        minSdk = 29
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
            api(libs.androidx.navigation3.runtime)
            api(libs.kotlinx.serialization.core)
        }
    }
}
