package com.example.biometricauthenticatorplugin

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL


internal class BiometricAuthenticatorImpl : BiometricAuthenticator {
    override fun isAvailable(context: Context): Boolean {
        return getAuthenticationState(context) == BiometricManager.BIOMETRIC_SUCCESS
    }

    override fun checkBiometricAvailableState(
        context: Context,
        callback: BioAuthenticatorAvailableCallback
    ) {
        when (getAuthenticationState(context)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                //성공
                callback.onSucceed()
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                //생체 인식 센서 또는 키가드 없음).
                Log.e("MY_APP_TAG", "No biometric features available on this device.")
                callback.onUnSupportedDevice()

            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                //하드웨어를 사용할 수 없음
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
                callback.onUnavailable()
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                //생체 인식이 등록되지 않음
                callback.onNotEnrolled()
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                //업데이트가 필요(또는 보안 취약점이 발견되어 할수 없음)
                callback.onUpdateRequired()
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                //지정된 옵션이 현재 Android 버전과 호환되지 않아 사용자를 인증할 수 없습니다.
            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                //알수 없음
                callback.onUnknownError()
            }
        }
    }

    override fun requestBiometricEnrollment(activity: AppCompatActivity) {
        // Prompts the user to create credentials that your app accepts.

        getAuthenticationState(activity)
        val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
            putExtra(
                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BIOMETRIC_STRONG or DEVICE_CREDENTIAL
            )
        }
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        }
        activity.startActivity(enrollIntent)
    }

    private fun getAuthenticationState(context: Context): Int {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)

    }
}