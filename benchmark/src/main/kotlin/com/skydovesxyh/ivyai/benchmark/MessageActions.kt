
package com.skydovesxyh.ivyai.benchmark

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until

fun MacrobenchmarkScope.messagesExplore() = device.apply {
  messagesWaitForContent()
  messagesScrollDownUp()
}

fun MacrobenchmarkScope.messagesWaitForContent() = device.apply {
  wait(Until.hasObject(By.res("Stream_MessagesScreen")), STANDARD_TIMEOUT)
}

fun MacrobenchmarkScope.messagesScrollDownUp() = device.apply {
  val channelList = waitAndFindObject(By.res("Stream_Messages"), STANDARD_TIMEOUT)
  flingElementDownUp(channelList)
}
