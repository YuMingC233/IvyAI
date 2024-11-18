
package com.skydovesxyh.ivyai.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkChatGPTColorScheme = darkColorScheme(
  primary = STREAM_PRIMARY,
  primaryContainer = STREAM_PRIMARY,
  secondary = STREAM_PRIMARY,
  background = STREAM_PRIMARY,
  tertiary = WHITE200,
  onTertiary = GRAY200,

)

private val LightChatGPTColorScheme = lightColorScheme(
  primary = STREAM_PRIMARY,
  primaryContainer = STREAM_PRIMARY,
  secondary = STREAM_PRIMARY,
  background = WHITE200,
  tertiary = WHITE200,
  onTertiary = GRAY200
)

/** Light Android background theme */
private val LightAndroidBackgroundTheme = BackgroundTheme(color = Color.White)

/** Dark Android background theme */
private val DarkAndroidBackgroundTheme = BackgroundTheme(color = BACKGROUND900)

@Composable
fun ChatGPTComposeTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = if (darkTheme) DarkChatGPTColorScheme else LightChatGPTColorScheme
  val backgroundTheme = if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme

  CompositionLocalProvider(
    LocalBackgroundTheme provides backgroundTheme
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
  }
}
