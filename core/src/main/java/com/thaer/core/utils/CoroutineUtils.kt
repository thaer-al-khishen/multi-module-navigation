package com.thaer.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

object CoroutineScopeHelper {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    fun getCoroutineScope(): CoroutineScope {
        return scope
    }
}

inline fun launchFastScope(crossinline action: suspend (CoroutineScope) -> Unit) {
    CoroutineScopeHelper.getCoroutineScope().launch {
        action.invoke(this)
    }
}
