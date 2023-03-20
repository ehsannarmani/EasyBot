package com.github.ehsannarmani.bot.plugins

import com.github.ehsannarmani.bot.model.update.Update
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

fun Application.configureBot(
    onUpdate:(Update)->Unit,
    onTextUpdate:(String)->Unit,
    onErrorThrown:(Throwable)->Unit
) {
    routing {
        post("/bot"){

            val input = call.receiveText()
            onTextUpdate(input)
            print(input)
            runCatching {
                Json{
                    ignoreUnknownKeys = true
                }.decodeFromString<Update>(input)
            }
                .onSuccess(onUpdate)
                .onFailure(onErrorThrown)
        }
    }
}
