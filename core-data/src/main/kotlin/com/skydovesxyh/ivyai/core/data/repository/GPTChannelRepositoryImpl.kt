
package com.skydovesxyh.ivyai.core.data.repository

import com.skydovesxyh.ivyai.core.data.chat.chatGPTUser
import com.skydovesxyh.ivyai.core.data.chat.commonChannelId
import com.skydovesxyh.ivyai.core.preferences.Preferences
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.models.Channel
import io.getstream.chat.android.models.User
import io.getstream.result.Result
import io.getstream.result.onSuccessSuspend
import java.util.Random
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class GPTChannelRepositoryImpl @Inject constructor(
  private val chatClient: ChatClient,
  private val preferences: Preferences
) : GPTChannelRepository {

  override suspend fun createRandomChannel(): Result<Channel> {
    val userId = chatClient.getCurrentUser()?.id ?: ""
    return chatClient.createChannel(
      channelType = "messaging",
      channelId = UUID.randomUUID().toString(),
      memberIds = listOf(userId, chatGPTUser.id),
      extraData = mapOf("name" to "新对话 ${Random().nextInt(99999)}")
    ).await()
  }

  override suspend fun joinTheCommonChannel(user: User) {
    val channelClient = chatClient.channel(commonChannelId)
    val result = channelClient.watch().await()
    result.onSuccessSuspend { channel ->
      val members = channel.members
      val isExist = members.firstOrNull { it.user.id == user.id }
      if (isExist == null) {
        channelClient.addMembers(listOf(user.id)).await()
      }
    }
  }

  override fun streamUserFlow(): Flow<User?> = chatClient.clientState.user

  override fun isBalloonChannelDisplayed(): Boolean = preferences.balloonChannelDisplayed

  override fun balloonChannelDisplayed() {
    preferences.balloonChannelDisplayed = true
  }
}
