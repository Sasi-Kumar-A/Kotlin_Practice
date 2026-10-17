package Fundamental

import java.lang.classfile.Signature

class Datatype {

    companion object {
        @JvmStatic
        fun main(arg: Array<String>) {
            var name: String = "Kotlin"
            val number: Int = 51
            val double: Double = 1.3
            val initial: Char = 'A'
            val ret : Boolean = false

            name = "jhh"


            println("$name + $ret")
            println(number)
            println(double)
            println(initial)
            println(ret)

        }
    }
}