
package com.skydovesxyh.ivyai.feature.chat.initializer

import android.content.Context
import androidx.startup.Initializer
import com.skydovesxyh.ivyai.feature.chat.BuildConfig
import io.getstream.log.Priority
import io.getstream.log.android.AndroidStreamLogger
import io.getstream.log.streamLog

class StreamLogInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    if (BuildConfig.DEBUG) {
      AndroidStreamLogger.install(minPriority = Priority.DEBUG)
      streamLog { "StreamLogInitializer is initialized" }
    }
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
