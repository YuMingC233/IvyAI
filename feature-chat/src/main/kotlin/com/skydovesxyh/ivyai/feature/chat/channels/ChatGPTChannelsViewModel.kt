
package com.skydovesxyh.ivyai.feature.chat.channels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydovesxyh.ivyai.core.data.repository.GPTChannelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.log.streamLog
import io.getstream.result.onSuccessSuspend
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ChatGPTChannelsViewModel @Inject constructor(
  private val gptChannelRepository: GPTChannelRepository
) : ViewModel() {

  private val channelsMutableUiState =
    MutableStateFlow<GPTChannelUiState>(GPTChannelUiState.Nothing)
  val channelUiState: StateFlow<GPTChannelUiState> = channelsMutableUiState

  private val isBalloonDisplayedMutableState =
    MutableStateFlow(gptChannelRepository.isBalloonChannelDisplayed())
  val isBalloonDisplayedState: StateFlow<Boolean> = isBalloonDisplayedMutableState

  init {
    viewModelScope.launch {
      gptChannelRepository.streamUserFlow().collect { user ->
        user?.let {
          gptChannelRepository.joinTheCommonChannel(it)
        } ?: run {
          streamLog {
            "User is null. Please check the app README.md and ensure " +
              "**Disable Auth Checks** is ON in the Dashboard"
          }
        }
      }
    }
  }

  fun handleEvents(gptChannelEvent: GPTChannelEvent) {
    when (gptChannelEvent) {
      GPTChannelEvent.CreateChannel -> createRandomChannel()
    }
  }

  private fun createRandomChannel() {
    viewModelScope.launch {
      channelsMutableUiState.value = GPTChannelUiState.Loading
      val result = gptChannelRepository.createRandomChannel()
      result.onSuccessSuspend {
        channelsMutableUiState.value = GPTChannelUiState.Success(it.id)
        delay(100L)
        channelsMutableUiState.value = GPTChannelUiState.Nothing
      }.onError {
        channelsMutableUiState.value = GPTChannelUiState.Error
      }
    }
  }

  fun balloonChannelDisplayed() {
    isBalloonDisplayedMutableState.value = true
    gptChannelRepository.balloonChannelDisplayed()
  }
}

sealed interface GPTChannelEvent {
  object CreateChannel : GPTChannelEvent
}

sealed interface GPTChannelUiState {
  data object Nothing : GPTChannelUiState

  data object Loading : GPTChannelUiState

  data class Success(val channelId: String) : GPTChannelUiState

  data object Error : GPTChannelUiState
}
