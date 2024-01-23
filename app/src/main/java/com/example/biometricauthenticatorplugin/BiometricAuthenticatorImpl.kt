package com.example.biometricauthenticatorplugin

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.core.app.ActivityCompat.startActivityForResult

internal class BiometricAuthenticatorImpl{
    fun checkHardwareAvailable(context: Context,callBack: BioAuthenticatorAvailableCallBack){
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS ->{
                callBack.onSucceed()
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->{
                Log.e("MY_APP_TAG", "No biometric features available on this device.")
                callBack.onUnSupportedDevice()

            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->{
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
                callBack.onUnavailable()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                callBack.onNotEnrolled()
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                callBack.onUpdateRequired()
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {

            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                TODO()
            }
        }
    }
}