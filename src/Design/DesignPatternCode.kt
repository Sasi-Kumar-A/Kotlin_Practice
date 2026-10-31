package Design



// ============================================================
// DESIGN PATTERNS PRACTICE - SINGLE KOTLIN FILE
// ============================================================


// ============================================================
// 1. SINGLETON PATTERN
// ============================================================

// Only one instance of this object exists
object AppConfig {

    val appName = "Kotlin Pattern App"

    fun showConfig() {
        println("App Name: $appName")
    }
}


// ============================================================
// 2. FACTORY PATTERN
// ============================================================

interface Animal {
    fun makeSound()
}

class Dog : Animal {
    override fun makeSound() {
        println("Dog says: Woof!")
    }
}

class Cat : Animal {
    override fun makeSound() {
        println("Cat says: Meow!")
    }
}

object AnimalFactory {

    fun createAnimal(type: String): Animal {

        return when (type.lowercase()) {
            "dog" -> Dog()
            "cat" -> Cat()
            else -> throw IllegalArgumentException("Unknown animal")
        }
    }
}


// ============================================================
// 3. BUILDER PATTERN
// ============================================================

data class User(
    val name: String,
    val age: Int,
    val email: String
)

class UserBuilder {

    private var name = ""
    private var age = 0
    private var email = ""

    fun setName(name: String): UserBuilder {
        this.name = name
        return this
    }

    fun setAge(age: Int): UserBuilder {
        this.age = age
        return this
    }

    fun setEmail(email: String): UserBuilder {
        this.email = email
        return this
    }

    fun build(): User {
        return User(name, age, email)
    }
}


// ============================================================
// 4. STRATEGY PATTERN
// ============================================================

// Different payment strategies

interface PaymentStrategy {
    fun pay(amount: Double)
}

class CreditCardPayment : PaymentStrategy {

    override fun pay(amount: Double) {
        println("Paid ₹$amount using Credit Card")
    }
}

class UPIPayment : PaymentStrategy {

    override fun pay(amount: Double) {
        println("Paid ₹$amount using UPI")
    }
}

class PaymentContext(
    private var paymentStrategy: PaymentStrategy
) {

    fun setPaymentStrategy(strategy: PaymentStrategy) {
        paymentStrategy = strategy
    }

    fun makePayment(amount: Double) {
        paymentStrategy.pay(amount)
    }
}


// ============================================================
// 5. OBSERVER PATTERN
// ============================================================

interface Observer {
    fun update(message: String)
}

class UserObserver(
    private val name: String
) : Observer {

    override fun update(message: String) {
        println("$name received notification: $message")
    }
}

class NotificationService {

    private val observers = mutableListOf<Observer>()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun notifyUsers(message: String) {

        observers.forEach {
            it.update(message)
        }
    }
}


// ============================================================
// MAIN FUNCTION
// ============================================================

fun main() {

    println("========== SINGLETON ==========")

    AppConfig.showConfig()


    println("\n========== FACTORY ==========")

    val dog = AnimalFactory.createAnimal("dog")
    dog.makeSound()

    val cat = AnimalFactory.createAnimal("cat")
    cat.makeSound()


    println("\n========== BUILDER ==========")

    val user = UserBuilder()
        .setName("Sasi")
        .setAge(22)
        .setEmail("sasi@example.com")
        .build()

    println(user)


    println("\n========== STRATEGY ==========")

    val payment = PaymentContext(
        CreditCardPayment()
    )

    payment.makePayment(500.0)

    payment.setPaymentStrategy(
        UPIPayment()
    )

    payment.makePayment(1000.0)


    println("\n========== OBSERVER ==========")

    val notificationService = NotificationService()

    val user1 = UserObserver("Sasi")
    val user2 = UserObserver("Arun")

    notificationService.addObserver(user1)
    notificationService.addObserver(user2)

    notificationService.notifyUsers(
        "New offer available!"
    )
}