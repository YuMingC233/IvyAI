
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
  id("skydoves.android.library")
  id("skydoves.android.library.compose")
  id("skydoves.android.feature")
  id("skydoves.android.hilt")
  id("skydoves.spotless")
  id(libs.plugins.google.secrets.get().pluginId)
}

android {
  namespace = "com.skydovesxyh.ivyai.feature.chat"

  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  // Stream chat Compose
  api(libs.stream.compose)
  api(libs.stream.offline)

  implementation(libs.androidx.lifecycle.runtimeCompose)
  implementation(libs.androidx.lifecycle.viewModelCompose)
  implementation(libs.androidx.startup)

  implementation(libs.androidx.worker)
  implementation(libs.viewmodel.lifecycle)
  implementation(libs.hilt.worker)

  implementation(libs.balloon.compose)
  implementation(libs.hutool.all)
}

secrets {
  propertiesFileName = "secrets.properties"
  defaultPropertiesFileName = "secrets.defaults.properties"
}