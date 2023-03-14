package com.github.ehsannarmani.repository

interface BotRepo {
    suspend fun setWebhook(token:String,url:String)
    suspend fun callMethod(token: String,method:String,body:Any):String
}