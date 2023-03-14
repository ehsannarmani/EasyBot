package com.github.ehsannarmani

import com.github.ehsannarmani.model.Update
import com.github.ehsannarmani.plugins.configureBot
import com.github.ehsannarmani.repository.BotRepo
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent

class Bot(
    private val token:String,
    private val onUpdate:(Update)->Unit = {},
    private val onErrorThrown:(Throwable)->Unit = {},
):KoinComponent {

    fun launch(
        post:Int = 3001,
        host:String = "127.0.0.1",
        webhookUrl:String = host
    ){
        val repo:BotRepo by inject()
        setupKoin()
        setWebhook(repo,token,webhookUrl)

        embeddedServer(Netty,host= host,port= post) {
            configureBot (onUpdate = onUpdate,onErrorThrown = onErrorThrown)
        }.start(wait = true)
    }
}