
package com.skydovesxyh.ivyai.core.designsystem.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.skydovesxyh.ivyai.core.designsystem.theme.STREAM_PRIMARY

@Composable
fun BoxScope.ChatGPTLoadingIndicator() {
  CircularProgressIndicator(
    modifier = Modifier.align(Alignment.Center),
    color = STREAM_PRIMARY
  )
}
