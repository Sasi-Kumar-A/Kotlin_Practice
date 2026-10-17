package Fundamental

lateinit var name : String

fun assign(){
    name = "Alice"
}

fun printName(){
    assign()
    if(::name.isInitialized){
        println(name)
    }
    else{
        println("Name is not initialized")
    }
}

fun main(){
    printName()
}