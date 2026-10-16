plugins {
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.androidLint)
}

kotlin {
    android {
        namespace = "dev.k1een.qrt.core.navigation"

        compileSdk {
            version = release(37)
        }

        minSdk = 29
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
            api(libs.compose.runtime)
            api(libs.compose.ui)
            api(libs.androidx.navigation3.runtime)
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.androidx.navigation3.ui)
            implementation(libs.androidx.lifecycle.viewmodelNavigation3)
        }
    }
}
