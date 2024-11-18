
package com.skydovesxyh.ivyai.feature.chat.initializer

import android.content.Context
import androidx.startup.Initializer
import com.skydovesxyh.ivyai.core.preferences.Preferences
import com.skydovesxyh.ivyai.feature.chat.BuildConfig
import com.skydovesxyh.ivyai.feature.chat.di.ChatEntryPoint
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.models.ConnectionData
import io.getstream.chat.android.models.User
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory
import io.getstream.log.streamLog
import io.getstream.result.call.Call
import javax.inject.Inject
import kotlin.random.Random

/**
 * StreamChatInitializer initializes all Stream Client components.
 */
class StreamChatInitializer : Initializer<Unit> {

  @Inject
  internal lateinit var preferences: Preferences

  override fun create(context: Context) {
    ChatEntryPoint.resolve(context).inject(this)

    streamLog { "StreamChatInitializer is initialized" }

    /**
     * initialize a global instance of the [ChatClient].
     * The ChatClient is the main entry point for all low-level operations on chat.
     * e.g, connect/disconnect user to the server, send/update/pin message, etc.
     */
    val logLevel = if (BuildConfig.DEBUG) ChatLogLevel.ALL else ChatLogLevel.NOTHING
    val offlinePluginFactory = StreamOfflinePluginFactory(
      appContext = context
    )
    val statePluginFactory = StreamStatePluginFactory(
      config = StatePluginConfig(
        backgroundSyncEnabled = true,
        userPresence = true
      ),
      appContext = context
    )
    val chatClient = ChatClient.Builder(BuildConfig.STREAM_API_KEY, context)
      .withPlugins(offlinePluginFactory, statePluginFactory)
      .logLevel(logLevel)
      .build()

    val user = User(
      id = preferences.userUUID,
      name = "User ${Random.nextInt(10000)}",
      image = "https://picsum.photos/id/${Random.nextInt(1000)}/300/300"
    )

    val token = chatClient.devToken(user.id)
    chatClient.connectUser(user, token).enqueue(object : Call.Callback<ConnectionData> {
      override fun onResult(result: io.getstream.result.Result<ConnectionData>) {
        if (result.isFailure) {
          streamLog {
            "Can't connect user. Please check the app README.md and ensure " +
              "**Disable Auth Checks** is ON in the Dashboard"
          }
        }
      }
    })
  }

  override fun dependencies(): List<Class<out Initializer<*>>> =
    listOf(StreamLogInitializer::class.java)
}
