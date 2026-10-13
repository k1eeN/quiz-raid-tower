plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidMultiplatformLibrary)
  alias(libs.plugins.androidLint)
}

kotlin {
  android {
    namespace = "dev.k1een.qrt.feature.game.impl"

    compileSdk {
      version = release(37)
    }

    minSdk = 29
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation(project(":feature:game:api"))
        implementation(libs.kotlin.stdlib)
      }
    }

    androidMain {
      dependencies {
        // Android-specific dependencies
      }
    }
  }
}