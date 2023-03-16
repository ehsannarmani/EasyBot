package com.github.ehsannarmani

import com.github.ehsannarmani.model.*
import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.method.*
import com.github.ehsannarmani.model.result.*
import com.github.ehsannarmani.model.update.ChatMember
import com.github.ehsannarmani.model.update.Message
import com.github.ehsannarmani.model.update.Update
import com.github.ehsannarmani.plugins.configureBot
import com.github.ehsannarmani.repository.BotRepo
import com.github.ehsannarmani.utils.Constants
import io.ktor.client.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

class Bot(
    private val token: String,
    private val onUpdate: (Update,Bot) -> Unit = {_,_->},
    private val onErrorThrown: (Throwable) -> Unit = {},
) : KoinComponent {

    private val client: HttpClient by inject()
    private val repo:BotRepo by inject()
    private val baseUrl = Constants.BASE_URL + token

    fun launch(
        post: Int = 3001,
        host: String = "127.0.0.1",
        webhookUrl: String = host
    ) {
        val repo: BotRepo by inject()
        setupKoin()
        setWebhook(repo, token, webhookUrl)

        embeddedServer(Netty, host = host, port = post) {
            configureBot(onUpdate = {
                onUpdate(it,this@Bot)
            }, onErrorThrown = onErrorThrown)
        }.start(wait = true)
    }

    suspend fun getMe():Result<Me>?{
        return call("getMe",null)
    }
    suspend fun sendMessage(message: TextMessage): Result<Message>? {
        return call("sendMessage", message)
    }
    suspend fun forwardMessage(message:ForwardMessage):Result<Message>?{
        return call("forwardMessage",message)
    }

    suspend fun copyMessage(message:CopyMessage):Result<MessageId>?{
        return call("copyMessage",message)
    }
    suspend fun sendPhoto(message:PhotoMessage):Result<Message>?{
        return call("sendPhoto",message)
    }

    suspend fun sendAudio(message:AudioMessage):Result<Message>?{
        return call("sendAudio",message)
    }
    suspend fun sendDocument(message:DocumentMessage):Result<Message>?{
        return call("sendDocument",message)
    }
    suspend fun sendVideo(message:VideoMessage):Result<Message>?{
        return call("sendVideo",message)
    }
    suspend fun sendAnimation(message:AnimationMessage):Result<Message>?{
        return call("sendAnimation",message)
    }
    suspend fun sendVoice(message:VoiceMessage):Result<Message>?{
        return call("sendVoice",message)
    }
    suspend fun sendVideoNote(message:VideoNoteMessage):Result<Message>?{
        return call("sendVideoNote",message)
    }
    suspend fun sendMediaGroup(message:MediaGroupMessage):Result<List<Message>>?{
        return call("sendMediaGroup",message)
    }
    suspend fun sendLocation(message:LocationMessage):Result<Message>?{
        return call("sendLocation",message)
    }
    suspend fun sendVenue(message:VenueMessage):Result<Message>?{
        return call("sendVenue",message)
    }
    suspend fun sendContact(message:ContactMessage):Result<Message>?{
        return call("sendContact",message)
    }
    suspend fun sendPoll(message:PollMessage):Result<Message>?{
        return call("sendPoll",message)
    }
    suspend fun sendDice(message:DiceMessage):Result<Message>?{
        return call("sendDice",message)
    }
    suspend fun sendChatAction(action: ChatAction):Result<Boolean>?{
        return call("sendChatAction",action)
    }
    suspend fun getUserProfilePhotos(user: UserProfilePhotos):Result<ProfilePhotos>?{
        return call("getUserProfilePhotos",user)
    }
    suspend fun getFile(fileId:String):DownloadFile{
        val file: Result<FilePath>? = callWithMap("getFile", listOf("file_id" to fileId))
        return DownloadFile(
            result = file,
            fileLink = "https://api.telegram.org/file/bot$token/${file?.result?.filePath}"
        )
    }
    suspend fun banChatMember(member: BanChatMember):Result<Boolean>?{
        return call("banChatMember",member)
    }
    suspend fun unbanChatMember(member: UnBanChatMember):Result<Boolean>?{
        return call("unbanChatMember",member)
    }
    suspend fun restrictChatMember(member: RestrictChatMember):Result<Boolean>?{
        return call("restrictChatMember",member)
    }
    suspend fun promoteChatMember(member: PromoteChatMember):Result<Boolean>?{
        return call("promoteChatMember",member)
    }
    suspend fun setChatAdminCustomTitle(title: ChatAdminCustomTitle):Result<Boolean>?{
        return call("setChatAdministratorCustomTitle",title)
    }
    suspend fun banChatSenderChat(senderChat: ChatSenderChat):Result<Boolean>?{
        return call("banChatSenderChat",senderChat)
    }
    suspend fun unbanChatSenderChat(senderChat: ChatSenderChat):Result<Boolean>?{
        return call("unbanChatSenderChat",senderChat)
    }
    suspend fun setChatPermissions(chat: ChangeChatPermissions):Result<Boolean>?{
        return call("setChatPermissions", chat)
    }
    suspend fun exportChatInviteLink(chatId:String):Result<String>?{
        return callWithMap("exportChatInviteLink", listOf("chat_id" to chatId))
    }
    suspend fun createChatInviteLink(link: ChatInviteLink):Result<InviteLink>?{
        return call("createChatInviteLink", link)
    }
    suspend fun editChatInviteLink(link: ChatInviteLink):Result<InviteLink>?{
        return call("editChatInviteLink", link)
    }
    suspend fun revokeChatInviteLink(chatId:String,inviteLink:String):Result<InviteLink>?{
        return callWithMap("revokeChatInviteLink", listOf(
            "chat_id" to chatId,
            "invite_link" to inviteLink
        ))
    }
    suspend fun approveChatJoinRequest(chatId:String,userId:Long):Result<Boolean>?{
        return callWithMap("approveChatJoinRequest", listOf(
            "chat_id" to chatId,
            "user_id" to userId
        ))
    }
    suspend fun declineChatJoinRequest(chatId:String,userId:Long):Result<Boolean>?{
        return callWithMap("declineChatJoinRequest", listOf(
            "chat_id" to chatId,
            "user_id" to userId
        ))
    }
    suspend fun setChatPhoto(chatId:String,photo:File):Result<Boolean>? {
        return callWithMap("setChatPhoto", listOf(
            "chat_id" to chatId,
            "photo" to photo
        ))
    }
    suspend fun deleteChatPhoto(chatId:String):Result<Boolean>? {
        return callWithMap("deleteChatPhoto", listOf(
            "chat_id" to chatId,
        ))
    }
    suspend fun setChatTitle(chatId:String,title:String):Result<Boolean>? {
        return callWithMap("setChatTitle", listOf(
            "chat_id" to chatId,
            "title" to title
        ))
    }
    suspend fun setChatDescription(chatId:String,description:String):Result<Boolean>? {
        return callWithMap("setChatDescription", listOf(
            "chat_id" to chatId,
            "description" to description
        ))
    }
    suspend fun pinChatMessage(chatId:String,messageId:Int,disableNotification:Boolean = false):Result<Boolean>? {
        return callWithMap("pinChatMessage", listOf(
            "chat_id" to chatId,
            "message_id" to messageId,
            "disable_notification" to disableNotification
        ))
    }
    suspend fun unpinChatMessage(chatId:String,messageId:Int):Result<Boolean>? {
        return callWithMap("unpinChatMessage", listOf(
            "chat_id" to chatId,
            "message_id" to messageId,
        ))
    }
    suspend fun unpinAllChatMessages(chatId:String):Result<Boolean>? {
        return callWithMap("unpinAllChatMessages", listOf(
            "chat_id" to chatId,
        ))
    }
    suspend fun leaveChat(chatId:String):Result<Boolean>? {
        return callWithMap("leaveChat", listOf(
            "chat_id" to chatId,
        ))
    }
    suspend fun getChat(chatId:String):Result<Chat>? {
        return callWithMap("getChat", listOf(
            "chat_id" to chatId,
        ))
    }
    suspend fun getChatAdministrators(chatId:String):Result<List<ChatMember>>? {
        return callWithMap("getChatAdministrators", listOf(
            "chat_id" to chatId,
        ))
    }
    suspend fun getChatMemberCount(chatId:String):Result<Int>? {
        return callWithMap("getChatMemberCount", listOf(
            "chat_id" to chatId,
        ))
    }
    suspend fun getChatMember(chatId:String,userId:Long):Result<ChatMember>? {
        return callWithMap("getChatMember", listOf(
            "chat_id" to chatId,
            "user_id" to userId
        ))
    }
    suspend fun setChatStickerSet(chatId:String, stickerSetName:String):Result<Boolean>? {
        return callWithMap("setChatStickerSet", listOf(
            "chat_id" to chatId,
            "sticker_set_name" to stickerSetName
        ))
    }
    suspend fun deleteChatStickerSet(chatId:String):Result<Boolean>? {
        return callWithMap("deleteChatStickerSet", listOf(
            "chat_id" to chatId,
        ))
    }

    private suspend inline fun <reified T:Any> call(method:String, body:Any?):T?{
        val res = repo.callMethod(
            token,
            method,
            body
        )
        print("\n\n$method: $res\n\n")
        var returnResult:T? = null
        runCatching {
            returnResult = Json {
                ignoreUnknownKeys = true
            }.decodeFromString(res)
        }.onFailure {
            println("\n\nerror: ${it.message}\n\n")
        }
        return returnResult
    }
    private suspend inline fun <reified T:Any> callWithMap(method:String, params:List<Pair<String,Any>>):T?{
        val res = repo.callMethodWithMap(
            token,
            method,
            params
        )
        print("\n\n$method: $res\n\n")
        var returnResult:T? = null
        runCatching {
            returnResult = Json {
                ignoreUnknownKeys = true
            }.decodeFromString(res)
        }.onFailure {
            println("\n\nerror: ${it.message}\n\n")
        }
        return returnResult
    }
    private suspend fun callWithoutSerialize(method: String, body: Any?): String {
        return repo.callMethod(
            token,
            method,
            body
        )
    }
}