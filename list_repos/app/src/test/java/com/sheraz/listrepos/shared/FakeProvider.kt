package com.sheraz.listrepos.shared

import com.sheraz.listrepos.data.CoroutinesDispatcherProvider
import kotlinx.coroutines.Dispatchers.Unconfined

fun provideFakeCoroutinesDispatcherProvider(): CoroutinesDispatcherProvider =
    CoroutinesDispatcherProvider(Unconfined, Unconfined, Unconfined)