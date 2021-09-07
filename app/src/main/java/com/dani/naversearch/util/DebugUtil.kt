package com.dani.naversearch.util

import android.util.Log
import com.dani.naversearch.BuildConfig

fun Any.errorLog(message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(this::class.java.simpleName, message)
    }
}