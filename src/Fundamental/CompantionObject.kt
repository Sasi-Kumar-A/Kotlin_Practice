package Fundamental

import Fundamental.CompanionObject.Companion

class CompanionObject {
    companion object{ // like Static Method &  both are not a single keyword, companion is midifier keyword, Object is a core decleration keyword.
    //

        val name = "Alice"
        val dept = "Manager"
    }
}
fun main(){
    println(Companion.name);
    println(Companion.dept)
}