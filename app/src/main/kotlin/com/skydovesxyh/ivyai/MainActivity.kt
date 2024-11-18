package com.skydovesxyh.ivyai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.skydovesxyh.ivyai.core.designsystem.composition.LocalOnFinishDispatcher
import com.skydovesxyh.ivyai.core.designsystem.theme.ChatGPTComposeTheme
import com.skydovesxyh.ivyai.core.navigation.AppComposeNavigator
import com.skydovesxyh.ivyai.ui.ChatGPTMain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  internal lateinit var appComposeNavigator: AppComposeNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CompositionLocalProvider(
        LocalOnFinishDispatcher provides { finish() }
      ) {
        ChatGPTComposeTheme { ChatGPTMain(composeNavigator = appComposeNavigator) }
      }
    }
  }
}
