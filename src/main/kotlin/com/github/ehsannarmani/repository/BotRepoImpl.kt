package com.github.ehsannarmani.repository

import com.github.ehsannarmani.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*

class BotRepoImpl(private val client: HttpClient):BotRepo {
    override suspend fun setWebhook(
        token:String,
        url:String
    ) {
        client.get("${Constants.BASE_URL}$token/setWebhook?url=$url")
    }
}