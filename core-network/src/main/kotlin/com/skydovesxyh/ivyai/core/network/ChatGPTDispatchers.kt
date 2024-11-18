
package com.skydovesxyh.ivyai.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val chatGPTDispatchers: ChatGPTDispatchers)

enum class ChatGPTDispatchers {
  IO
}
