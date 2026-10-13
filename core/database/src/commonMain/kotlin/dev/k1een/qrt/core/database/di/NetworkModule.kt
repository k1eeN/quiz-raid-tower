package dev.k1een.qrt.core.database.di

import dev.k1een.qrt.core.database.data.network.api.OpenTdbApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            expectSuccess = true
            defaultRequest {
                url("https://opentdb.com/")
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
    single { OpenTdbApiService(get()) }
}
