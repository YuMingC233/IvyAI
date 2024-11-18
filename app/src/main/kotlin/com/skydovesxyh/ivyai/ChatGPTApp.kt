
package com.skydovesxyh.ivyai

import android.app.Application
import com.skydoves.snitcher.Snitcher
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChatGPTApp : Application() {

  override fun onCreate() {
    super.onCreate()

    // install Snitcher to trace global exceptions and restore the app.
    // https://github.com/skydoves/snitcher
    Snitcher.install(this)
  }
}
