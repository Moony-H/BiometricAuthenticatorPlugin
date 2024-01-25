package com.example.biometricauthenticatorplugin

import androidx.appcompat.app.AppCompatActivity
import android.content.Context

interface BiometricAuthenticator {
    fun isAvailable(context: Context):Boolean
    fun requestBiometricEnrollment(activity:AppCompatActivity)
    fun checkBiometricAvailableState(context: Context,callback: BioAuthenticatorAvailableCallback)
    fun showMessage()
}