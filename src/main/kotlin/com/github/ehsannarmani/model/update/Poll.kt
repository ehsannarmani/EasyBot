package com.github.ehsannarmani.model.update

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Poll(
    @SerialName("id")
    val pollId:Long,
    @SerialName("question")
    val question:String,
    @SerialName("options")
    val options:List<PollOptions>,
    @SerialName("total_voter_count")
    val totalVoterCount:Int,
    @SerialName("is_closed")
    val isClosed:Boolean,
    @SerialName("is_anonymous")
    val isAnonymous:Boolean,
    @SerialName("type")
    val type:String,
    @SerialName("allow_multiple_answers")
    val allowMultipleAnswers:Boolean,
)

@Serializable
data class PollOptions(
    @SerialName("text")
    val text:String,
    @SerialName("voter_count")
    val voteCount:Int
)
