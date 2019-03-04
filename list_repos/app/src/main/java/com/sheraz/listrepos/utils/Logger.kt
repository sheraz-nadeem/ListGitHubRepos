package com.sheraz.listrepos.utils

import android.util.Log
import com.sheraz.listrepos.BuildConfig



object Logger {

    private val TAG = Logger::class.java.simpleName
    private var loggingEnabled = false

    init {
        if (BuildConfig.DEBUG) {
            loggingEnabled = true
        }
        Log.d(TAG, "init(): loggingEnabled: $loggingEnabled")
    }


    /**
     * Debug Level
     */

    fun d(s: String, message: String) {
        if (loggingEnabled) {
            Log.d(s, message)
        }
    }



    /**
     * Info Level
     */

    fun i(s: String, message: String) {
        if (loggingEnabled) {
            Log.i(s, message)
        }
    }



    /**
     * Verbose Level
     */

    fun v(s: String, message: String) {
        if (loggingEnabled) {
            Log.v(s, message)
        }
    }



    /**
     * Warning Level
     */

    fun w(s: String, message: String) {
        if (loggingEnabled) {
            Log.w(s, message)
        }
    }



    /**
     * Error Level
     */

    fun e(s: String, message: String) {
        if (loggingEnabled) {
            Log.e(s, message)
        }
    }


}