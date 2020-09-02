package com.aengussong.gasstationtracker.repo.local

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

class UserIdProvider {
    companion object {
        @SuppressLint("HardwareIds")
        fun getId(context: Context): String {
            return Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        }
    }
}