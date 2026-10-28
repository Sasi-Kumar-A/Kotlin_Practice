//package Coroutines
//
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.CoroutineScope
//
//// Define the global handler
//val globalErrorHandler = CoroutineExceptionHandler { _, exception ->
//    println("Caught an unhandled exception globally: ${exception.localizedMessage}")
//    // Send to your error tracking server here
//}
//
//// Attach it to the root scope of your operations
//val scope = CoroutineScope(Job() + Dispatchers.Main + globalErrorHandler)
//
//scope.launch {
//    throw NullPointerException("Unexpected code error!") // App won't crash!
//}
