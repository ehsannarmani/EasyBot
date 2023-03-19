package com.github.ehsannarmani

import com.fasterxml.jackson.core.type.TypeReference
import com.github.ehsannarmani.model.*
import com.github.ehsannarmani.model.database.UserData
import com.github.ehsannarmani.model.message.*
import com.github.ehsannarmani.model.message.keyboard.Keyboard
import com.github.ehsannarmani.model.message.keyboard.inline.InlineKeyboardItem
import com.github.ehsannarmani.model.message.keyboard.reply.ChatAdministratorRights
import com.github.ehsannarmani.model.message.keyboard.reply.ReplyKeyboardItem
import com.github.ehsannarmani.model.method.*
import com.github.ehsannarmani.model.method.CallbackQuery
import com.github.ehsannarmani.model.method.Game
import com.github.ehsannarmani.model.method.Invoice
import com.github.ehsannarmani.model.method.ShippingQuery
import com.github.ehsannarmani.model.method.command.AddCommands
import com.github.ehsannarmani.model.method.command.BotCommand
import com.github.ehsannarmani.model.method.command.Commands
import com.github.ehsannarmani.model.method.inline_query.AnswerInlineQuery
import com.github.ehsannarmani.model.result.*
import com.github.ehsannarmani.model.update.*
import com.github.ehsannarmani.model.update.Photo
import com.github.ehsannarmani.model.updating_messages.*
import com.github.ehsannarmani.plugins.configureBot
import com.github.ehsannarmani.repository.BotRepo
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File
import java.lang.reflect.ParameterizedType
import java.nio.file.Paths


class Bot(
    private val token: String,
    private val onUpdate: suspend Bot.(Update) -> Unit = {},
    private val onTextUpdate: Bot.(String) -> Unit = {},
    private val onErrorThrown: Bot.(Throwable) -> Unit = {},
) : KoinComponent {

    private val repo: BotRepo by inject()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val _update: MutableSharedFlow<Update?> = MutableSharedFlow()
    val update = _update.asSharedFlow()
    var lastUpdate: Update? = null


    private val inlineKeyboardCollects = mutableListOf<String>()
    private val replyKeyboardCollects = mutableListOf<String>()

    init {
        setupKoin()
    }


    fun launch(
        post: Int = 3001,
        host: String = "127.0.0.1",
        webhookUrl: String = host,
        dropPendingUpdates: Boolean = true
    ) {
        val repo: BotRepo by inject()
        setWebhook(
            botRepo = repo,
            token = token,
            url = webhookUrl,
            dropPendingUpdates = dropPendingUpdates
        )

        embeddedServer(Netty, host = host, port = post) {
            configureBot(onUpdate = { update ->
                handleUpdate(update)
            }, onErrorThrown = {
                onErrorThrown(this@Bot, it)
            }, onTextUpdate = {
                onTextUpdate(this@Bot, it)
            })
        }.start(wait = true)
    }

    private fun handleUpdate(update: Update) {
        lastUpdate = update
        coroutineScope.launch {
            _update.emit(update)
            onUpdate(this@Bot, update)
        }
    }

    fun startPolling(timeout:Int = 60){
        val repo: BotRepo by inject()
        runBlocking(Dispatchers.IO) {
            println("\ncoroutine")
            runCatching {
                if (lastUpdate != null){
                    repo.getUpdates(
                        token = token,
                        offset = (lastUpdate!!.updateId +1).toString(),
                        timeout = timeout
                    )
                }else{
                    repo.getUpdates(
                        token = token,
                        timeout = timeout
                    )
                }
            }.onFailure {
                println("\nonFailure ${it.message}")
                startPolling(timeout)
            }.onSuccess {
                println("\nonSuccess :$it")
                runCatching {
                    Json{
                        ignoreUnknownKeys = true
                    }.decodeFromString<Result<List<Update>>>(it)
                }
                    .onSuccess{
                        println("\n update success")
                        it.result?.forEach {
                            handleUpdate(it)
                        }
                    }
                    .onFailure{
                        println("\n update error: ${it.message}")
                        onErrorThrown(this@Bot,it)
                    }
                startPolling()
            }
        }
    }


    /**
     * Use method for saving data in any types for user.
     *
     * If you savin classes as data, you must mark class as @Serializable annotation
     */
    inline fun <reified T> From.put(key:String, data: T){
        put(UserData(
            user = id,
            key = key,
            data = data
        ))
    }

    /**
     * Use method for getting last saved data with key in type for user.
     *
     * For example: if you saved two string data with 'name' key, it gives to you last saved item
     */
    inline fun <reified T> From.get(key:String):T?{
        return get<T>(
            user = id,
            key = key
        )?.data
    }

    /**
     * Use this method for get total data saved in a type
     */
    inline fun <reified T> From.getData():List<T>{
        return getAllData<T>(
            user = this.id,
        ).map { it.data }
    }

    /**
     * Use this method for get total data saved in a type
     */
    inline fun <reified T> get():List<T>{
        return getAllData<T>().map { it.data }
    }

    /**
     * Use this method for get list of data saved with some keys in a type
     *
     * For example : if you saved two strings with 'name' key, it gives to you two items with 'name' key
     */
    inline fun <reified T> From.getDataList(key:String):List<T>{
        return getAllData<T>(
            user = this.id,
            key = key
        ).map { it.data }
    }

    /**
     * Use this method for edit saved data with some keys in type
     *
     * For example : if you saved two strings with 'name' key, if you will edit that, last item will be edited
     *
     */
    inline fun <reified T> From.editData(key:String,newData:T){
        editData(
            newData = UserData(
                user = this.id,
                key = key,
                data = newData
            ),
        )
    }

    /**
     * Use this method for delete saved data with some keys in type
     */
    inline fun <reified T> From.deleteData(key:String){
        deleteData<T>(
            user = this.id,
            key = key
        )
    }

    /**
     * Use this method for delete all saved data in type for user
     */
    inline fun <reified T> From.deleteData(){
        deleteAllData<T>(
            user = this.id,
        )
    }

    /**
     * Use this method to delete message
     */

    suspend fun Message?.delete() {
        deleteMessage(
            chatId = this?.chat?.id.toString(),
            messageId = this?.messageId ?: 0
        )
    }

    /**
     * Use this method to register user data
     */
    fun From.register(){
        if (get<From>("self") == null){
            put("self",this)
        }
    }

    /**
     * Use this method when you want registered user delete, and register again
     */
    fun From.reRegister(){
        deleteData<From>("self")
        register()
    }


    /**
     * Use this method to get registered data
     */
    fun From.getSelf():From?{
        return get("self")
    }

    /**
     * Use this method to get all of registered users
     */
    fun getUsers():List<From>{
        return get()
    }


    /**
     * Use this method to answer the callback query
     *
     * Note: if you want to answer in alert dialog, pass true to alert field
     */
    suspend fun com.github.ehsannarmani.model.update.CallbackQuery?.answer(
        text: String,
        alert: Boolean = false,
        url: String? = null
    ) {
        answerCallbackQuery(
            CallbackQuery(
                id = this?.id.toString(),
                text = text,
                showAlert = alert,
                url = url
            )
        )
    }

    /**
     * Use this method on message to edit that
     */
    suspend fun Message?.editText(text: String, parseMode: String = "markdown", keyboard: Keyboard? = null) {
        editMessageText(
            EditTextMessage(
                chatId = this?.chat?.id.toString(),
                messageId = this?.messageId ?: 0,
                text = text,
                parseMode = parseMode,
                keyboard = keyboard

            )
        )
    }

    /**
     * Use this method on message to edit that message keyboard
     */
    suspend fun Message?.editKeyboard(newKeyboard: Keyboard) {
        editMessageReplyMarkup(
            MessageReplyMarkup(
                chatId = this?.chat?.id.toString(),
                messageId = this?.messageId ?: 0,
                keyboard = newKeyboard,
            )
        )
    }


    /**
     * Use this method on message to reply on message
     */
    suspend fun Message?.reply(
        text: String,
        keyboard: Keyboard? = null,
    ) {
        sendMessage(
            TextMessage(
                chatId = this?.chat?.id.toString(),
                text = text,
                replyToMessageId = this?.messageId ?: 0,
                keyboard = keyboard,

                )
        )
    }

    /**
     * Use this method to reply on last message received
     */
    suspend fun reply(
        text: String,
        keyboard: Keyboard? = null,
    ) {
        lastUpdate?.message?.reply(text, keyboard)
    }

    /**
     * Use this method to listen text messages
     */
    suspend fun onText(block: suspend (String) -> Unit) {
        if (lastUpdate?.message?.text != null) {
            block(lastUpdate?.message?.text ?: "")
        }
    }

    /**
     * Use this method to listen text message you want
     *
     * Example: if you want to listen on 'hello' message you can use it like: onText("hello"){ ... }
     */
    suspend fun onText(filter:String,block: suspend (String) -> Unit) {
        if (lastUpdate?.message?.text == filter) {
            block(lastUpdate?.message?.text ?: "")
        }
    }

    /**
     * Use this method to listen photo messages
     */
    suspend fun onPhoto(block: suspend (List<Photo>) -> Unit) {
        if (lastUpdate?.message?.photo != null) {
            block(lastUpdate?.message?.photo ?: listOf())
        }
    }

    /**
     * Use this method to listen sticker messages
     */
    suspend fun onSticker(block: suspend (Sticker) -> Unit) {
        if (lastUpdate?.message?.sticker != null) {
            block(lastUpdate?.message?.sticker!!)
        }
    }

    /**
     * Use this method to listen animation messages
     */
    suspend fun onAnimation(block: suspend (Animation) -> Unit) {
        if (lastUpdate?.message?.animation != null) {
            block(lastUpdate?.message?.animation!!)
        }
    }

    /**
     * Use this method to listen document messages
     */
    suspend fun onDocument(block: suspend (Document) -> Unit) {
        if (lastUpdate?.message?.document != null) {
            block(lastUpdate?.message?.document!!)
        }
    }


    /**
     * Use this method to listen audio messages
     */
    suspend fun onAudio(block: suspend (Audio) -> Unit) {
        if (lastUpdate?.message?.audio != null) {
            block(lastUpdate?.message?.audio!!)
        }
    }

    /**
     * Use this method to listen voice messages
     */
    suspend fun onVoice(block: suspend (Voice) -> Unit) {
        if (lastUpdate?.message?.voice != null) {
            block(lastUpdate?.message?.voice!!)
        }
    }

    /**
     * Use this method to listen video messages
     */
    suspend fun onVideo(block: suspend (Video) -> Unit) {
        if (lastUpdate?.message?.video != null) {
            block(lastUpdate?.message?.video!!)
        }
    }

    /**
     * Use this method to listen video note messages(circle video messages)
     */
    suspend fun onVideoNote(block: suspend (VideoNote) -> Unit) {
        if (lastUpdate?.message?.videoNote != null) {
            block(lastUpdate?.message?.videoNote!!)
        }
    }

    /**
     * Use this method to listen poll messages
     */
    suspend fun onPoll(block: suspend (Poll) -> Unit) {
        if (lastUpdate?.message?.poll != null) {
            block(lastUpdate?.message?.poll!!)
        }
    }

    /**
     * Use this method to listen contact messages
     */
    suspend fun onContact(block: suspend (Contact) -> Unit) {
        if (lastUpdate?.message?.contact != null) {
            block(lastUpdate?.message?.contact!!)
        }
    }

    /**
     * Use this method to listen location messages
     */
    suspend fun onLocation(block: suspend (Location) -> Unit) {
        if (lastUpdate?.message?.location != null) {
            block(lastUpdate?.message?.location!!)
        }
    }

    /**
     * Use this method to listen venue messages(a type of location)
     */
    suspend fun onVenue(block: suspend (Venue) -> Unit) {
        if (lastUpdate?.message?.venue != null) {
            block(lastUpdate?.message?.venue!!)
        }
    }

    /**
     * Use this method to listen invoice messages
     */
    suspend fun onInvoice(block: suspend (com.github.ehsannarmani.model.update.Invoice) -> Unit) {
        if (lastUpdate?.message?.invoice != null) {
            block(lastUpdate?.message?.invoice!!)
        }
    }

    /**
     * Use this method to listen game messages
     */
    suspend fun onGame(block: suspend (com.github.ehsannarmani.model.update.Game) -> Unit) {
        if (lastUpdate?.message?.game != null) {
            block(lastUpdate?.message?.game!!)
        }
    }

    /**
     * Use this method to listen callback queries(inline keyboard clicks)
     */
    suspend fun onCallbackQuery(block: suspend (com.github.ehsannarmani.model.update.CallbackQuery) -> Unit) {
        if (lastUpdate?.callbackQuery != null) {
            block(lastUpdate?.callbackQuery!!)
        }
    }
    /**
     * Use this method to listen callback query you want
     *
     * Example: if you want to listen on 'close' callback queries, use it like this: onCallbackQuery("close"){ ... }
     */
    suspend fun onCallbackQuery(filter:String,block: suspend (com.github.ehsannarmani.model.update.CallbackQuery) -> Unit) {
        if (lastUpdate?.callbackQuery != null) {
            if(lastUpdate?.callbackQuery?.data == filter){
                block(lastUpdate?.callbackQuery!!)
            }
        }
    }

    /**
     * Use this method to listen inline queries
     */
    suspend fun onInlineQuery(block: suspend (InlineQuery) -> Unit) {
        if (lastUpdate?.inlineQuery != null) {
            block(lastUpdate?.inlineQuery!!)
        }
    }

    /**
     * Use this method to listen all messages
     */
    suspend fun onMessage(block: suspend (Message) -> Unit) {
        if (lastUpdate?.message != null) {
            block(lastUpdate?.message!!)
        }
    }


    /**
     * Use this method on inline keyboard items to listen to clicking by users
     */
    suspend fun InlineKeyboardItem.onCLick(onClick:suspend (com.github.ehsannarmani.model.update.CallbackQuery)->Unit):InlineKeyboardItem{
        callbackData?.let {
            coroutineScope.launch {
                if (this@onCLick.callbackData !in inlineKeyboardCollects){
                    inlineKeyboardCollects.add(this@onCLick.callbackData)
                    _update.collect{
                        if(it?.callbackQuery?.data == this@onCLick.callbackData){
                            onClick(it.callbackQuery)
                        }
                    }
                }
            }
        }
        return this
    }

    /**
     * Use this method on reply keyboard items to listen to sending by users
     */
    suspend fun ReplyKeyboardItem.onCLick(onClick:suspend (Message)->Unit): ReplyKeyboardItem {
        coroutineScope.launch {
            if (this@onCLick.text !in replyKeyboardCollects){
                replyKeyboardCollects.add(this@onCLick.text)
                update.collect{
                    if(it?.message?.text == this@onCLick.text){
                        onClick(it.message)
                    }
                }
            }
        }
        return this
    }


    suspend fun getMe(): Result<Me>? {
        return call("getMe", null)
    }

    suspend fun sendMessage(message: TextMessage): Result<Message>? {
        return call("sendMessage", message)
    }

    suspend fun forwardMessage(message: ForwardMessage): Result<Message>? {
        return call("forwardMessage", message)
    }

    suspend fun copyMessage(message: CopyMessage): Result<MessageId>? {
        return call("copyMessage", message)
    }

    suspend fun sendPhoto(message: PhotoMessage): Result<Message>? {
        return call("sendPhoto", message)
    }

    suspend fun sendAudio(message: AudioMessage): Result<Message>? {
        return call("sendAudio", message)
    }

    suspend fun sendDocument(message: DocumentMessage): Result<Message>? {
        return call("sendDocument", message)
    }

    suspend fun sendVideo(message: VideoMessage): Result<Message>? {
        return call("sendVideo", message)
    }

    suspend fun sendAnimation(message: AnimationMessage): Result<Message>? {
        return call("sendAnimation", message)
    }

    suspend fun sendVoice(message: VoiceMessage): Result<Message>? {
        return call("sendVoice", message)
    }

    suspend fun sendVideoNote(message: VideoNoteMessage): Result<Message>? {
        return call("sendVideoNote", message)
    }

    suspend fun sendMediaGroup(message: MediaGroupMessage): Result<List<Message>>? {
        return call("sendMediaGroup", message)
    }

    suspend fun sendLocation(message: LocationMessage): Result<Message>? {
        return call("sendLocation", message)
    }

    suspend fun sendVenue(message: VenueMessage): Result<Message>? {
        return call("sendVenue", message)
    }

    suspend fun sendContact(message: ContactMessage): Result<Message>? {
        return call("sendContact", message)
    }

    suspend fun sendPoll(message: PollMessage): Result<Message>? {
        return call("sendPoll", message)
    }

    suspend fun sendDice(message: DiceMessage): Result<Message>? {
        return call("sendDice", message)
    }

    suspend fun sendChatAction(action: ChatAction): Result<Boolean>? {
        return call("sendChatAction", action)
    }

    suspend fun getUserProfilePhotos(user: UserProfilePhotos): Result<ProfilePhotos>? {
        return call("getUserProfilePhotos", user)
    }

    suspend fun getFile(fileId: String): DownloadFile {
        val file: Result<FilePath>? = callWithMap("getFile", listOf("file_id" to fileId))
        return DownloadFile(
            result = file,
            fileLink = "https://api.telegram.org/file/bot$token/${file?.result?.filePath}"
        )
    }

    suspend fun banChatMember(member: BanChatMember): Result<Boolean>? {
        return call("banChatMember", member)
    }

    suspend fun unbanChatMember(member: UnBanChatMember): Result<Boolean>? {
        return call("unbanChatMember", member)
    }

    suspend fun restrictChatMember(member: RestrictChatMember): Result<Boolean>? {
        return call("restrictChatMember", member)
    }

    suspend fun promoteChatMember(member: PromoteChatMember): Result<Boolean>? {
        return call("promoteChatMember", member)
    }

    suspend fun setChatAdminCustomTitle(title: ChatAdminCustomTitle): Result<Boolean>? {
        return call("setChatAdministratorCustomTitle", title)
    }

    suspend fun banChatSenderChat(senderChat: ChatSenderChat): Result<Boolean>? {
        return call("banChatSenderChat", senderChat)
    }

    suspend fun unbanChatSenderChat(senderChat: ChatSenderChat): Result<Boolean>? {
        return call("unbanChatSenderChat", senderChat)
    }

    suspend fun setChatPermissions(chat: ChangeChatPermissions): Result<Boolean>? {
        return call("setChatPermissions", chat)
    }

    suspend fun exportChatInviteLink(chatId: String): Result<String>? {
        return callWithMap("exportChatInviteLink", listOf("chat_id" to chatId))
    }

    suspend fun createChatInviteLink(link: ChatInviteLink): Result<InviteLink>? {
        return call("createChatInviteLink", link)
    }

    suspend fun editChatInviteLink(link: ChatInviteLink): Result<InviteLink>? {
        return call("editChatInviteLink", link)
    }

    suspend fun revokeChatInviteLink(chatId: String, inviteLink: String): Result<InviteLink>? {
        return callWithMap(
            "revokeChatInviteLink", listOf(
                "chat_id" to chatId,
                "invite_link" to inviteLink
            )
        )
    }

    suspend fun approveChatJoinRequest(chatId: String, userId: Long): Result<Boolean>? {
        return callWithMap(
            "approveChatJoinRequest", listOf(
                "chat_id" to chatId,
                "user_id" to userId
            )
        )
    }

    suspend fun declineChatJoinRequest(chatId: String, userId: Long): Result<Boolean>? {
        return callWithMap(
            "declineChatJoinRequest", listOf(
                "chat_id" to chatId,
                "user_id" to userId
            )
        )
    }

    suspend fun setChatPhoto(chatId: String, photo: File): Result<Boolean>? {
        return callWithMap(
            "setChatPhoto", listOf(
                "chat_id" to chatId,
                "photo" to photo
            )
        )
    }

    suspend fun deleteChatPhoto(chatId: String): Result<Boolean>? {
        return callWithMap(
            "deleteChatPhoto", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun setChatTitle(chatId: String, title: String): Result<Boolean>? {
        return callWithMap(
            "setChatTitle", listOf(
                "chat_id" to chatId,
                "title" to title
            )
        )
    }

    suspend fun setChatDescription(chatId: String, description: String): Result<Boolean>? {
        return callWithMap(
            "setChatDescription", listOf(
                "chat_id" to chatId,
                "description" to description
            )
        )
    }

    suspend fun pinChatMessage(chatId: String, messageId: Int, disableNotification: Boolean = false): Result<Boolean>? {
        return callWithMap(
            "pinChatMessage", listOf(
                "chat_id" to chatId,
                "message_id" to messageId,
                "disable_notification" to disableNotification
            )
        )
    }

    suspend fun unpinChatMessage(chatId: String, messageId: Int): Result<Boolean>? {
        return callWithMap(
            "unpinChatMessage", listOf(
                "chat_id" to chatId,
                "message_id" to messageId,
            )
        )
    }

    suspend fun unpinAllChatMessages(chatId: String): Result<Boolean>? {
        return callWithMap(
            "unpinAllChatMessages", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun leaveChat(chatId: String): Result<Boolean>? {
        return callWithMap(
            "leaveChat", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun getChat(chatId: String): Result<Chat>? {
        return callWithMap(
            "getChat", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun getChatAdministrators(chatId: String): Result<List<ChatMember>>? {
        return callWithMap(
            "getChatAdministrators", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun getChatMemberCount(chatId: String): Result<Int>? {
        return callWithMap(
            "getChatMemberCount", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun getChatMember(chatId: String, userId: Long): Result<ChatMember>? {
        return callWithMap(
            "getChatMember", listOf(
                "chat_id" to chatId,
                "user_id" to userId
            )
        )
    }

    suspend fun setChatStickerSet(chatId: String, stickerSetName: String): Result<Boolean>? {
        return callWithMap(
            "setChatStickerSet", listOf(
                "chat_id" to chatId,
                "sticker_set_name" to stickerSetName
            )
        )
    }

    suspend fun deleteChatStickerSet(chatId: String): Result<Boolean>? {
        return callWithMap(
            "deleteChatStickerSet", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun getForumTopicIconStickers(): Result<List<TopicSticker>>? {
        return call("getForumTopicIconStickers", null)
    }

    suspend fun createForumTopic(topic: ForumTopic): Result<Topic>? {
        return call("createForumTopic", topic)
    }

    suspend fun editForumTopic(topic: ForumTopic): Result<Boolean>? {
        return call("editForumTopic", topic)
    }

    suspend fun closeForumTopic(chatId: String, messageThreadId: Int): Result<Boolean>? {
        return callWithMap(
            "closeForumTopic", listOf(
                "chat_id" to chatId,
                "message_thread_id" to messageThreadId
            )
        )
    }

    suspend fun reopenForumTopic(chatId: String, messageThreadId: Int): Result<Boolean>? {
        return callWithMap(
            "reopenForumTopic", listOf(
                "chat_id" to chatId,
                "message_thread_id" to messageThreadId
            )
        )
    }

    suspend fun deleteForumTopic(chatId: String, messageThreadId: Int): Result<Boolean>? {
        return callWithMap(
            "deleteForumTopic", listOf(
                "chat_id" to chatId,
                "message_thread_id" to messageThreadId
            )
        )
    }

    suspend fun unpinAllForumTopicMessages(chatId: String, messageThreadId: Int): Result<Boolean>? {
        return callWithMap(
            "unpinAllForumTopicMessages", listOf(
                "chat_id" to chatId,
                "message_thread_id" to messageThreadId
            )
        )
    }

    suspend fun editGeneralForumTopic(chatId: String, name: String): Result<Boolean>? {
        return callWithMap(
            "editGeneralForumTopic", listOf(
                "chat_id" to chatId,
                "name" to name
            )
        )
    }

    suspend fun closeGeneralForumTopic(chatId: String): Result<Boolean>? {
        return callWithMap(
            "closeGeneralForumTopic", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun reopenGeneralForumTopic(chatId: String): Result<Boolean>? {
        return callWithMap(
            "reopenGeneralForumTopic", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun unhideGeneralForumTopic(chatId: String): Result<Boolean>? {
        return callWithMap(
            "unhideGeneralForumTopic", listOf(
                "chat_id" to chatId,
            )
        )
    }

    suspend fun answerCallbackQuery(callbackQuery: CallbackQuery): Result<Boolean>? {
        return call("answerCallbackQuery", callbackQuery)
    }

    suspend fun setMyCommands(commands: AddCommands): Result<Boolean>? {
        return call("setMyCommands", commands)
    }

    suspend fun deleteMyCommands(commands: Commands = Commands()): Result<Boolean>? {
        return call("deleteMyCommands", commands)
    }

    suspend fun getMyCommands(commands: Commands = Commands()): Result<List<BotCommand>>? {
        return call("getMyCommands", commands)
    }

    suspend fun setMyDescription(description: String, languageCode: String = "en"): Result<Boolean>? {
        return callWithMap(
            "setMyDescription", listOf(
                "description" to description,
                "language_code" to languageCode
            )
        )
    }

    suspend fun getMyDescription(languageCode: String = "en"): Result<Description>? {
        return callWithMap(
            "getMyDescription", listOf(
                "language_code" to languageCode
            )
        )
    }

    suspend fun setMyShortDescription(shortDescription: String, languageCode: String = "en"): Result<Boolean>? {
        return callWithMap(
            "setMyShortDescription", listOf(
                "short_description" to shortDescription,
                "language_code" to languageCode
            )
        )
    }

    suspend fun getMyShortDescription(languageCode: String = "en"): Result<ShortDescription>? {
        return callWithMap(
            "getMyShortDescription", listOf(
                "language_code" to languageCode
            )
        )
    }

    suspend fun setChatMenuButton(chatMenuButton: ChatMenuButton): Result<Boolean>? {
        return call("setChatMenuButton", chatMenuButton)
    }

    suspend fun getChatMenuButton(chatId: Int): Result<MenuButton>? {
        return call("getChatMenuButton", listOf("chat_id" to chatId))
    }

    suspend fun getChatMenuButton(): Result<MenuButton>? {
        return call("getChatMenuButton", null)
    }

    suspend fun setMyDefaultAdministratorRights(defaultAdministratorRights: DefaultAdministratorRights): Result<Boolean>? {
        return call("setMyDefaultAdministratorRights", defaultAdministratorRights)
    }

    suspend fun getMyDefaultAdministratorRights(forChannels: Boolean): Result<ChatAdministratorRights>? {
        return callWithMap("getMyDefaultAdministratorRights", listOf("for_channels" to forChannels))
    }

    suspend fun getMyDefaultAdministratorRights(): Result<ChatAdministratorRights>? {
        return call("getMyDefaultAdministratorRights", null)
    }

    suspend fun editMessageText(message: EditTextMessage): Result<Message>? {
        return call("editMessageText", message)
    }

    suspend fun editMessageCaption(caption: MessageCaption): Result<Message>? {
        return call("editMessageCaption", caption)
    }

    suspend fun editMessageMedia(media: MessageMedia): Result<Message>? {
        return call("editMessageMedia", media)
    }

    suspend fun editMessageLiveLocation(liveLocation: MessageLiveLocation): Result<Message>? {
        return call("editMessageLiveLocation", liveLocation)
    }

    suspend fun stopMessageLiveLocation(stopLiveLocation: StopMessageLiveLocation): Result<Message>? {
        return call("stopMessageLiveLocation", stopLiveLocation)
    }

    suspend fun editMessageReplyMarkup(message: MessageReplyMarkup): Result<Message>? {
        return call("editMessageReplyMarkup", message)
    }

    suspend fun stopPoll(poll: MessageReplyMarkup): Result<Poll>? {
        return call("stopPoll", poll)
    }

    suspend fun deleteMessage(chatId: String, messageId: Int): Result<Boolean>? {
        return callWithMap(
            "deleteMessage", listOf(
                "chat_id" to chatId,
                "message_id" to messageId
            )
        )
    }

    suspend fun sendSticker(sticker: StickerMessage): Result<Message>? {
        return call("sendSticker", sticker)
    }

    suspend fun getStickerSet(name: String): Result<StickerSet>? {
        return callWithMap("getStickerSet", listOf("name" to name))
    }

    suspend fun getCustomEmojiStickers(customEmojiStickers: CustomEmojiStickers): Result<List<TopicSticker>>? {
        return call("getCustomEmojiStickers", customEmojiStickers)
    }

    suspend fun uploadStickerFile(
        userId: Long,
        sticker: File,
        stickerFormat: String
    ): Result<com.github.ehsannarmani.model.method.File>? {
        return callWithMap(
            "uploadStickerFile", listOf(
                "user_id" to userId,
                "sticker" to sticker,
                "stickerFormat" to stickerFormat
            )
        )
    }

    suspend fun createNewStickerSet(newStickerSet: NewStickerSet): Result<Boolean>? {
        return call("createNewStickerSet", newStickerSet)
    }

    suspend fun addStickerToSet(stickerToSet: StickerToSet): Result<Boolean>? {
        return call("addStickerToSet", stickerToSet)
    }

    suspend fun setStickerPositionInSet(sticker: String, position: Int): Result<Boolean>? {
        return callWithMap(
            "setStickerPositionInSet", listOf(
                "sticker" to sticker,
                "position" to position
            )
        )
    }

    suspend fun deleteStickerFromSet(sticker: String): Result<Boolean>? {
        return callWithMap(
            "deleteStickerFromSet", listOf(
                "sticker" to sticker,
            )
        )
    }

    suspend fun setStickerEmojiList(stickerEmojiList: StickerEmojiList): Result<Boolean>? {
        return call("setStickerEmojiList", stickerEmojiList)
    }

    suspend fun setStickerKeywords(stickerKeywords: StickerKeywords): Result<Boolean>? {
        return call("setStickerKeywords", stickerKeywords)
    }

    suspend fun setStickerMaskPosition(stickerMaskPosition: StickerMaskPosition): Result<Boolean>? {
        return call("setStickerMaskPosition", stickerMaskPosition)
    }

    suspend fun setStickerSetTitle(name: String, title: String): Result<Boolean>? {
        return callWithMap(
            "setStickerSetTitle", listOf(
                "name" to name,
                "title" to title,
            )
        )
    }

    suspend fun setStickerSetThumbnail(stickerThumbnail: StickerThumbnail): Result<Boolean>? {
        return call("setStickerSetThumbnail", stickerThumbnail)
    }

    suspend fun setCustomEmojiStickerSetThumbnail(name: String, customEmojiId: String): Result<Boolean>? {
        return callWithMap(
            "setCustomEmojiStickerSetThumbnail", listOf(
                "name" to name,
                "custom_emoji_id" to customEmojiId,
            )
        )
    }

    suspend fun setCustomEmojiStickerSetThumbnail(name: String): Result<Boolean>? {
        return callWithMap(
            "setCustomEmojiStickerSetThumbnail", listOf(
                "name" to name,
            )
        )
    }

    suspend fun deleteStickerSet(name: String): Result<Boolean>? {
        return callWithMap(
            "deleteStickerSet", listOf(
                "name" to name,
            )
        )
    }

    suspend fun answerInlineQuery(answer: AnswerInlineQuery): Result<Boolean>? {
        return call("answerInlineQuery", answer)
    }

    suspend fun sendInvoice(invoice: Invoice): Result<Message>? {
        return call("sendInvoice", invoice)
    }

    suspend fun createInvoiceLink(invoice: Invoice): Result<String>? {
        return call("createInvoiceLink", invoice)
    }

    suspend fun answerShippingQuery(shippingQuery: ShippingQuery): Result<Boolean>? {
        return call("answerShippingQuery", shippingQuery)
    }

    suspend fun answerPreCheckoutQuery(
        preCheckoutQueryId: String,
        ok: Boolean,
        errorMessage: String
    ): Result<Boolean>? {
        return callWithMap(
            "answerPreCheckoutQuery", listOf(
                "pre_checkout_query_id" to preCheckoutQueryId,
                "ok" to ok,
                "error_message" to errorMessage
            )
        )
    }

    suspend fun sendGame(game: Game): Result<Message>? {
        return call("sendGame", game)
    }

    suspend fun setGameScore(score: GameScore): Result<Boolean>? {
        return call("setGameScore", score)
    }

    suspend fun getGameHighScores(highScores: GameHighScores): Result<List<GameHighScore>>? {
        return call("getGameHighScores", highScores)
    }

    fun From.setStep(step:String){
        put("step",step)
    }
    fun From.getStep():String?{
        return get<String>("step")
    }
    fun From.deleteStep(){
        deleteData<String>("step")
    }

    private suspend inline fun <reified T : Any> call(method: String, body: Any?): T? {
        val res = repo.callMethod(
            token,
            method,
            body
        )
        print("\n\n$method: $res\n\n")
        var returnResult: T? = null
        runCatching {
            returnResult = Json {
                ignoreUnknownKeys = true
            }.decodeFromString(res)
        }.onFailure {
            println("\n\nerror: ${it.message}\n\n")
        }
        return returnResult
    }

    private suspend inline fun <reified T : Any> callWithMap(method: String, params: List<Pair<String, Any>>): T? {
        val res = repo.callMethodWithMap(
            token,
            method,
            params
        )
        print("\n\n$method: $res\n\n")
        var returnResult: T? = null
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

    inline fun <reified T>put(data:UserData<T>) {
        var path = Paths.get("").toAbsolutePath().toString() + "/database"
        val dbPath = buildDBPath<T>()
        path+= "\\"+dbPath.path
        File(path).also {
            if (!it.exists()) {
                it.mkdirs()
            }
            val dataFile = File("${path}/${dbPath.fileName}")
            if (!dataFile.exists()) {
                dataFile.createNewFile()
            }
            var content = dataFile.readText()
            var decoded = mutableListOf<UserData<T>>()
            if (content.isNotEmpty()) {
                decoded = Json.decodeFromString(content)
            }

            decoded.add(data)

            val encoded = Json.encodeToString(decoded)
            content = encoded
            dataFile.writeText(content)
        }
    }
    inline fun <reified T>editData(newData:UserData<T>) {
        var path = Paths.get("").toAbsolutePath().toString() + "/database"
        val dbPath = buildDBPath<T>()
        path+= "\\"+dbPath.path
        println("\n${path} - ${File(path).exists()}")
        File(path).also {
            if (!it.exists()) {
                it.mkdirs()
            }
            val dataFile = File("${path}/${dbPath.fileName}")
            if (!dataFile.exists()) {
                dataFile.createNewFile()
            }
            var content = dataFile.readText()
            var decoded = mutableListOf<UserData<T>>()
            if (content.isNotEmpty()) {
                decoded = Json.decodeFromString(content)
            }

            val find = decoded.filter { it.user == newData.user && it.key == newData.key }
            val finalFind = find.last()
            val findIndex = decoded.indexOf(finalFind)
            decoded[findIndex] = newData

            val encoded = Json.encodeToString(decoded)
            content = encoded
            dataFile.writeText(content)
        }
    }
    inline fun <reified T>deleteData(user: Long,key: String) {
        var path = Paths.get("").toAbsolutePath().toString() + "/database"
        val dbPath = buildDBPath<T>()
        path+= "\\"+dbPath.path
        File(path).also {
            if (!it.exists()) return
            val dataFile = File("${path}/${dbPath.fileName}")
            if (!dataFile.exists()) return
            var content = dataFile.readText()
            var decoded = mutableListOf<UserData<T>>()
            if (content.isNotEmpty()) {
                decoded = Json.decodeFromString(content)
            }

            decoded.removeIf { it.user ==user && it.key == key }

            val encoded = Json.encodeToString(decoded)
            content = encoded
            dataFile.writeText(content)
        }
    }
    inline fun <reified T>deleteAllData(user: Long) {
        var path = Paths.get("").toAbsolutePath().toString() + "/database"
        val dbPath = buildDBPath<T>()
        path+= "\\"+dbPath.path
        File(path).also {
            if (!it.exists()) return
            val dataFile = File("${path}/${dbPath.fileName}")
            if (!dataFile.exists()) return
            var content = dataFile.readText()
            var decoded = mutableListOf<UserData<T>>()
            if (content.isNotEmpty()) {
                decoded = Json.decodeFromString(content)
            }

            decoded.removeIf { it.user ==user}

            val encoded = Json.encodeToString(decoded)
            content = encoded
            dataFile.writeText(content)
        }
    }
    inline fun <reified T>get(user: Long, key: String): UserData<T>? {
        return getDecodedData<T>()?.lastOrNull { it.user == user && it.key == key }
    }
    inline fun <reified T>getAllData(user: Long): List<UserData<T>> {
        return getDecodedData<T>()?.filter { it.user == user} ?: listOf()
    }
    inline fun <reified T>getAllData(): List<UserData<T>> {
        return getDecodedData() ?: listOf()
    }
    inline fun <reified T>getAllData(user: Long,key: String): List<UserData<T>> {
        return getDecodedData<T>()?.filter { it.user == user && it.key == key} ?: listOf()
    }
    data class DBPath(
        val path: String,
        val fileName:String
    )

    inline fun <reified T> getDecodedData(): MutableList<UserData<T>>? {
        var path = Paths.get("").toAbsolutePath().toString() + "/database"
        val dbPath = buildDBPath<T>()
        path+= "\\"+dbPath.path
        File(path).also {
            if (!it.exists()) return null
            val data = File("${path}/${dbPath.fileName}")

            if (!data.exists()) return null
            var content = data.readText()
            var decoded = mutableListOf<UserData<T>>()
            if (content.isNotEmpty()) {
                decoded = Json.decodeFromString<List<UserData<T>>>(content).toMutableList()
            }
            return decoded
        }
    }
    inline fun <reified T> buildDBPath(): DBPath {
        val convertPackageNameToPath:(String?)->String = {
            it?.split(".")?.joinToString(separator = "\\") { it.lowercase() } ?: ""
        }
        val typeClass = T::class
        var path = convertPackageNameToPath(typeClass.qualifiedName)
        val type = object : TypeReference<T>() {}.type
        if (type is ParameterizedType){
            if (type.actualTypeArguments.count() == 1){
                path += "\\"+convertPackageNameToPath(type.actualTypeArguments.first().typeName)
            }else{
                throw Throwable("Only one generics supported in saving user data")
            }
        }
        val fileName = path.split("\\").last()
        val dir = path.replace("\\${fileName}","")
        return DBPath(
            path = dir,
            fileName = fileName+"_data.json",
        )
    }
}