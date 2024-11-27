package com.skydovesxyh.ivyai.core.data.session

object user {
  var userName: String? = null
  var passWord: String? = null

  fun login(userName: String, passWord: String) {
    this.userName = userName
    this.passWord = passWord
  }

  fun logout() {
    this.userName = null
    this.passWord = null
  }
}