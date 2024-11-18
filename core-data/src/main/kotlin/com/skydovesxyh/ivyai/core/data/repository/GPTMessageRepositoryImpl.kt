
package com.skydovesxyh.ivyai.core.data.repository

import com.skydovesxyh.ivyai.core.model.network.GPTChatRequest
import com.skydovesxyh.ivyai.core.model.network.GPTChatResponse
import com.skydovesxyh.ivyai.core.network.ChatGPTDispatchers
import com.skydovesxyh.ivyai.core.network.Dispatcher
import com.skydovesxyh.ivyai.core.network.service.ChatGPTService
import com.skydoves.sandwich.ApiResponse
import io.getstream.chat.android.client.ChatClient
import io.getstream.result.onSuccessSuspend
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class GPTMessageRepositoryImpl @Inject constructor(
  @Dispatcher(ChatGPTDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
  private val chatClient: ChatClient,
  private val chatGptService: ChatGPTService
) : GPTMessageRepository {

  override suspend fun sendMessage(gptChatRequest: GPTChatRequest): ApiResponse<GPTChatResponse> {
    return chatGptService.sendMessage(gptChatRequest)
  }

  override fun watchIsChannelMessageEmpty(cid: String): Flow<Boolean> = flow {
    val result = chatClient.channel(cid).watch().await()
    result.onSuccessSuspend { channel ->
      val messages = channel.messages
      emit(messages.isEmpty())
    }
  }.flowOn(ioDispatcher)
}
