package Fundamental

import java.lang.classfile.constantpool.IntegerEntry

fun main(){
    val name : List<String> = listOf("Alice", "Bob","Charlis","Dravid");
    val sal : Set<Int> = setOf(20000, 24000, 18000,800000)
    val emp : Map<String, Int> = mapOf("Alice" to 20000, "Bob" to 24000, "Charlis" to 18000, "Dravid" to 80000)
    val nam : Set<Int> = buildSet {
        println(name)
    }
    println(emp)
    println(sal)
    println(name)

}