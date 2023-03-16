package com.github.ehsannarmani

import com.github.ehsannarmani.model.Result
import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.result.Me
import com.github.ehsannarmani.model.result.MessageId
import com.github.ehsannarmani.model.update.Message
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

    suspend fun getMe():Result<Me>?{
        return call("getMe",null)
    }
    suspend fun sendMessage(message: TextMessage): Result<Message>? {
        return call("sendMessage", message)
    }
    suspend fun forwardMessage(message:ForwardMessage):Result<Message>?{
        return call("forwardMessage",message)
    }

    suspend fun copyMessage(message:CopyMessage):Result<MessageId>?{
        return call("copyMessage",message)
    }
    suspend fun sendPhoto(message:PhotoMessage):Result<Message>?{
        return call("sendPhoto",message)
    }

    suspend fun sendAudio(message:AudioMessage):Result<Message>?{
        return call("sendAudio",message)
    }
    suspend fun sendDocument(message:DocumentMessage):Result<Message>?{
        return call("sendDocument",message)
    }
    suspend fun sendVideo(message:VideoMessage):Result<Message>?{
        return call("sendVideo",message)
    }
    suspend fun sendAnimation(message:AnimationMessage):Result<Message>?{
        return call("sendAnimation",message)
    }
    suspend fun sendVoice(message:VoiceMessage):Result<Message>?{
        return call("sendVoice",message)
    }
    suspend fun sendVideoNote(message:VideoNoteMessage):Result<Message>?{
        return call("sendVideoNote",message)
    }
    suspend fun sendMediaGroup(message:MediaGroupMessage):Result<List<Message>>?{
        return call("sendMediaGroup",message)
    }
    suspend fun sendLocation(message:LocationMessage):Result<Message>?{
        return call("sendLocation",message)
    }
    suspend fun sendVenue(message:VenueMessage):Result<Message>?{
        return call("sendVenue",message)
    }

    private suspend inline fun <reified T:Any> call(method:String, body:Any?):T?{
        val res = repo.callMethod(
            token,
            method,
            body
        )
        print("\n\n$method: $res\n\n")
        var returnResult:T? = null
        runCatching {
            returnResult = Json {
                ignoreUnknownKeys = true
            }.decodeFromString(res)
        }.onFailure {
            println("\n\nerror: ${it.message}\n\n")
        }
        return returnResult
    }
    private suspend fun callWithoutSerialize(method: String, body: Any?): String {
        return repo.callMethod(
            token,
            method,
            body
        )
    }
}