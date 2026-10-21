package Fundamental


    val exp : String by lazy{
        "Initialize value"
    }

fun main(){
    println("This is a Lazy Initializer")
    println(exp)
    println("After the Lazy Initializer")
}