
package com.skydovesxyh.ivyai.core.data.di

import com.skydovesxyh.ivyai.core.data.repository.GPTChannelRepository
import com.skydovesxyh.ivyai.core.data.repository.GPTChannelRepositoryImpl
import com.skydovesxyh.ivyai.core.data.repository.GPTMessageRepository
import com.skydovesxyh.ivyai.core.data.repository.GPTMessageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

  @Binds
  fun bindsChatGPTChannelsRepository(
    gptChannelRepository: GPTChannelRepositoryImpl
  ): GPTChannelRepository

  @Binds
  fun bindsChatGPTMessagesRepository(
    gptMessageRepository: GPTMessageRepositoryImpl
  ): GPTMessageRepository
}
