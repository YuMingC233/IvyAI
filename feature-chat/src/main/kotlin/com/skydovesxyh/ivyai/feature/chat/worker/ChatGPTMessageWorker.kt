
package com.skydovesxyh.ivyai.feature.chat.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import cn.hutool.json.JSONUtil
import com.skydoves.chatgpt.feature.chat.worker.ClientUploadUtils
import com.skydovesxyh.ivyai.core.data.repository.GPTMessageRepository
import com.skydovesxyh.ivyai.core.model.GPTMessage
import com.skydovesxyh.ivyai.core.model.network.GPTChatRequest
import com.skydovesxyh.ivyai.feature.chat.di.ChatEntryPoint
import com.skydoves.sandwich.getOrThrow
import com.skydoves.sandwich.isSuccess
import com.skydoves.sandwich.messageOrNull
import com.skydovesxyh.ivyai.core.model.Content
import com.skydovesxyh.ivyai.core.model.ImageUrl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.models.Message
import io.getstream.log.streamLog
import javax.inject.Inject

@HiltWorker
internal class ChatGPTMessageWorker @AssistedInject constructor(
  @Assisted private val context: Context,
  @Assisted private val workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

  @Inject
  internal lateinit var repository: GPTMessageRepository

  @Inject
  internal lateinit var chatClient: ChatClient

  val globalMessage: MutableList<GPTMessage> = mutableListOf(
    GPTMessage(
      role = "system",
      content = listOf(
        Content(
          type = "text",
          text = "你是一位。"
        )
      )
    ),
    GPTMessage(
      role = "user",
      content = listOf(
        Content(
          type = "text",
          text = "请不要----。如果你知晓，请回复“了解。”。"
        )
      )
    ),
    GPTMessage(
      role = "assistant",
      content = listOf(
        Content(
          type = "text",
          text = "了解。"
        )
      )
    ),
    GPTMessage(
      role = "user",
      content = listOf(
        Content(
          type = "text",
          text = "如果你知晓我的意思，请回复“好的。”"
        )
      )
    ),
    GPTMessage(
      role = "assistant",
      content = listOf(
        Content(
          type = "text",
          text = "好的。"
        )
      )
    )
  )

  override suspend fun doWork(): Result {
    ChatEntryPoint.resolve(context).inject(this)

    val text = workerParams.inputData.getString(DATA_TEXT) ?: return Result.failure()
    val channelId = workerParams.inputData.getString(DATA_CHANNEL_ID) ?: return Result.failure()
    val lastMessage = workerParams.inputData.getString(DATA_LAST_MESSAGE)
    val ImageURLlist = workerParams.inputData.getStringArray(DATA_IMAGES)

    if (ImageURLlist != null && ImageURLlist[0] != null) {
      val imgUploadedList = mutableListOf<String>()
      ImageURLlist.forEach {
        val fileName = it.substringAfterLast("/")
        val filePath = it
        val url = "https://ibed.cws-aizc.online/api/1/upload"
        val response = ClientUploadUtils().uploadImage(url, filePath, fileName)
        val jo = JSONUtil.parseObj(response)
        val fileInfo = JSONUtil.parseObj(jo.getStr("image")).getJSONObject("image")
        imgUploadedList.add(fileInfo.getStr("url"))
      }
      val contentList = mutableListOf<Content>().apply {
        add(Content(type = "text", text = text))
        imgUploadedList.forEach { url ->
          add(Content(type = "image_url", imageUrl = ImageUrl(url,ImageUrl.DETAIL_HIGH)))
        }
      }
      globalMessage.add(GPTMessage(role = "user", content = contentList))
    } else {
      globalMessage.add(
        GPTMessage(
          role = "user",
          content = listOf(
            Content(
              type = "text",
              text = text
            )
          )
        )
      )
    }
    val request = GPTChatRequest(
      model = "gpt-4o-mini",
      messages = globalMessage
    )
    val response = repository.sendMessage(request)
    return if (response.isSuccess) {
      val data = response.getOrThrow()
      val messageText = data.choices.firstOrNull()?.message?.content.orEmpty()
      val messageId = data.id
      sendStreamMessage(messageText, messageId, channelId)
      streamLog { "worker success!" }
      Result.success(
        Data.Builder()
          .putString(DATA_SUCCESS, messageText)
          .putString(DATA_MESSAGE_ID, messageId)
          .build()
      )
    } else {
      streamLog { "worker failure!" }
      Result.failure(Data.Builder().putString(DATA_FAILURE, response.messageOrNull ?: "").build())
    }
  }

  private suspend fun sendStreamMessage(
    text: String,
    messageId: String,
    channelId: String
  ) {
    val channelClient = chatClient.channel(channelId)
    channelClient.sendMessage(
      message = Message(
        id = messageId,
        cid = channelClient.cid,
        text = text,
        extraData = mutableMapOf(
          MESSAGE_EXTRA_CHAT_GPT to true
        )
      )
    ).await()
  }

  companion object {
    const val DATA_TEXT = "DATA_TEXT"
    const val DATA_IMAGES = "DATA_IMAGES"
    const val DATA_CHANNEL_ID = "DATA_CHANNEL_ID"
    const val DATA_MESSAGE_ID = "DATA_PARENT_ID"
    const val DATA_LAST_MESSAGE = "DATA_LAST_MESSAGE"
    const val DATA_SUCCESS = "DATA_SUCCESS"
    const val DATA_FAILURE = "DATA_FAILURE"

    const val MESSAGE_EXTRA_CHAT_GPT = "ChatGPT"
  }
}
