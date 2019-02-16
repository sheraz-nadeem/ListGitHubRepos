package com.sheraz.listrepos.utils

import com.sheraz.listrepos.BuildConfig
import timber.log.Timber



object Logger {


    init {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    /**
     * Debug Level
     */

    fun d(s: String, vararg objects: Any) {
        Timber.d(s, objects)
    }

    fun d(throwable: Throwable, s: String, vararg objects: Any) {
        Timber.d(throwable, s, objects)
    }



    /**
     * Info Level
     */

    fun i(s: String, vararg objects: Any) {
        Timber.i(s, objects)
    }

    fun i(throwable: Throwable, s: String, vararg objects: Any) {
        Timber.i(throwable, s, objects)
    }



    /**
     * Verbose Level
     */

    fun v(s: String, vararg objects: Any) {
        Timber.v(s, objects)
    }

    fun v(throwable: Throwable, s: String, vararg objects: Any) {
        Timber.v(throwable, s, objects)
    }



    /**
     * Warning Level
     */

    fun w(s: String, vararg objects: Any) {
        Timber.w(s, objects)
    }

    fun w(throwable: Throwable, s: String, vararg objects: Any) {
        Timber.w(throwable, s, objects)
    }



    /**
     * Error Level
     */

    fun e(s: String, vararg objects: Any) {
        Timber.e(s, objects)
    }

    fun e(throwable: Throwable, s: String, vararg objects: Any) {
        Timber.e(throwable, s, objects)
    }

}