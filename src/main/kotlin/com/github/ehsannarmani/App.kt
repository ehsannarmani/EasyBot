package com.github.ehsannarmani

import com.github.ehsannarmani.model.Result
import com.github.ehsannarmani.model.message.TextMessage
import com.github.ehsannarmani.model.update.Update
import com.github.ehsannarmani.plugins.configureBot
import com.github.ehsannarmani.repository.BotRepo
import com.github.ehsannarmani.utils.Constants
import io.ktor.client.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Bot(
    private val token: String,
    private val onUpdate: (Update,Bot) -> Unit = {_,_->},
    private val onErrorThrown: (Throwable) -> Unit = {},
) : KoinComponent {

    private val client: HttpClient by inject()
    private val repo:BotRepo by inject()
    private val baseUrl = Constants.BASE_URL + token

    fun launch(
        post: Int = 3001,
        host: String = "127.0.0.1",
        webhookUrl: String = host
    ) {
        val repo: BotRepo by inject()
        setupKoin()
        setWebhook(repo, token, webhookUrl)

        embeddedServer(Netty, host = host, port = post) {
            configureBot(onUpdate = {
                onUpdate(it,this@Bot)
            }, onErrorThrown = onErrorThrown)
        }.start(wait = true)
    }

    suspend fun sendMessage(message: TextMessage): Result? {
        return call("sendMessage", message)
    }

    private suspend inline fun <reified T> call(method:String, body:Any):T?{
        val res = repo.callMethod(
            token,
            method,
            body
        )
        var returnResult:T? = null
        runCatching {
            returnResult = Json {
                ignoreUnknownKeys = true
            }.decodeFromString(res)
        }
        return returnResult
    }
}