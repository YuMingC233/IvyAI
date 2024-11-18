

plugins {
  id("skydoves.android.library")
  id("skydoves.android.hilt")
  id("skydoves.spotless")
  id(libs.plugins.google.secrets.get().pluginId)
}

android {
  namespace = "com.skydovesxyh.ivyai.core.network"

  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementation(project(":core-model"))
  implementation(project(":core-preferences"))

  implementation(libs.androidx.startup)
  implementation(libs.stream.log)

  api(libs.okhttp.logging)
  api(libs.retrofit.core)
  api(libs.retrofit.moshi.converter)
  api(libs.sandwich)
}

secrets {
  propertiesFileName = "secrets.properties"
  defaultPropertiesFileName = "secrets.defaults.properties"
}