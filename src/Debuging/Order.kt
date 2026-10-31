package Debuging

data class CartItem(
    val name: String,
    val price: Double,
    val quantity: Int
)

data class OrderResult(
    val subtotal: Double,
    val finalTotal: Double,
    val perPerson: Double
)

/**
 * Calculates the total cost of items.
 */
fun calculateSubtotal(items: List<CartItem>): Double {
    var total = 0.0

    // BUG #1 HINT: Check the range bounds in Kotlin loops (inclusive '..' vs 'until')
    for (i in 0..items.size) {
        total += items[i].price * items[i].quantity
    }

    return total
}

/**
 * Applies coupon codes to the subtotal.
 */
fun applyDiscount(subtotal: Double, discountCode: String): Double {
    val discounts = mapOf(
        "SAVE10" to 0.10,
        "HALFPRICE" to 0.50,
        "FREE" to 1.00
    )

    // BUG #2 HINT: Map lookup with 'getValue()' throws NoSuchElementException
    // if the key is missing or case does not match.
    val rate = discounts.getValue(discountCode)

    val discountedAmount = subtotal - (subtotal * rate)
    return discountedAmount
}

/**
 * Splits the bill evenly among group members.
 */
fun splitBillPerPerson(finalAmount: Double, customerCount: Int): Double {
    // BUG #3 HINT: Floating-point division by 0.0 in Kotlin yields 'Infinity',
    // or integer modulo/division checks can cause ArithmeticException.
    if (customerCount <= 0) {
        throw IllegalArgumentException("Customer count must be greater than zero.")
    }
    val costPerPerson = finalAmount / customerCount
    return Math.round(costPerPerson * 100.0) / 100.0
}

fun processOrder(cart: List<CartItem>, coupon: String, numPeople: Int): OrderResult {
    println("\n--- Processing Order ---")

    // Step 1: Calculate raw subtotal
    val subtotal = calculateSubtotal(cart)
    println("Subtotal: $${String.format("%.2f", subtotal)}")

    // Step 2: Apply discount
    val discountedTotal = applyDiscount(subtotal, coupon)
    println("Total after coupon '$coupon': $${String.format("%.2f", discountedTotal)}")

    // Step 3: Split between customers
    val perPerson = splitBillPerPerson(discountedTotal, numPeople)
    println("Each person pays ($numPeople people): $${String.format("%.2f", perPerson)}")

    return OrderResult(
        subtotal = subtotal,
        finalTotal = discountedTotal,
        perPerson = perPerson
    )
}

fun main() {
    // Test Case 1: Standard valid cart
    val cartOne = listOf(
        CartItem("Laptop", 1200.0, 1),
        CartItem("Wireless Mouse", 25.0, 2),
        CartItem("USB Cable", 10.0, 3)
    )

    println("Running Test 1...")
    // Expected: Subtotal = 1280.0, Discount = 10% (1152.0), Per person (2 people) = 576.0
    val resultOne = processOrder(cart = cartOne, coupon = "SAVE10", numPeople = 2)
    println("Test 1 Result: $resultOne")

    // Test Case 2: Edge cases
    val cartTwo = listOf(
        CartItem("Headphones", 150.0, 1)
    )

    println("\nRunning Test 2...")
    // Edge case coupon: "WELCOME" (unrecognized)
    // Edge case count: 0 people
    val resultTwo = processOrder(cart = cartTwo, coupon = "WELCOME", numPeople = 0)
    println("Test 2 Result: $resultTwo")
}