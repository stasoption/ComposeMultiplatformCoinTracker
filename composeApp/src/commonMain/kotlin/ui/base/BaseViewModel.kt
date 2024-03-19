package ui.base

import io.github.aakira.napier.Napier
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent

open class BaseViewModel : KoinComponent {
    
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Napier.e(tag = "CoroutineExceptionHandler", message = "${exception.message}")
        onError(exception)
    }
    
    protected val viewModelScope = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)
    
    open fun onError(exception: Throwable) = Unit
    
    fun launchIO(block: suspend () -> Unit) = viewModelScope.launch {
        block.invoke()
    }
}