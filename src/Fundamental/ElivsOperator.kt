package Fundamental

fun main(args: Array<String>) {
    val name : String ?= null; // The reason to use the ? is explicite tell to complier that this variable can hold the null value.
    val length : Int = name?.length ?: 0

    println(length)


}