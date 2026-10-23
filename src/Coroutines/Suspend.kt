//package Coroutines
//
//import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking
//
//
////A function modifier that allows pausing and resuming.
//// It does not hold lifecycle states; it is just a piece of execution logic.
////Can only be called from inside another suspend function or a coroutine block.
//class Suspend {
//    suspend fun fetchData(): String{
//        delay(2000L)
//        return "Data Fetching....."
//    }
//
//    suspend fun create() : String{
//        delay(2000L)
//
////        launch {
////            delay(1000L)
////            println("I'am a 1st Block")
////        }
//        return "String is Created...."
//    }
//
//    suspend fun update() = coroutineScope {
//        println("I'm a coroutineScope")
//        launch {
//            delay(1000L)
//            println("I'm a launch block inside the coroutines Scope")
//        }
//    }
//}
////Kotlin coroutines cannot run out of thin air;
//// they must exist inside a structured environment called a CoroutineScope.
//fun main() = runBlocking {
//    val obj = Suspend()
//
//    val cre = launch { obj.create() } // Launch is a function
//    val data = launch { obj.fetchData() }
//
//    println("This is a Button click function....."+data)
//    println("This is a Button click function....."+cre)
//    println(obj.update())
//    println(obj.fetchData())
//    println(obj.create())
//}












package Coroutines

import kotlinx.coroutines.*

class Suspend {
    suspend fun fetchData(): String {
        delay(2000L)
        return "Data Fetching....."
    }

    suspend fun create(): String {
        delay(2000L)
        return "String is Created...."
    }

    suspend fun update() = coroutineScope {
        println("I'm a coroutineScope starting internal background work...")
        // Using launch here is completely fine for a fire-and-forget logging side effect
        launch {
            delay(1000L)
            println("I'm a launch block executing inside the coroutines Scope!")
        }
        "Update state sequence finished" // Returns this string value out of coroutineScope
    }
}

// Optimization: Using modern 'suspend fun main' instead of manual runBlocking structures
suspend fun main() = coroutineScope {
    val obj = Suspend()

    println("⏳ Spawning asynchronous network calculations concurrently...")

    // Fix: Using async instead of launch to capture the real data result futures
    val creDeferred = async { obj.create() }
    val dataDeferred = async { obj.fetchData() }

    // Fix: Calling .await() to pull the concrete text content out of the Deferred instances
    println("This is a Button click function result data: " + dataDeferred.await())
    println("This is a Button click function result cre: " + creDeferred.await())

    println("----------------------------------------")

    // Testing the scope function structure
    val updateMessage = obj.update()
    println("Update function result: $updateMessage")
}
