package edu.hnjd.hyx.ivyai.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.skydovesxyh.ivyai.core.navigation.ChatGPTScreens

sealed class NavigationItem(var route: String, var defIcon: ImageVector,var selectedIcon: ImageVector, var title: String) {
    object Home : NavigationItem(ChatGPTScreens.Channels.name, Icons.Outlined.Home,Icons.Filled.Home,  "首页")
    object Mine : NavigationItem(ChatGPTScreens.Login.name, Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle,"我的")

}
val items = listOf(
    NavigationItem.Home,
    NavigationItem.Mine
)