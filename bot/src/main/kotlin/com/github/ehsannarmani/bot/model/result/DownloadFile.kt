package com.github.ehsannarmani.bot.model.result

import com.github.ehsannarmani.bot.model.Result

data class DownloadFile(
    val result: Result<FilePath>?,
    val fileLink: String
)
