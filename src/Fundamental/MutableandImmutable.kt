package Fundamental

fun main(){
    val mutable = mutableListOf(10,20,30,90);
    val immutable = listOf<Int>(10,80,333,553,45,78,69)

    mutable[2] = 101;
    mutable.add(3090);
    mutable.remove(90)

    println(immutable)
    println(mutable)
}