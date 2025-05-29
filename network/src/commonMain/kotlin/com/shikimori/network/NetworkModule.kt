package com.shikimori.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.header
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object NetworkModule {
    
    fun provideHttpClient(): HttpClient {
        return HttpClient {
            // JSON serialization
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = false
                })
            }
            
            // Detailed logging for debugging
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                sanitizeHeader { header -> header == "Authorization" }
            }
            
            // Default request configuration according to Shikimori API
            defaultRequest {
                url("https://shikimori.one/api/")
                
                // Обязательные заголовки для Shikimori API
                header("User-Agent", "ShikimoriKMP/1.0 (Mobile App)")
                header("Accept", "application/json")
                header("Content-Type", "application/json")
                // Убираем лишние слеши и правильно указываем base URL
            }
            
            // Timeout configuration
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 10000
                socketTimeoutMillis = 10000
            }
        }
    }
}
