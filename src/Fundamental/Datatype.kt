package Fundamental

import java.lang.classfile.Signature

class Datatype {

    companion object {
        @JvmStatic
        fun main(arg: Array<String>) {
            val name: String = "Kotlin"
            val Number: Int = 51
            val double: Double = 1.3
            val initial: Char = 'A'
            val ret : Boolean = false

            println("$name + $ret")
            println(Number)
            println(double)
            println(initial)
            println(ret)

        }
    }
}