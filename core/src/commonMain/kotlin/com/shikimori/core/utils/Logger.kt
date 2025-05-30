package com.shikimori.core.utils

import co.touchlab.kermit.Logger as KermitLogger

object Logger {
    fun d(tag: String, message: String) {
        KermitLogger.d(message, tag = tag)
    }
    
    fun i(tag: String, message: String) {
        KermitLogger.i(message, tag = tag)
    }
    
    fun w(tag: String, message: String) {
        KermitLogger.w(message, tag = tag)
    }
    
    fun e(tag: String, message: String, throwable: Throwable? = null) {
        KermitLogger.e(message, throwable, tag = tag)
    }
}