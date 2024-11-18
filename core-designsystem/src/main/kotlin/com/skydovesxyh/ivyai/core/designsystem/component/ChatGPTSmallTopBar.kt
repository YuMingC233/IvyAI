
package com.skydovesxyh.ivyai.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydovesxyh.ivyai.core.designsystem.theme.ChatGPTComposeTheme

@Composable
fun ChatGPTSmallTopBar(title: String) {
  CenterAlignedTopAppBar(
    modifier = Modifier
      .fillMaxWidth()
    ,
    title = {
      Text(
        text = title,
        color = MaterialTheme.colorScheme.tertiary,
        style = MaterialTheme.typography.titleMedium,
        overflow = TextOverflow.Ellipsis
      )
    },
    navigationIcon = {
      IconButton(onClick = { /*TODO*/ }) {
        Icon(
          imageVector = Icons.Filled.Menu,
          contentDescription = "菜单",
          tint = MaterialTheme.colorScheme.tertiary
        )
      }
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
