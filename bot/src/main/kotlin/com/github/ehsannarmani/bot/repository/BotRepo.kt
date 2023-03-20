package com.github.ehsannarmani.bot.repository


interface BotRepo {
    suspend fun setWebhook(token: String, url: String, dropPendingUpdates: Boolean)
    suspend fun callMethod(token: String, method: String, body: Any?): String
    suspend fun callMethodWithMap(token: String, method: String, params: List<Pair<String, Any>>): String
    suspend fun getUpdates(token:String,offset:String,timeout:Int):String
    suspend fun getUpdates(token:String,timeout:Int):String
}





