
plugins {
  id("skydoves.android.library")
  id("skydoves.android.hilt")
  id("skydoves.spotless")
  id("com.google.devtools.ksp")
}

android {
  namespace = "com.skydovesxyh.ivyai.core.data"
}

dependencies {
  api(project(":core-model"))
  api(project(":core-network"))
  api(project(":core-preferences"))

  api(libs.moshi)
  ksp(libs.moshi.codegen)
}
