package com.github.ehsannarmani.repository


import com.github.ehsannarmani.model.database.UserData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Paths

interface BotRepo {
    suspend fun setWebhook(token: String, url: String, dropPendingUpdates: Boolean)
    suspend fun callMethod(token: String, method: String, body: Any?): String
    suspend fun callMethodWithMap(token: String, method: String, params: List<Pair<String, Any>>): String

}





