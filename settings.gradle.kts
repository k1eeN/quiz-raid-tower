rootProject.name = "QuizRaidTower"

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":app:androidApp")
include(":app:composeApp")
include(":core:database")
include(":core:designsystem")
include(":core:navigation")
include(":feature:home:api")
include(":feature:home:impl")
include(":feature:game:api")
include(":feature:game:impl")
