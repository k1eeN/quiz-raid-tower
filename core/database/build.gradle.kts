plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.kotlinSerialization)
  alias(libs.plugins.androidMultiplatformLibrary)
  alias(libs.plugins.androidLint)
}

kotlin {
  android {
    namespace = "dev.k1een.qrt.core.database"

    compileSdk {
      version = release(37)
    }

    minSdk = 29
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation(libs.kotlin.stdlib)
        implementation(libs.koin.core)
        implementation(libs.ktor.client.core)
        implementation(libs.ktor.client.contentNegotiation)
        implementation(libs.ktor.serialization.kotlinx.json)
        implementation(libs.kotlinx.serialization.json)
      }
    }

    androidMain {
      dependencies {
        implementation(libs.ktor.client.android)
      }
    }
  }
}
