package com.github.ehsannarmani.bot.repository

import com.github.ehsannarmani.bot.model.database.*
import com.github.ehsannarmani.bot.model.update.Photo
import com.github.ehsannarmani.bot.model.update.Update
import com.github.ehsannarmani.bot.utils.Constants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Paths

@OptIn(InternalAPI::class)
class BotRepoImpl(val client: HttpClient) : BotRepo {
    override suspend fun setWebhook(
        token: String,
        url: String,
        dropPendingUpdates: Boolean
    ) {
        client.get("${Constants.BASE_URL}$token/setWebhook?url=$url&drop_pending_updates=$dropPendingUpdates")
    }

    override suspend fun callMethod(
        token: String,
        method: String,
        body: Any?
    ): String {
        val result: String = client.post("${Constants.BASE_URL}$token/$method") {
            setBody(body)
        }.body()
        return result
    }

    override suspend fun callMethodWithMap(token: String, method: String, params: List<Pair<String, Any>>): String {
        val result: String = client.submitFormWithBinaryData(
            url = "${Constants.BASE_URL}$token/$method",
            formData = formData {
                params.forEach {
                    if (it.second is File) {
                        append(it.first, (it.second as File).readBytes(), Headers.build {
                            append(HttpHeaders.ContentType, "image/png")
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=\"photo-${System.currentTimeMillis()}.png\""
                            )
                        })
                    } else {
                        append(it.first, it.second)
                    }
                }
            }) {
            this.method = HttpMethod.Post
        }.body()
        return result
    }

    override suspend fun getUpdates(token: String, offset: String, timeout: Int): String {
        return client.get("https://api.telegram.org/bot$token/getUpdates?timeout=$timeout&offset=$offset") {
            this.timeout {
                requestTimeoutMillis = timeout.toLong() * 1000
            }
        }.body()
    }

    override suspend fun getUpdates(token: String, timeout: Int): String {
        return client.get("https://api.telegram.org/bot$token/getUpdates?timeout=$timeout") {
            this.timeout {
                requestTimeoutMillis = timeout.toLong() * 1000
            }
        }.body()
    }


}