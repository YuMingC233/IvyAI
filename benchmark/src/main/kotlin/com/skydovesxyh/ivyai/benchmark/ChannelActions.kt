
package com.skydovesxyh.ivyai.benchmark

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until

fun MacrobenchmarkScope.channelsExplore() = device.apply {
  channelsWaitForContent()
  channelsScrollDownUp()
}

fun MacrobenchmarkScope.channelsWaitForContent() = device.apply {
  wait(Until.hasObject(By.res("Stream_ChannelsScreen")), STANDARD_TIMEOUT)
}

fun MacrobenchmarkScope.channelsScrollDownUp() = device.apply {
  val channelList = waitAndFindObject(By.res("Stream_Channels"), STANDARD_TIMEOUT)
  flingElementDownUp(channelList)
}

fun MacrobenchmarkScope.navigateFromChannelsToMessages() = device.apply {
  waitAndFindObject(By.res("Stream_ChannelItem"), STANDARD_TIMEOUT).click()
  waitForIdle()
}
