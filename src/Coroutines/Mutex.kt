package Coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SharedCounter {
    // 1. Initialize the Mutex instance
    private val mutex = Mutex()
    var count = 0
        private set

    // 2. Protect the modification with a lock
    suspend fun increment() {
        mutex.withLock {
            println("Mutex Lock started")
            count++
        }
    }
}

fun main() = runBlocking {
    val counter = SharedCounter()

    // Launch 1,000 coroutines to increment the counter concurrently
    val jobs = List(1000) {
        launch(Dispatchers.Default) {
            counter.increment()
        }
    }

    // Wait for all 1,000 operations to finish
    jobs.joinAll()

    // 3. The result is guaranteed to be exactly 1000
    println("Final count: ${counter.count}")
}
