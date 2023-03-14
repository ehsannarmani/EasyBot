package com.github.ehsannarmani.extensions

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

suspend inline fun <reified T>HttpClient.serializedPost(
    url:String,
    params:List<Pair<String,Any>>
):T?{
    val result: String = submitForm(url, formParameters = Parameters.build {
        params.forEach {
            append(it.first,it.second.toString())
        }
    }){
        method = HttpMethod.Post
    }.body()
    var returnResult:T? = null
    runCatching {
        returnResult = Json {
            ignoreUnknownKeys = true
        }.decodeFromString(result)
    }
    return returnResult
}