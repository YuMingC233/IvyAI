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

package com.skydovesxyh.ivyai.feature.mine

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydovesxyh.ivyai.core.data.session.user
import com.skydovesxyh.ivyai.core.data.session.user.userName
import com.skydovesxyh.ivyai.core.navigation.AppComposeNavigator
import com.skydovesxyh.ivyai.core.navigation.ChatGPTScreens

var globalNavigator: AppComposeNavigator? = null

@Composable
fun ChatGPTMine(
  composeNavigator: AppComposeNavigator,
) {
  val context: Context = LocalContext.current;
  globalNavigator = composeNavigator
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      // 用户信息头部
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color(0xFF005dfc)) // 蓝色背景
          .padding(30.dp, 50.dp, 30.dp, 50.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          // 用户头像
          /*
          Image(
            painter = painterResource(id = io.getstream.chat.android.client.R.drawable.abc_ic_star_black_16dp), // 替换成你的头像资源
            contentDescription = null,
            modifier = Modifier
              .size(64.dp)
              .clip(CircleShape)
          )
          Spacer(modifier = Modifier.width(16.dp))
          */
          Column(
            modifier = Modifier.weight(1f)
              .clickable {
                // 跳转到登录页面
                globalNavigator!!.navigate(ChatGPTScreens.Login.route)
              }
            ,
          ) {
            // 用户名
            val userName = user.userName
            if (userName != null) {
              Text(text = userName, fontSize = 18.sp, color = Color.White)
            } else {
              Text(text = "尚未登录", fontSize = 18.sp, color = Color.White)
            }
          }
          // 退出登录按钮
          TextButton(
            onClick = {
              // 退出登录
              user.logout()
              Toast.makeText(context, "退出登录成功！", Toast.LENGTH_SHORT).show()
              // 重新刷新整个App的状态以达到退出登录的效果
            },
            modifier = Modifier.align(Alignment.CenterVertically)
          ) {
            Text(text = "退出登录", color = Color.White)
          }
        }
      }

      // 用户统计信息
      /*
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(text = "获赞", fontSize = 16.sp, color = Color.Gray)
          Text(text = "35", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(text = "收益", fontSize = 16.sp, color = Color.Gray)
          Text(text = "1.31", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(text = "发布", fontSize = 16.sp, color = Color.Gray)
          Text(text = "26", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
      }*/

      // 列表
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
      ) {
        item {
          MenuItem(icon = Icons.Default.FavoriteBorder, text = "偏好管理", router = ChatGPTScreens.Prefer.route, context)
          HorizontalDivider(thickness = 2.dp)
        }
        item {
          MenuItem(icon = Icons.Default.Memory, text = "记忆管理", router = "a", context)
          HorizontalDivider(thickness = 2.dp)
        }
        item {
          MenuItem(icon = Icons.Default.AccountBalanceWallet, text = "提示词管理", router = "a", context)
          HorizontalDivider(thickness = 2.dp)
        }
        item {
          MenuItem(icon = Icons.Default.Settings, text = "设置", router = "a", context)
        }
      }
    }
  }
}

@Composable
fun MenuItem(icon: ImageVector, text: String, router: String, context: Context) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(62.dp)
      .padding(vertical = 8.dp)
      .clickable {
        if (userName == null) {
          Toast.makeText(context, "您尚未登录，请先登录。", Toast.LENGTH_SHORT).show()
        } else {
          // 跳转到指定页面
          globalNavigator!!.navigate(router)
        }
      },
    verticalAlignment = Alignment.CenterVertically,
  ) {
    androidx.compose.material.Icon(
      imageVector = icon,
      contentDescription = null,
      tint = Color.Gray
    )
    Spacer(modifier = Modifier.width(16.dp))
    Text(text = text, fontSize = 16.sp, color = Color.Gray)
  }
}
