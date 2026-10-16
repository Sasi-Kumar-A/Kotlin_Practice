package Fundamental

fun main(){
    var number : Int = 53
    val char = number.toChar()
    val double = number.toDouble()
    val str = number.toString()

    number = 41

    val int = str.toInt()
    println(int)
    println(number::class.simpleName)
    println(char)
    println(double)
    println(str)
    println(number)

}