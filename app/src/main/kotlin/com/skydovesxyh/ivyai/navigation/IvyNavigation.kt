
package com.skydovesxyh.ivyai.navigation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.skydovesxyh.ivyai.core.navigation.AppComposeNavigator
import com.skydovesxyh.ivyai.core.navigation.ChatGPTScreens
import com.skydovesxyh.ivyai.core.navigation.ChatGPTScreens.Companion.argument_channel_id
import com.skydovesxyh.ivyai.feature.chat.channels.ChatGPTChannels
import com.skydovesxyh.ivyai.feature.chat.messages.ChatGPTMessages
import com.skydovesxyh.ivyai.feature.login.IvyLogin
import com.skydovesxyh.ivyai.feature.mine.ChatGPTMine


fun NavGraphBuilder.ivyHomeNavigation(
  composeNavigator: AppComposeNavigator
) {
  composable(route = ChatGPTScreens.Channels.name) {
    ChatGPTChannels(
      modifier = Modifier,
      composeNavigator = composeNavigator
    )
  }

  composable(route = ChatGPTScreens.Mine.name) {
    ChatGPTMine(composeNavigator = composeNavigator)
  }

  composable(route = ChatGPTScreens.Login.name) {
    IvyLogin(composeNavigator = composeNavigator)
  }

  composable(
    route = ChatGPTScreens.Messages.name,
    arguments = ChatGPTScreens.Messages.navArguments
  ) {
    val channelId = it.arguments?.getString(argument_channel_id) ?: return@composable
    ChatGPTMessages(
      channelId = channelId,
      composeNavigator = composeNavigator,
      viewModel = hiltViewModel()
    )
  }
}


/**
 * 一个简单的底部导航组件，用于显示底部导航栏。
 */
@Composable
fun BottomNavigationBar(navController: NavController) {
  // 使用Compose提供的collectAsState方法实时获取并响应NavController的当前路由变化。
  val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

  NavigationBar(
    modifier = Modifier.fillMaxWidth()
      .height(60.dp),
  ) {
    items.forEach { item ->
      val isSelected = item.route == currentRoute
      CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
        NavigationBarItem(
          selected = isSelected,
          onClick = {
            if (navController.currentDestination?.route != item.route) {
              navController.navigate(item.route) {
                navController.graph.startDestinationRoute?.let { route ->
                  popUpTo(route) {
                    saveState = true
                  }
                }
                launchSingleTop = true
                restoreState = true
              }
            }
          },
          icon = {
            Icon(
              modifier=Modifier.offset(y = (3).dp), // 向Y轴偏移 3 dp
              imageVector = if (isSelected) item.selectedIcon else item.defIcon,
              contentDescription = item.title,
              tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
          },
          colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(LocalAbsoluteTonalElevation.current)
          ),
          label = {
            Text(
              modifier=Modifier.offset(y = (-3).dp), // 向Y轴偏移 -3 dp
              text = item.title,
              color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
              fontSize = 12.sp,
              style = TextStyle(
                lineHeight = 0.sp
              )
            )
          },
          interactionSource = remember { MutableInteractionSource() }, // 新增
        )
      }

    }
  }
}


private object RippleCustomTheme: RippleTheme {
  //Your custom implementation...
  @Composable
  override fun defaultColor() =
    RippleTheme.defaultRippleColor(
      // 将涟漪颜色改为和 navigation bar 相同的颜色，做到取消涟漪效果
      MaterialTheme.colorScheme.surfaceColorAtElevation(LocalAbsoluteTonalElevation.current),
      lightTheme = true

    )
  @Composable
  override fun rippleAlpha(): RippleAlpha =
    RippleTheme.defaultRippleAlpha(
      // 将涟漪颜色改为和 navigation bar 相同的颜色，做到取消涟漪效果
      MaterialTheme.colorScheme.surfaceColorAtElevation(LocalAbsoluteTonalElevation.current),
      lightTheme = true
    )
}