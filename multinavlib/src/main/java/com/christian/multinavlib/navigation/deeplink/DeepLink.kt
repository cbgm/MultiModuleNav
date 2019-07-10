package com.christian.multinavlib.navigation.deeplink


data class DeepLink(
      var action: DeepLinkIdentifier,
      var parameter: String? = null
)