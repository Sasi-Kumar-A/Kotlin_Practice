package Fundamental



fun printLength(text : String?){
    if(text != null){
        println(text.length)
    }

    else{
        println(text)
        println("This is a null data")
    }

    val message : String = "This is the Kotlin"
    val num : Int = 97

    val safe : String? = num as? String

    println(safe)

    @Suppress("Not Work")
    val notsafe :Int? = message as? Int
    println(notsafe)
}

fun main(args : Array<String>){
    printLength(null)
}