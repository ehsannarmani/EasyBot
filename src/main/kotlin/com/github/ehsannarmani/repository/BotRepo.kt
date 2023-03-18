package com.github.ehsannarmani.repository

interface BotRepo {
    suspend fun setWebhook(token:String,url:String,dropPendingUpdates:Boolean)
    suspend fun callMethod(token: String,method:String,body:Any?):String
    suspend fun callMethodWithMap(token: String,method:String,params:List<Pair<String,Any>>):String
}