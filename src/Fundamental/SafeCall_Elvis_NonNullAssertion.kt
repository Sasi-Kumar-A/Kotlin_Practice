package Fundamental


fun main() {
    // A mix of nullable data to simulate real-world API responses or user input
    val rawData: Any? = "Kotlin Programming"
    val nullData: String? = null
    print("Enter the String: ")
    val data : String? = readln()
    data?.length

    println("--- 1. SAFE CALL (?.) ---")
    // Safe call returns the length if rawData is a String, or null if it's null.
    // (We cast rawData to String? first so we can read its length)
    val inputStr = rawData as? String
    val length1: Int? = inputStr?.length
    val length2: Int? = nullData?.length
    println("Length of text: $length1") // Prints: 18
    println("Length of null: $length2")  // Prints: null


    println("\n--- 2. ELVIS OPERATOR (?:) ---")
    // The Elvis operator provides a fallback value if the left side evaluates to null.
    val safeLength: Int = nullData?.length ?: 0
    println("Fallback length for null data: $safeLength") // Prints: 0

    // You can even use 'return' or 'throw' on the right side of the Elvis operator
    val mandatoryText: String = nullData ?: "Default Value"
    println("Text value: $mandatoryText") // Prints: Default Value


    println("\n--- 3. SAFE CAST (as?) ---")
    // Regular cast (as) crashes if the type doesn't match.
    // Safe cast (as?) returns null instead of crashing.
    val numberObj: Any = 42

    val stringCast: String? = numberObj as? String // Cannot cast Int to String
    println("Result of invalid safe cast: $stringCast") // Prints: null


    println("\n--- 4. NON-NULL ASSERTION (!!) ---")
    // Use this ONLY when you are 100% sure a nullable variable is not null.
    // If it actually is null, your program will crash with a NullPointerException.
    val confirmedNotNull: String? = "I am definitely here"

    val forcedLength: Int = confirmedNotNull!!.length
    println("Forced length: $forcedLength") // Prints: 20

    try {
        println("Testing !! on a null variable:")
        val crashMe: Int = nullData!!.length // This will crash!
    } catch (e: NullPointerException) {
        println("Caught expected crash: Variable was actually null!")
    }
}
