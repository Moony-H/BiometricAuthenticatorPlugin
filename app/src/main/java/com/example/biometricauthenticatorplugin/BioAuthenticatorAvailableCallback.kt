package com.example.biometricauthenticatorplugin

abstract class BioAuthenticatorAvailableCallback {
    abstract fun onSucceed()
    fun onUnavailable(){}
    fun onUnSupportedDevice(){}
    fun onNotEnrolled(){}
    fun onUnknownError(){}
    fun onUpdateRequired(){}
}