
package com.skydovesxyh.ivyai.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class ChatGPTScreens(
  val route: String,
  val navArguments: List<NamedNavArgument> = emptyList()
) {
  val name: String = route.appendArguments(navArguments)

  // login screen
  data object Login : ChatGPTScreens("login")

  // channel screen
  data object Channels : ChatGPTScreens("channels")

  // message screen
  data object Messages : ChatGPTScreens(
    route = "messages",
    navArguments = listOf(navArgument(argument_channel_id) { type = NavType.StringType })
  ) {
    fun createRoute(channelId: String) =
      name.replace("{${navArguments.first().name}}", channelId)
  }

  companion object {
    const val argument_channel_id = "channelId"
  }
}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
  val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
    .orEmpty()
  val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
    .orEmpty()
  return "$this$mandatoryArguments$optionalArguments"
}
