package Fundamental

import kotlin.math.pow

fun main() {
    val number = 1634

    if (isArmstrong(number)) {
        println("$number is an Armstrong number.")
    } else {
        println("$number is not an Armstrong number.")
    }
}

fun isArmstrong(num: Int): Boolean {
    var originalNumber = num
    var digits = 0
    var sum = 0

    // Count the number of digits
    while (originalNumber != 0) {
        originalNumber /= 10
        digits++
    }

    originalNumber = num

    // Calculate the sum of the digits raised to the power of 'digits'
    while (originalNumber != 0) {
        val remainder = originalNumber % 10
        sum += remainder.toDouble().pow(digits).toInt()
        originalNumber /= 10
    }

    // Check if the sum equals the original number
    return sum == num
}
