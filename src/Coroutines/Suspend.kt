package Coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Suspend {
    suspend fun fetchData(): String{
        delay(2000L)
        return "Data Fetching....."
    }

    suspend fun create() : String{
        delay(2000L)

//        launch {
//            delay(1000L)
//            println("I'am a 1st Block")
//        }
        return "String is Created...."
    }

    suspend fun update() = coroutineScope {
        println("I'm a coroutineScope")
        launch {
            delay(1000L)
            println("I'm a launch block inside the coroutines Scope")
        }
    }
}

fun main() = runBlocking {
    val obj = Suspend()

    val cre = launch { obj.create() } // Launch is a function
    val data = launch { obj.fetchData() }

    println("This is a Button click function....."+data)
    println("This is a Button click function....."+cre)
    println(obj.update())
    println(obj.fetchData())
    println(obj.create())
}