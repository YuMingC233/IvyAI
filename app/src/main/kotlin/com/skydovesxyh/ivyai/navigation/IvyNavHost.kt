
package com.skydovesxyh.ivyai.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.skydovesxyh.ivyai.R
import com.skydovesxyh.ivyai.core.designsystem.component.ChatGPTSmallTopBar
import com.skydovesxyh.ivyai.core.navigation.AppComposeNavigator
import com.skydovesxyh.ivyai.core.navigation.ChatGPTScreens
import com.skydovesxyh.ivyai.feature.chat.channels.ChatGPTChannels

@Composable
fun IvyNavHost(
  navHostController: NavHostController,
  composeNavigator: AppComposeNavigator
) {
  val navBackStackEntry by navHostController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route

  Scaffold(
    /**
    topBar = {
      ChatGPTSmallTopBar(
        title = stringResource(id = R.string.app_name)
      )
    },
     */
    bottomBar = {
      if (currentRoute == ChatGPTScreens.Channels.name || currentRoute == ChatGPTScreens.Mine.name) {
        BottomNavigationBar(navHostController)
      }
    }
  ) { padding ->
    NavHost(
      navController = navHostController,
      startDestination = ChatGPTScreens.Channels.route,
      modifier = Modifier.padding(padding)
    ) {
      ivyHomeNavigation(
        composeNavigator = composeNavigator
      )
    }
  }
}