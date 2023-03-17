package com.github.ehsannarmani.model.method.inline_query

import com.github.ehsannarmani.model.method.inline_query.result.InlineQueryResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AnswerInlineQuery(
    @SerialName("inline_query_id")
    val inlineQueryId:String,
    @SerialName("results")
    val results:List<InlineQueryResult>,
    @SerialName("cache_time")
    val cacheTime:Int? = null,
    @SerialName("is_personal")
    val isPersonal:Boolean? = null,
    @SerialName("next_offset")
    val nextOffset:String? = null,
    @SerialName("switch_pm_text")
    val switchPmText:String? = null,
    @SerialName("switch_pm_parameter")
    val switchPmParameter:String? = null,

)
