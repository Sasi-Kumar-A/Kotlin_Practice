package Coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job  = launch{
        delay(1000L)
        println("Coroutines Executed...")
    }

    println("Coroutines")
    job.join()
    println("End")
}