
package com.skydovesxyh.ivyai.core.network.service

import com.skydovesxyh.ivyai.core.model.network.GPTChatRequest
import com.skydovesxyh.ivyai.core.model.network.GPTChatResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatGPTService {

  @POST("v1/chat/completions")
  suspend fun sendMessage(@Body request: GPTChatRequest): ApiResponse<GPTChatResponse>
}
