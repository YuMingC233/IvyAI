
package com.skydovesxyh.ivyai.core.model.network

import com.skydovesxyh.ivyai.core.model.GPTChoice
import com.skydovesxyh.ivyai.core.model.GPTUsage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GPTChatResponse(
  @field:Json(name = "id") val id: String,
  @field:Json(name = "object") val `object`: String,
  @field:Json(name = "created") val created: Long,
  @field:Json(name = "model") val model: String,
  @field:Json(name = "system_fingerprint") val systemFingerprint: String,
  @field:Json(name = "choices") val choices: List<GPTChoice>,
  @field:Json(name = "usage") val usage: GPTUsage
)
