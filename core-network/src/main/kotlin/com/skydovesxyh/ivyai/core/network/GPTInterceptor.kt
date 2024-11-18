
package com.skydovesxyh.ivyai.core.network

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class GPTInterceptor @Inject constructor() : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalUrl = originalRequest.url
    val url = originalUrl.newBuilder().build()
    val requestBuilder = originalRequest.newBuilder().url(url).apply {
      addHeader("Authorization", "Bearer ${BuildConfig.GPT_API_KEY}")
    }
    val request = requestBuilder.build()
    return chain.proceed(request)
  }
}
