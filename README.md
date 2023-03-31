# Telegram Bot Library For Kotlin

### Build telegram bot easier than ever with this library!

<hr/>

## Headlines:

* ### [\[ Add Dependency \]](#dependency)
* ### [\[ Basic Usage \]](#basic-usage)
  * #### [\[ Startups \]](#types-of-startups)
  * #### [\[ Receiving Updates \]](#receiving-updates)
  * #### [\[ Update Types \]](#update-types)
  * #### [\[ Message Types \]](#message-types)
  * #### [\[ Error Handling \]](#error-handling)
  * #### [\[ Methods \]](#methods)
  * #### [\[ Keyboards \]](#keyboards)
  * #### [\[ InlineQueryResults \]](#inlinequeryresults)
* ### [\[ Easy Usage \]](#easy-usage)
  * #### [\[ Listeners \]](#listeners)
  * #### [\[ Helper Methods \]](#helper-methods)
  * #### [\[ DataStore \]](#datastore)
  * #### [\[ Steps \]](#steps)

## Dependency

### Step 1: Add the JitPack repository to your build file

#### Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

#### Kotlin DSL(build.gradle.kts):

```kotlin
    repositories {
        ...
        maven("https://jitpack.io")
    }   
```

## Step 2: Add the dependency

[![](https://jitpack.io/v/ehsannarmani/EasyBot.svg)](https://jitpack.io/#ehsannarmani/EasyBot)

#### Groovy:
```groovy
dependencies {
    implementation "com.github.ehsannarmani:EasyBot:latest_version"
}
```
#### Kotlin DSL (build.gradle.kts):
```groovy
dependencies {
    implementation("com.github.ehsannarmani:EasyBot:latest_version")
}
```

<hr/>

## Basic Usage

```kotlin
Bot(
    token = "put bot token here",
    onUpdate = {
        onText("test"){
            reply("lib working fine")
        }
    }
).startPolling()
```
#### Note: you can create bot in @BotFather and get that token.

## Types of startups:

#### 1. Use polling: In this type, you don't need to create web server and set webhook and ...
```kotlin
bot.startPolling(timeout = 60)
```
#### 2. Use Webhook: In this type, automatically webserver will create with Ktor and automaticlly bot will call setWebhook (you don't need to setWebhook)
```kotlin
bot.launch(
    port = 3000,
    host = "127.0.0.1",
    webhookUrl = "https://xxx.xx",
    dropPendingUpdates = true of false
)
```
##### webhookUrl: webhook url must be your webserver address, if you are using this in your local system, you can use <a href='https://ngrok.com/'>ngrok<a/> tool to get url from your local system like this:
```
ngrok http 3000
```
#### Note: You must enter the port that you entered in the launch method. it will give you address like: https://b114-94-131-98-78.eu.ngrok.io, use this address as webhookUrl in launch method
#### dropPendingUpdates: Also you can set this option for drop pending updates, if you set true for this param, pending updates in queue will not be considered and only new updates after launch will consider.
<hr/>

## Receiving Updates
### You can pass a function when making new instance from Bot like this: 
```kotlin
Bot(
    ...,
    onUpdate = { update->
        
    }
)
```
### to get updates!

#### For example if you want to get message sender user id, you can do like this:
```kotlin
Bot(
    ...,
    onUpdate = { update->
        println(update?.message?.from?.id)
    }
)   
```
#### Note: for using better updates, read [telegram bot document](https://core.telegram.org/bots/api)
### Also you can get text update with passing function to parameter **onTextUpdate** like this:
```kotlin
Bot(
    ...,
    onTextUpdate = { update->
        println(update)
    }
)   
```


### Also you can collect update shared flow from bot to get updates in other place of your code like this:
```kotlin
val bot = Bot(token = "...")
bot.update.collect { update->
    println(update?.message?.text)
}
```
#### Note: to get update type, see [this](#update-types) section.
#### Note: to get message type, see [this](#message-types) section.
## Error Handling
### You can pass function for parameter **onErrorThrown** when you making instance from Bot to get errors like: serialization error and others:
```kotlin
Bot(
    ...,
    onErrorThrown = { error->
        
    }
)
```

## Methods:
### You can call all methods API bots have, For example you send message with this code:
```kotlin
val bot = Bot(...)
bot.sendMessage(TextMessage(
    chatId = "@mrenk",
    text = "Hello!",
    parseMode = "markdown",
    ...
))
```
### Or sendPhoto:
```kotlin
val bot = Bot(...)
bot.sendPhoto(PhotoMessage(
    chatId = "@mrenk",
    photo = "photo link here",
    caption = "Hello!",
    ...
))
```
### And other methods!
#### Note: for information about methods you can read [telegram bot document](https://core.telegram.org/bots/api).

### You can use this methods in onUpdate,onTextUpdate,onErrorThrown functions without writing bot instance like this:
```kotlin
Bot(
    ...,
    onUpdate = { update->
        sendMessage(TextMessage(
            chatId = update?.message?.chat?.id,
            text = "Hello, bot working successfully"
        ))
        sendPhoto(PhotoMessage(
            chatId = update?.message?.chat?.id,
            photo = "..."
        ))
        ...
    }
)
```

## Keyboards
### You can send four types of keyboards: InlineKeyboard,ReplyKeyboard,ForceReply,RemoveKeyboard
#### InlineKeyboard: (Buttons under the message and glass)
```kotlin
bot.sendMessage(TextMessage(
    chatId = "@mrenk",
    text = "Hey!",
    keyboard = InlineKeyboard(
        keyboard = listOf(
            listOf(
                InlineKeyboardItem(text = "Key1",callbackData = "key1Data"),
                InlineKeyboardItem(text = "Key2",callbackData = "key2Data"),
            ),
            listOf(
                InlineKeyboardItem(text = "Url",url = "https://github.com/ehsannarmani/EasyBot"),
            )
        )
    )
))
```
#### Note: you can check callback query data to handle this keys click:
```kotlin
onUpdate = { update->
    if(update?.callbackQuery?.data == "key1Data"){
        println("Key1 clicked!") 
    }
}
```
#### ReplyKeyboard:
```kotlin
bot.sendMessage(TextMessage(
    chatId = "@mrenk",
    text = "Hey!",
    keyboard = ReplyKeyboard(
        keyboard = listOf(
            listOf(
                ReplyKeyboardItem(text = "Key1"),
                ReplyKeyboardItem(text = "Key2"),
            ),
            listOf(
                ReplyKeyboardItem(text = "Contact",requestContact = true),
            )
        ),
        resizeKeyboard = true,
    )
))
```
#### ForceReply (For replying user telegram client automatically on message):
```kotlin
bot.sendMessage(TextMessage(
    chatId = "@mrenk",
    text = "Hey!",
    keyboard = ForceReply()
))
```
#### RemoveKeyboard (For removing current reply keyboard):
```kotlin
bot.sendMessage(TextMessage(
    chatId = "@mrenk",
    text = "Hey!",
    keyboard = RemoveKeyboard()
))
```

## InlineQueryResults:
### You can send result in types:
* #### InlineQueryResultArticle
* #### InlineQueryResultAudio
* #### InlineQueryResultGif
* #### InlineQueryResultContact
* #### InlineQueryResultDocument
* #### InlineQueryResultGame
* #### InlineQueryResultPhoto
* #### InlineQueryResultVenue
* #### InlineQueryResultVideo
* #### InlineQueryResultVoice
* #### And some cached results (InlineQueryResultCachedAudio,Photo,...)

```kotlin
if (update.inlineQuery?.query == "hey"){
    answerInlineQuery(AnswerInlineQuery(
        inlineQueryId = update.inlineQuery?.id.toString(),
        results = listOf(
            InlineQueryResultArticle(
                title = "this is result",
                description = "this is description of result",
                isPersonal = true,
                ...
            )
        )
    ))
}else if (update.inlineQuery?.query == "hi"){
    answerInlineQuery(AnswerInlineQuery(
        inlineQueryId = update.inlineQuery?.id.toString(),
        results = listOf(
            InlineQueryResultPhoto(
                photoUrl = "url of photo",
                ...
            )
        )
    )) 
}
...
```
<hr/>

## Easy Usage
### Listeners:
#### 1.You can listen for messages with onMessage{ } method:
```kotlin
Bot(
    ...,
    onUpdate = { update->
        onMessage{ message->
            println("Any messages received!")
        }
    }
)
```
#### 2.You can listen for text messages with onText{ } method:
```kotlin
Bot(
    ...,
    onUpdate = { update->
        onText{ text->
            println("a text message received!")
        }
    }
)
```
##### Note: you can pass string for onText method to filter your listener:
```kotlin
onText("hello"){
    println("User sent to me hello")
}
```
#### 3.You can listen for commands with onCommand{ } method:
```kotlin
Bot(
    ...,
    onUpdate = { update->
        onCommand("start"){
            println("start command entered!")
        }
    }
)
```
#### Note: for default , this method call when messages contain command, for example if text entered '/start@EasyBot' this scope will call, if you dont want to this happen, you can pass false for second parameter of this method lile:
```kotlin
onCommand("start",false){
    
}
```
#### this works when text only is '/start'
#### 4.You can listen for all of other messages with their methods:
```kotlin
Bot(
    ...,
    onUpdate = { update->
        onPhoto{ photo->
            println("a photo message received!")
        }
        onVoice{ voice->
            println("a voice message received!")
        }
        onConatct{ contact->
            println("a contact message received!")
        }
        // and all of other message types
    }
)
```
#### 5.You can listen for callback queries with onCallbackQuery { } method:
```kotlin
Bot(
    ...,
    onUpdate = { update->
        onCallbackQuery{ callbackQuery->
            print("callback query received")
        }
    }
)
```
#### Note: You can listen for a callback query data instead writing 'if' and checking data:
```kotlin
onUpdate = { update->
    onCallbackQuery("key1Data"){
        println("key1Data pressed!")
    }
}
```
#### 6.You can listen for inline queries with onInlineQuery { } method:
```kotlin
Bot(
    ...,
    onUpdate = { update->
        onInlineQuery{ inlineQuery->
            print("inline query update received!")
        }
    }
)
```
#### Note: You can listen for a inline query - query instead writing 'if' and checking query:
```kotlin
onUpdate = {update->
    onInlineQuery("hi"){
        println("hi inline query typed!")
    }
}
```
<hr/>

## Helper Methods
### 1. reply
#### You can reply on sent message easier than calling sendMessage method
```kotlin
onUpdate = { update->
    onText("/start"){
        reply("Hey! You started bot.")
    }
}
```
### 2. delete
#### You can delete messages with calling delete method on that message instead calling deleteMessage method
```kotlin
onUpdate = { update->
    onCallbackQuery("key1Data"){ callbackQuery->
        callbackQuery.message.delete()
    }
    onText("Violent content"){
        update?.message?.delete()
    }
}
```
### 3. editText
#### You can edit text messages with calling editText method on that message instead calling editMessage method
```kotlin
onUpdate = { update->
    onCallbackQuery("key1Data"){ callbackQuery->
        callbackQuery.message.editText(text = "New message text")
    }
}
```
### 4. editKeyboard
#### You can edit messages keyboard with calling editKeyboard method on that message instead calling editMessage method
```kotlin
onUpdate = { update->
    onCallbackQuery("key1Data"){ callbackQuery->
        callbackQuery.message.editKeyboard(newKeyboard = InlineKeyboard(
            keyboard= listOf(
                listOf(
                    InlineKeyboardItem(text = "Key8", callbackData = "key8Data")
                )
            )
        ))
    }
}
```
### 5. answer
#### You can answer callback queries with calling answer method on callback query instead calling answerCallbackQuery method
```kotlin
onUpdate = { update->
    onCallbackQuery("key1Data"){ callbackQuery->
        callbackQuery.answer("Hey! you clicked on Key1 button!",alert= true)
    }
}
```
### 6. onClick (InlineKeyboards)
#### You can pass onClick to InlineKeyboardItems instead checking callback query data in onUpdate!
```kotlin
bot.sendMessage(TextMessage(
    chatId = "@mrenk",
    text = "Hey!",
    keyboard = InlineKeyboard(
        keyboard = listOf(
            listOf(
                InlineKeyboardItem(text = "Key1",callbackData = "key1Data").onClick{ callbackQuery->
                    callbackQuery.answer("You clicked Key1!")
                },
                InlineKeyboardItem(text = "Key2",callbackData = "key2Data").onClick{ 
                    // do something
                },
            )
        )
    )
))
```

### 7. onClick (ReplyKeyboards)
#### You can pass onClick to ReplyKeyboardItems instead checking text in onUpdate!
```kotlin
bot.sendMessage(TextMessage(
    chatId = "@mrenk",
    text = "Hey!",
    keyboard = ReplyKeyboard(
        keyboard = listOf(
            listOf(
                ReplyKeyboardItem(text = "Key1").onClick{ message->
                    reply("You clicked on Key1")
                },
                ReplyKeyboardItem(text = "Key2").onClick{ 
                    // do something
                },
            )
        )
    )
))
```

<hr/>

## DataStore
### 1. Put
#### You can save data in all of types for user
```kotlin
onUpdate = {
    onMessage {
        it.from.put(key = "foo",data = "this is data will saved in foo key")
        it.from.put(key = "age",data = 22)
        it.from.put(key = "grades",data = listOf(15,16,19,14))
    }
}
```
#### Note: You can save data classes! But you must use [Kotlinx Serialization](https://kotlinlang.org/docs/serialization.html) library and mark your data class as @Serializable
```kotlin
@Serializable
data class Student(@SerialName("name") val name:String,@SerialName("age") val age:Int)

@Serializable
data class Teacher<T>(@SerialName("name") val name:String, @SerialName("subset") val subset:T)

Bot(
    ...,
    onUpdate = {
        onMessage {
            it.from.put(key = "student",data = Student(name = "jax",age = 17))
            it.from.put(key = "teacher",data = Teacher(name = "mr. jace",subset = Student(name = "alaska",age = 18)))
        }
    }
)
```
#### Note: saving data classes supported 1 generics currently !

### 2. Get
#### You can get saved data for user
```kotlin
onUpdate = {
    onMessage {
        val stringData = it.from.get<String>(key = "foo")
        val integerData:Int = it.from.get(key = "age")
        val listData = it.from.get<List<Int>>(key = "grades")
        val classData = it.from.get<Student>(key = "student")
    }
}
```
#### Note: you must give your data type when you need to get it in <> , or specify your variable type
#### Note: you can get all of data saved in a type for user without specifying key:
```kotlin
onUpdate = {
    onMessage {
        val allStringDataSavedForUser = it.from.get<String>()
        allStringDataSavedForUser.forEach{
            val user = it.user
            val key = it.key
            val data = it.data
        }
    }
}
```
#### Note: you can get all of data saved in a type for all users:
```kotlin
val allStringsSaved = bot.get<String>()
```
### 3. Edit
#### You can edit saved data for user
```kotlin
onUpdate = {
    onMessage {
       it.from.editData(key = "foo", newData = "x")
       it.from.editData(key = "student", newData = Student(name = "alaska", age = 17))
    }
}
```
### 4. Delete
#### You can delete saved data for user
```kotlin
onUpdate = {
    onMessage {
       it.from.deleteData<String>(key = "foo")
       it.from.deleteData<Student>(key = "student")
    }
}
```
#### Note: you must give your data type when you need to delete it in <> , or specify your variable type

### Helpers
* #### Register: You can register user by calling this method on user:
```kotlin
onUpdate = { update->
    update.message?.from?.register()
}
```
* #### ReRegister: You can register user by calling this method on user(if user was already registered, it will delete that and register again)
```kotlin
onUpdate = { update->
    update.message?.from?.reRegister()
}
```
* #### GetSelf: You can get registered user in data store by calling this method on user
```kotlin
onUpdate = { update->
    val self = update.message?.from?.getSelf()
}
```
* #### GetUsers: You can get all users registered in datastore
```kotlin
onUpdate = { update->
    val usersList = update.message?.from?.getUsers()
    val usersCount = usersList.count()
    ...
}
```

<hr/>

## Steps

### 1. step
#### You can set step for user, using this method
```kotlin
onUpdate = { update ->
    onMessage { msg ->
        onCommand("start"){
            step("name")
            reply("send your name")
        }
    }
}
```
### 2. onStep
#### You can listen for sending message from user on some steps with this method
```kotlin
onUpdate = { update ->
    onMessage { msg ->
        onStep("name"){
            reply("name received, your name is: $it")
        }
    }
}
```
### With these two methods you can receive inputs from user steply, a code for example:
```kotlin
onUpdate = { update ->
    onMessage { msg ->
        onCommand("start"){
            step("name")
            reply("send your name")
        }
        onStep("name"){ enteredName->
            step("age")
            reply("your name received , send your age")
        }
        onStep("age"){ enteredAge->
            reply("your age received.")
        }
    }
}
```

<hr/>

## Update Types:
### You can get update types:
```kotlin
onUpdate = { update->
  when(update.updateType){
    UpdateType.Message-> println("Message Update Received")
    UpdateType.EditMessage-> println("A Message Edited")
    UpdateType.ChannelPost-> println("A Channel Posted Something")
    UpdateType.ChannelPostEdit -> println("A Channel Edited Some Posts")
    UpdateType.JoinRequest -> println("Join Request Update Received")
    UpdateType.InlineResult -> println("Inline Result Update Received")
    UpdateType.PreCheckout -> println("PreCheckout Update Received")
    UpdateType.Shipping -> println("Shipping Update Received")
    UpdateType.CallbackQuery -> println("Callback Query Update Received")
    UpdateType.InlineQuery -> println("Inline Query Update Received")
    UpdateType.ChatMember -> println("Chat Member Update Received")
    UpdateType.MyChatMember -> println("MyChatMember Update Received")
    UpdateType.PollAnswer -> println("Someone put answer for a poll")
    UpdateType.Poll -> println("Poll Update Received")
  }
}
```
## Message Types:
### You can get message types:
```kotlin
onUpdate = {
  onMessage { msg ->
    when(msg.messageType){
      MessageType.Text -> print("Text Message Received")
      MessageType.Photo -> print("Photo Message Received")
      MessageType.Audio -> print("Audio Message Received")
      MessageType.Voice -> print("Voice Message Received")
      MessageType.Document -> print("Document Message Received")
      MessageType.Gif -> print("Gif Message Received")
      MessageType.Sticker -> print("Sticker Message Received")
      MessageType.Poll -> print("Poll Message Received")
      MessageType.Game -> print("Game Message Received")
      MessageType.Invoice -> print("Invoice Message Received")
    }
  }
}
```




