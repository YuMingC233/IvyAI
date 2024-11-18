
package com.skydovesxyh.ivyai.core.preferences

import android.content.SharedPreferences
import com.skydovesxyh.ivyai.core.preferences.delegate.booleanPreferences
import com.skydovesxyh.ivyai.core.preferences.delegate.stringPreferences
import java.util.UUID
import javax.inject.Inject

class Preferences @Inject constructor(
  val sharedPreferences: SharedPreferences
) {

  val userUUID: String by stringPreferences(
    key = KEY_UUID,
    defaultValue = UUID.randomUUID().toString()
  )

  var balloonChannelDisplayed: Boolean by booleanPreferences(
    key = KEY_BALLOON_CHANNEL_DISPLAYED,
    defaultValue = false
  )

  companion object {
    private const val KEY_UUID: String = "key_uuid"
    private const val KEY_BALLOON_CHANNEL_DISPLAYED = "key_balloon_channel_displayed"
  }
}

val String.Companion.Empty
  inline get() = ""
