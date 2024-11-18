
package com.skydovesxyh.ivyai.feature.chat.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.skydovesxyh.ivyai.core.designsystem.theme.BACKGROUND900
import com.skydovesxyh.ivyai.core.designsystem.theme.STREAM_PRIMARY
import com.skydovesxyh.ivyai.core.designsystem.theme.STREAM_PRIMARY_LIGHT
import com.skydovesxyh.ivyai.feature.chat.reactions.ChatGPTReactionFactory
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.ui.theme.StreamColors

@Composable
fun ChatGPTStreamTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val streamColors = if (darkTheme) {
    StreamColors.defaultDarkColors().copy(
      appBackground = BACKGROUND900,
      primaryAccent = STREAM_PRIMARY,
      ownMessagesBackground = STREAM_PRIMARY
    )
  } else {
    StreamColors.defaultColors().copy(
      primaryAccent = STREAM_PRIMARY,
      ownMessagesBackground = STREAM_PRIMARY_LIGHT
    )
  }

  ChatTheme(
    colors = streamColors,
    reactionIconFactory = ChatGPTReactionFactory(),
    content = content
  )
}
