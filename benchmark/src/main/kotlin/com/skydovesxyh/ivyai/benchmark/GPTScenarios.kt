
package com.skydovesxyh.ivyai.benchmark

import androidx.benchmark.macro.MacrobenchmarkScope

fun MacrobenchmarkScope.chatGPTScenarios() {
  pressHome()
  startActivityAndWait()
  device.waitForIdle()

  // -------------
  // Channels
  // -------------
  channelsExplore()
  navigateFromChannelsToMessages()

  // -------------
  // Messages
  // -------------
  messagesExplore()
}
