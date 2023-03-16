package com.github.ehsannarmani.model.result

import com.github.ehsannarmani.model.File
import com.github.ehsannarmani.model.Result

data class DownloadFile(
    val result: Result<FilePath>?,
    val fileLink: String
)
