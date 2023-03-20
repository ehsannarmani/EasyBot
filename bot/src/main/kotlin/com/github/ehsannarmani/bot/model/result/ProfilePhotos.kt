package com.github.ehsannarmani.bot.model.result

import com.github.ehsannarmani.bot.model.method.File
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProfilePhotos(
    @SerialName("total_count")
    val totalCount:Int,
    @SerialName("photos")
    val photos:List<List<File>>
)
