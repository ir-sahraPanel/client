package ir.sahrapanel.app.core.util

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual val Dispatchers.IO: CoroutineContext
    get() = Dispatchers.IO