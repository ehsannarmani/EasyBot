package com.github.ehsannarmani.bot.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Me(
    @SerialName("id")
    val id:Long,
    @SerialName("is_bot")
    val isBot:Boolean,
    @SerialName("first_name")
    val firstName:String,
    @SerialName("username")
    val username:String,
    @SerialName("can_join_groups")
    val canJoinGroups:Boolean,
    @SerialName("can_read_all_group_messages")
    val canReadAllGroupMessages:Boolean,
    @SerialName("supports_inline_queries")
    val supportInlineQueries:Boolean
)
