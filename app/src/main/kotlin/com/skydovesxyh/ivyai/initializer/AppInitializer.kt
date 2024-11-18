
package com.skydovesxyh.ivyai.initializer

import android.content.Context
import androidx.startup.Initializer
import com.skydovesxyh.ivyai.feature.chat.initializer.StreamChatInitializer

class AppInitializer : Initializer<Unit> {

  override fun create(context: Context) = Unit

  override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
    StreamChatInitializer::class.java
  )
}
