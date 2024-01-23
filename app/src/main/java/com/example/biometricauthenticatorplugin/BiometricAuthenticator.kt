package com.example.biometricauthenticatorplugin

interface BiometricAuthenticator {
    fun isAvailable():Boolean
    fun requestBiometric()
}