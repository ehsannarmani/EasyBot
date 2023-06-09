package com.github.ehsannarmani.bot

import com.github.ehsannarmani.bot.repository.BotRepo
import com.github.ehsannarmani.bot.repository.BotRepoImpl
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun setupKoin() {
    startKoin {
        modules(module {
            single {
                HttpClient(CIO) {
                    expectSuccess = false
//                    install(Logging) {
//                        level = LogLevel.BODY
//                    }
                    install(HttpTimeout)
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                            encodeDefaults = true
                            explicitNulls = false
                            classDiscriminator = "clazz"
                        })
                    }
                    defaultRequest {
                        header("Content-Type", "application/json")
                    }

                }
            }
            single {
                val botRepo: BotRepo = BotRepoImpl(client = get())
                botRepo
            }
        })
    }
}