
package com.skydovesxyh.ivyai.core.model.network

import com.skydovesxyh.ivyai.core.model.GPTMessage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GPTChatRequest(
  @field:Json(name = "model") val model: String,
  @field:Json(name = "messages") val messages: List<GPTMessage>
)
