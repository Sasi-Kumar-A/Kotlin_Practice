package Coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// this is used to switch execution to a different thread or Dispatcher temporarily.
// main thread , Worker Thread (IO)
class withcontext {

    suspend fun add(){
        println("${10+ 877}- By the Thread is ${Thread.currentThread().name}")

        val sub = withContext(Dispatchers.IO){// we should use only inside the suspend function only not outside
            println("This is the withCOntext ${Thread.currentThread().name}")
            delay(2000)
        }

        val mul = withContext(Dispatchers.Default){
            println("This is the withCOntext ${Thread.currentThread().name}")
        }
    }



    fun mat() : String{
        return Thread.currentThread().name;
    }
}

fun main() = runBlocking{
    val obj = withcontext()

    obj.add()
    println(obj.mat())
}