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

package com.skydovesxyh.ivyai.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skydovesxyh.ivyai.core.designsystem.theme.ChatGPTComposeTheme

@Composable
fun ChatGPTSmallTopBar(title: String) {
  TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    title = {
      Text(
        text = title,
        color = MaterialTheme.colorScheme.tertiary,
        style = MaterialTheme.typography.titleLarge
      )
    },
    colors = TopAppBarDefaults.mediumTopAppBarColors(
      containerColor = MaterialTheme.colorScheme.primary
    )
  )
}

@Preview
@Composable
private fun ChatGPTSmallTopBarPreview() {
  ChatGPTComposeTheme {
    ChatGPTSmallTopBar("ChatGPT Android")
  }
}

@Preview
@Composable
private fun ChatGPTSmallTopBarDarkPreview() {
  ChatGPTComposeTheme(darkTheme = true) {
    ChatGPTSmallTopBar("ChatGPT Android")
  }
}