package com.github.ehsannarmani.repository

import com.github.ehsannarmani.extensions.serializedPost
import com.github.ehsannarmani.utils.Constants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

@OptIn(InternalAPI::class)
class BotRepoImpl(val client: HttpClient) : BotRepo {
    override suspend fun setWebhook(
        token: String,
        url: String,
        dropPendingUpdates:Boolean
    ) {
        client.get("${Constants.BASE_URL}$token/setWebhook?url=$url&drop_pending_updates=$dropPendingUpdates")
    }

    override suspend fun callMethod(token: String, method: String, body: Any?): String {
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
                    if(it.second is File){
                        append(it.first,(it.second as File).readBytes(),Headers.build {
                            append(HttpHeaders.ContentType, "image/png")
                            append(HttpHeaders.ContentDisposition, "filename=\"photo-${System.currentTimeMillis()}.png\"")
                        })
                    }else{
                        append(it.first, it.second)
                    }
                }
            }) {
            this.method = HttpMethod.Post
        }.body()
        return result
    }


}