
package com.skydovesxyh.ivyai.core.model

import com.skydovesxyh.ivyai.core.model.GPTMessageResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GPTChoice(
  @field:Json(name = "index") val index: Int,
  @field:Json(name = "message") val message: GPTMessageResponse,
  @field:Json(name = "logprobs") val logProbs: String?,
  @field:Json(name = "finish_reason") val finishReason: String?
)
