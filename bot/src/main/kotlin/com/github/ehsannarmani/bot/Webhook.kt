package com.github.ehsannarmani.bot

import com.github.ehsannarmani.bot.repository.BotRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun setWebhook(botRepo: BotRepo,token:String,url:String,dropPendingUpdates:Boolean){
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        botRepo.setWebhook(
            token = token,
            url = url,
            dropPendingUpdates = dropPendingUpdates
        )
    }
}