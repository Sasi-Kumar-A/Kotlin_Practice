package Fundamental

fun main(){
    fun sum  (x : Int, y : Int, add : (Int, Int) -> Int ):Int{
        return add(x,y)
    }
}