package com.sheraz.listrepos.data.network

import com.sheraz.listrepos.utils.Logger
import okhttp3.logging.HttpLoggingInterceptor

class HttpLogger(
    private val logger: Logger
): HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        logger.d(TAG, "RAW_MESSAGE $message")
    }


    companion object {
        private val TAG = HttpLogger::class.java.simpleName
    }
}