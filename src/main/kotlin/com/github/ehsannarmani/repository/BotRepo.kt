package com.github.ehsannarmani.repository

interface BotRepo {
    suspend fun setWebhook(token:String,url:String)
}