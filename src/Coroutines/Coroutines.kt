package Coroutines

import kotlinx.coroutines.*

// A lightweight concurrency framework / execution block
//It manages a running task's lifecycles, jobs, contexts, and cancellations.
fun main() = runBlocking {
    val job  =  launch{
        //launch-> Job Object , async-> return type is deffered

        delay(1000L)
        println("Coroutines Executed...")

    }

    println("Coroutines")
    job.join()
    println("End")
}