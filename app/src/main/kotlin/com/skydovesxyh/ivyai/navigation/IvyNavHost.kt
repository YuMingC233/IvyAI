/*
 * Designed and developed by 2024 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
      /**
       * 自定义LocalRippleTheme行为覆盖Navigation的默认点击特效
       */
      if (currentRoute == ChatGPTScreens.Channels.name || currentRoute == ChatGPTScreens.Login.name) {
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