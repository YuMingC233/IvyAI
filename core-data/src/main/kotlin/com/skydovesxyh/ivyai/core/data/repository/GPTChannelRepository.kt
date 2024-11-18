
package com.skydovesxyh.ivyai.core.data.repository

import io.getstream.chat.android.models.Channel
import io.getstream.chat.android.models.User
import io.getstream.result.Result
import kotlinx.coroutines.flow.Flow

interface GPTChannelRepository {

  suspend fun createRandomChannel(): Result<Channel>

  suspend fun joinTheCommonChannel(user: User)

  fun streamUserFlow(): Flow<User?>

  fun isBalloonChannelDisplayed(): Boolean

  fun balloonChannelDisplayed()
}
