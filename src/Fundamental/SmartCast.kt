package Fundamental

fun printLength(text : String?){
    if(text != null){
        println(text.length)
    }
    else{
        println("This is a null data")
    }
}

fun main(args : Array<String>){
    printLength(null)
}