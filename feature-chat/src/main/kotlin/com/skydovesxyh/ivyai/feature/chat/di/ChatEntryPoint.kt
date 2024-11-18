
package com.skydovesxyh.ivyai.feature.chat.di

import android.content.Context
import com.skydovesxyh.ivyai.feature.chat.initializer.StreamChatInitializer
import com.skydovesxyh.ivyai.feature.chat.worker.ChatGPTMessageWorker
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface ChatEntryPoint {

  fun inject(streamChatInitializer: StreamChatInitializer)

  fun inject(chatGPTMessageWorker: ChatGPTMessageWorker)

  companion object {

    fun resolve(context: Context): ChatEntryPoint {
      val appContext = context.applicationContext ?: throw IllegalStateException(
        "applicationContext was not found in ChatEntryPoint"
      )
      return EntryPointAccessors.fromApplication(
        appContext,
        ChatEntryPoint::class.java
      )
    }
  }
}
