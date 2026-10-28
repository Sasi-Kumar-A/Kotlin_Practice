package Coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

fun main() = runBlocking {
    // 1. Initialize a Semaphore with a limit of 3 concurrent permits
    val downloadSemaphore = Semaphore(permits = 3)

    // Launching 10 download tasks concurrently
    val jobs = List(10) { fileId ->
        launch(Dispatchers.Default) {
            println("File #$fileId: Waiting for an available download slot...")

            // 2. Wrap the protected operation inside withPermit
            downloadSemaphore.withPermit {
                println("-> File #$fileId: Slot acquired! Downloading...")
                delay(1500) // Simulating network download time
                println("<- File #$fileId: Download finished.")
            }
            // The permit is automatically released right here
        }
    }

    jobs.joinAll()
    println("All files downloaded successfully.")
}
