
@file:OptIn(ExperimentalComposeUiApi::class)

package com.skydovesxyh.ivyai.feature.chat.channels

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skydoves.balloon.compose.Balloon
import com.skydovesxyh.ivyai.core.designsystem.component.ChatGPTLoadingIndicator
import com.skydovesxyh.ivyai.core.designsystem.composition.LocalOnFinishDispatcher
import com.skydovesxyh.ivyai.core.designsystem.theme.STREAM_PRIMARY
import com.skydovesxyh.ivyai.core.navigation.AppComposeNavigator
import com.skydovesxyh.ivyai.core.navigation.ChatGPTScreens
import com.skydovesxyh.ivyai.feature.chat.R
import com.skydovesxyh.ivyai.feature.chat.theme.ChatGPTStreamTheme
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen

@Composable
fun ChatGPTChannels(
  modifier: Modifier,
  composeNavigator: AppComposeNavigator,
  viewModel: ChatGPTChannelsViewModel = hiltViewModel(),
  onFinishDispatcher: (() -> Unit)? = LocalOnFinishDispatcher.current
) {
  ChatClient.instance().clientState.initializationState.collectAsStateWithLifecycle()

  val uiState by viewModel.channelUiState.collectAsStateWithLifecycle()

  HandleGPTChannelsUiState(uiState = uiState)

  ChatGPTStreamTheme {
    Box(
      modifier = modifier
        .fillMaxSize()
        .semantics { testTagsAsResourceId = true }
    ) {
      ChannelsScreen(
        isShowingHeader = false,
        onChannelClick = { channel ->
          composeNavigator.navigate(ChatGPTScreens.Messages.createRoute(channel.cid))
        },
        onBackPressed = { onFinishDispatcher?.invoke() }
      )

      val isBalloonDisplayed by viewModel.isBalloonDisplayedState.collectAsStateWithLifecycle()

      Balloon(
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .padding(16.dp)
          .size(58.dp),
        builder = rememberFloatingBalloon(),
        balloonContent = {
          Text(
            modifier = Modifier
              .padding(12.dp)
              .fillMaxWidth(),
            text = "你可以手动添加一个新的会话…",
            textAlign = TextAlign.Center,
            color = Color.White
          )
        }
      ) { balloonWindow ->

        LaunchedEffect(key1 = Unit) {
          if (!isBalloonDisplayed) {
            balloonWindow.showAlignTop()
          }

          balloonWindow.setOnBalloonDismissListener {
            viewModel.balloonChannelDisplayed()
            balloonWindow.dismiss()
          }
        }

        FloatingActionButton(
          modifier = Modifier.matchParentSize(),
          containerColor = STREAM_PRIMARY,
          shape = CircleShape,
          onClick = { viewModel.handleEvents(GPTChannelEvent.CreateChannel) }
        ) {
          Icon(
            imageVector = Icons.Filled.AddComment,
            contentDescription = null,
            tint = Color.White
          )
        }
      }

      if (uiState == GPTChannelUiState.Loading) {
        ChatGPTLoadingIndicator()
      }
    }
  }
}

@Composable
private fun HandleGPTChannelsUiState(
  uiState: GPTChannelUiState
) {
  val context = LocalContext.current
  LaunchedEffect(key1 = uiState) {
    when (uiState) {
      is GPTChannelUiState.Success -> Toast.makeText(
        context,
        R.string.toast_success_create_channel,
        Toast.LENGTH_SHORT
      ).show()

      is GPTChannelUiState.Error -> Toast.makeText(
        context,
        R.string.toast_error,
        Toast.LENGTH_SHORT
      ).show()

      else -> Unit
    }
  }
}
