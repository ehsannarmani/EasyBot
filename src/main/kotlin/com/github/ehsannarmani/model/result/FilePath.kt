package com.github.ehsannarmani.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FilePath(
    @SerialName("file_path") val filePath:String,
)
