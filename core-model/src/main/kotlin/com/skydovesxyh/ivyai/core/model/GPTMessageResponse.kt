package com.skydovesxyh.ivyai.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GPTMessageResponse(
  @field:Json(name = "role") val role: String,
  @field:Json(name = "content") val content: String
)