
plugins {
  id("skydoves.android.library")
  id("skydoves.spotless")
  id("kotlin-parcelize")
  id("com.google.devtools.ksp")
}

android {
  namespace = "com.skydovesxyh.ivyai.core.model"
}

dependencies {
  api(libs.stream.client)
  api(libs.moshi)
  ksp(libs.moshi.codegen)
}
