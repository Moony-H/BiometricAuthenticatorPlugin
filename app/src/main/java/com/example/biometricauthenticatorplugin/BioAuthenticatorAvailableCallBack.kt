package com.example.biometricauthenticatorplugin

abstract class BioAuthenticatorAvailableCallBack {
    abstract fun onSucceed()
    abstract fun onUnavailable()
    abstract fun onUnSupportedDevice()
    abstract fun onNotEnrolled()
    abstract fun onUnknownError()
    abstract fun onUpdateRequired()
}