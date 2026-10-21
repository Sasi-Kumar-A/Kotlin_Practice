package Fundamental


    data class Vector(val x: Int, val y: Int) {
        operator fun plus(other: Vector): Vector {
            return Vector(x + other.x, y + other.y)
        }
    }



    fun main() {
        val v1 = Vector(1, 2)
        val v2 = Vector(3, 4)
        val sum = v1 + v2

        println(sum) // Output: Vector(x=4, y=6)
    }
