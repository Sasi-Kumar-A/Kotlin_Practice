package Fundamental

fun main(){
    val name : String? = null
    val number : Int? = null
    var doub : Double? = null
    val length : Int = name?.length ?: 0

    val len : Int? = number?.minus(2)



    println(length)

    println(name)
    println(number)

}