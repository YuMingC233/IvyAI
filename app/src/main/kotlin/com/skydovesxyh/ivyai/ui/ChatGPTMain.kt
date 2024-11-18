package com.skydovesxyh.ivyai.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.skydovesxyh.ivyai.core.designsystem.component.ChatGPTBackground
import com.skydovesxyh.ivyai.core.designsystem.theme.ChatGPTComposeTheme
import com.skydovesxyh.ivyai.core.navigation.AppComposeNavigator
import com.skydovesxyh.ivyai.navigation.IvyNavHost

@Composable
fun ChatGPTMain(
  composeNavigator: AppComposeNavigator
) {
  ChatGPTComposeTheme {
    val navHostController = rememberNavController()

    LaunchedEffect(Unit) {
      composeNavigator.handleNavigationCommands(navHostController)
    }

    ChatGPTBackground {
      IvyNavHost(navHostController = navHostController, composeNavigator = composeNavigator)
    }
  }
}
