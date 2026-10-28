package Fundamental

data class User(val username: String, val email: String, val id: Int)

fun main() {
    val user = User("Alice", "alice@example.com", 101)

    // Destructuring into 3 variables based on position
    val (username, email, id) = user

    println(username) // Outputs: Alice
    println(id)       // Outputs: 101
}
