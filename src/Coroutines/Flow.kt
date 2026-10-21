package Coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


class Flow { //it is also like a stream,pipeline
    //it will emits multiple data
    fun count() : Flow<Int> = flow {
        for(i in 1..10){
            delay(1000)
            emit(i)
        }
    }
}

fun main() = runBlocking{
    val obj = Flow()

    obj.count().map { it * 2 }.collect{ value -> println(value)}
}