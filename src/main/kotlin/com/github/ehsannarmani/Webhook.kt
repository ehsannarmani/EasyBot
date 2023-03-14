package com.github.ehsannarmani

import com.github.ehsannarmani.repository.BotRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun setWebhook(botRepo: BotRepo,token:String,url:String){
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        botRepo.setWebhook(token,url)
    }
}