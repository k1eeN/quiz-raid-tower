plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    android {
        namespace = "dev.k1een.qrt.core.designsystem"
        compileSdk {
            version = release(37)
        }
        minSdk = 29
        androidResources {
            enable = true
        }
    }
    sourceSets {
        commonMain.dependencies {
            api(libs.compose.runtime)
            api(libs.compose.ui)
            api(libs.compose.components.resources)
            api(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.uiToolingPreview)
        }
        androidMain.dependencies {
            implementation(libs.compose.uiTooling)
            implementation(libs.lottie.compose)
        }
    }
}

compose.resources {
    packageOfResClass = "dev.k1een.qrt.core.designsystem.resources"
}
