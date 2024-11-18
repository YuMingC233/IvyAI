
package com.skydovesxyh.ivyai.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GPTMessage(
  @field:Json(name = "role") val role: String,
  @field:Json(name = "content") val content:  List<Content>
)


@JsonClass(generateAdapter = true)
data class Content(
  @Json(name = "type") val type: String,
  @Json(name = "text") val text: String? = null,
  @Json(name = "image_url") val imageUrl: ImageUrl? = null
)

@JsonClass(generateAdapter = true)
data class ImageUrl(
  @Json(name = "url") val url: String,
  @Json(name = "detail") val detail: String
) {
  // 伴生对象创建常量
  companion object {
    const val DETAIL_LOW = "low"
    const val DETAIL_AUTO = "auto"
    const val DETAIL_HIGH = "high"
  }
}