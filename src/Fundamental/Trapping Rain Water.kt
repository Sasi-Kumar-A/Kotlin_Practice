package Fundamental

fun trap(height: IntArray): Int {
    var left = 0
    var right = height.size - 1
    var leftMax = 0
    var rightMax = 0
    var water = 0

    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= leftMax) leftMax = height[left]
            else water += leftMax - height[left]
            left++
        } else {
            if (height[right] >= rightMax) rightMax = height[right]
            else water += rightMax - height[right]
            right--
        }
    }
    return water
}

fun main() {
    println(trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1))) // 6
    println(trap(intArrayOf(4, 2, 0, 3, 2, 5)))                   // 9
    println(trap(intArrayOf()))                                   // 0
}