
plugins {
  id("skydoves.android.library")
  id("skydoves.android.library.compose")
  id("skydoves.android.hilt")
  id("skydoves.spotless")
}

android {
  namespace = "com.skydovesxyh.ivyai.core.navigation"
}

dependencies {
  implementation(project(":core-model"))

  implementation(libs.kotlinx.coroutines.android)
  api(libs.androidx.navigation.compose)
}
