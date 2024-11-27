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

package com.skydovesxyh.ivyai.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydovesxyh.ivyai.core.data.session.user
import com.skydovesxyh.ivyai.core.navigation.AppComposeNavigator
import com.skydovesxyh.ivyai.core.navigation.ChatGPTScreens
import kotlinx.coroutines.delay

@Composable
fun IvyLogin(
  composeNavigator: AppComposeNavigator,
) {
  val userName = remember { mutableStateOf("") }
  val passWord = remember { mutableStateOf("") }
  val formState = remember { mutableStateOf(false) }

  val isButtonEnabled = remember { mutableStateOf(true) }
  val snackbarHostState = remember { SnackbarHostState() }
  val isLoading = remember { mutableStateOf(false) }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF005dfc))
  ) {
    Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
      Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("test",
          color = Color.White,
          fontSize = 30.sp,
          modifier = Modifier.padding(20.dp),
          fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
        Column (
          modifier = Modifier.width(360.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // 账户输入框
          TextField(
            value = userName.value,
            onValueChange = {
              userName.value = it
            },
            isError = formState.value && userName.value.isEmpty(),
            label = { Text("用户名") },
            supportingText = { Text("请输入用户名", color = Color.White) },
            modifier = Modifier.padding(8.dp),
          )
          // 密码输入框
          TextField(
            value = passWord.value,
            onValueChange = {
              passWord.value = it
            },
            label = { Text("密码") },
            modifier = Modifier.padding(8.dp),
            isError = formState.value && passWord.value.isEmpty(),
            supportingText = { Text("请输入密码", color = Color.White) },
            visualTransformation = PasswordVisualTransformation('*')
          )
        }

        // 登录按钮组
        Row (
          modifier = Modifier.width(180.dp),
          horizontalArrangement = Arrangement.Center
        ) {
          Button(
            onClick = {
              formState.value = userName.value.isEmpty() || passWord.value.isEmpty()
              if (formState.value.not()) {
                // 禁用当前按钮并显示加载指示器
                isButtonEnabled.value = false
                isLoading.value = true
              }
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF599e5e),
              contentColor = Color.White
            ),
            enabled = isButtonEnabled.value
          ) {
            Text("登录")
          }
//          Button(onClick = {}) {
//            Text("注册")
//          }

        }
      }
    }
    if (!isButtonEnabled.value) {
      LaunchedEffect(Unit) {
        delay(2000)
        snackbarHostState.showSnackbar("登录成功！")
        isButtonEnabled.value = true
        isLoading.value = false
        // 将用户名和密码保存到全局对象中
        user.login(userName.value, passWord.value)

        // 跳转到主页
        composeNavigator.navigate(ChatGPTScreens.Channels.route)
      }
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(Color(0x80000000)), // 半透明黑色背景
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator(
          modifier = Modifier.size(48.dp),
          color = Color.White
        )
      }
    }
    SnackbarHost(hostState = snackbarHostState)
  }
}
