package com.github.ehsannarmani.plugins

import com.github.ehsannarmani.model.Update
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.util.Identity.decode
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

fun Application.configureBot(
    onUpdate:(Update)->Unit,
    onErrorThrown:(Throwable)->Unit
) {
    routing {
        post("/bot"){

            val input = call.receiveText()
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
