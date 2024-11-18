
plugins {
  id("skydoves.android.library")
  id("skydoves.android.library.compose")
  id("skydoves.spotless")
}

android {
  namespace = "com.skydovesxyh.ivyai.core.designsystem"
}

dependencies {
  // image loading
  api(libs.landscapist.glide)
  api(libs.landscapist.animation)
  api(libs.landscapist.placeholder)

  api(libs.androidx.compose.runtime)
  api(libs.androidx.compose.ui)
  api(libs.androidx.compose.ui.tooling)
  api(libs.androidx.compose.ui.tooling.preview)
  api(libs.androidx.compose.material.iconsExtended)
  api(libs.androidx.compose.material)
  api(libs.androidx.compose.material3)
  api(libs.androidx.compose.foundation)
  api(libs.androidx.compose.foundation.layout)
  api(libs.androidx.compose.constraintlayout)
}
