package com.github.ehsannarmani

import com.github.ehsannarmani.repository.BotRepo
import com.github.ehsannarmani.repository.BotRepoImpl
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*
import kotlinx.serialization.serializer
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun setupKoin(){
    startKoin {
        modules(module {
            single {
                HttpClient(CIO){
                    expectSuccess = false
                    install(Logging){
                        level = LogLevel.BODY
                    }
                }
            }
            single {
                val botRepo:BotRepo = BotRepoImpl(client = get())
                botRepo
            }
        })
    }
}