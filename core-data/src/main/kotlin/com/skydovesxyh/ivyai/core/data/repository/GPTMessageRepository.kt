
package com.skydovesxyh.ivyai.core.data.repository

import com.skydovesxyh.ivyai.core.model.network.GPTChatRequest
import com.skydovesxyh.ivyai.core.model.network.GPTChatResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface GPTMessageRepository {

  suspend fun sendMessage(gptChatRequest: GPTChatRequest): ApiResponse<GPTChatResponse>

  fun watchIsChannelMessageEmpty(cid: String): Flow<Boolean>
}
