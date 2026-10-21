package Fundamental

fun String.removeSpaces(): String{
    return this.replace(" ", "")
}

fun String.validEmail(): Boolean{
    return this.contains("@") && this.contains(".")
}


fun main(){
    val str = "sa ta n k i n d @ g ma i l . c o m"
    println(str.removeSpaces())
    println(str.validEmail())
}