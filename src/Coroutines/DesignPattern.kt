package Coroutines

// ==========================================
// 1. SINGLETON PATTERN (Creational Type)
// ==========================================
object AppConfig {
    val environment = "Production"
    fun log(message: String) = println("[$environment Log]: $message")
}

// ==========================================
// 2. BUILDER PATTERN (Creational Type)
// ==========================================
// Kotlin replaces the Builder class using constructor defaults
data class Laptop(
    val cpu: String,
    val ram: Int = 8,           // Default value
    val storage: Int = 256,     // Default value
    val modelName: String = "Standard Pro"
)

// ==========================================
// 3. DELEGATION PATTERN (Structural Type)
// ==========================================
interface OrderProcessor {
    fun process(item: String)
}

class StandardOrderProcessor : OrderProcessor {
    override fun process(item: String) {
        println("📦 Packaging item: $item for delivery.")
    }
}

// Uses native delegation 'by' to pass structural calls to the inner processor
class ECommerceSystem(processor: OrderProcessor) : OrderProcessor by processor

// ==========================================
// 4. STRATEGY PATTERN (Behavioral Type)
// ==========================================
class CheckoutContext(private val basePrice: Double) {
    // Strategy is passed as a first-class function/lambda parameter
    fun calculateTotal(discountStrategy: (Double) -> Double): Double {
        return discountStrategy(basePrice)
    }
}

// ==========================================
// RUNNABLE APPLICATION ENTRY POINT
// ==========================================
fun main() {
    // 1. Singleton Usage
    AppConfig.log("Initializing E-Commerce Subsystems...")

    // 2. Builder Pattern (via Named & Default Arguments)
    val customGamingLaptop = Laptop(cpu = "Intel i9", ram = 32, storage = 1000)
    AppConfig.log("Created Order Item: $customGamingLaptop")

    // 3. Delegation Pattern Usage
    val standardProcessor = StandardOrderProcessor()
    val store = ECommerceSystem(standardProcessor)
    store.process(customGamingLaptop.modelName) // Delegated automatically

    // 4. Strategy Pattern Usage
    val checkout = CheckoutContext(basePrice = 1500.0)

    // Switch algorithm strategies instantly at runtime using lambdas
    val regularPrice = checkout.calculateTotal { price -> price }
    val blackFridayPrice = checkout.calculateTotal { price -> price * 0.80 } // 20% Off

    println("💰 Regular Checkout Price: $$regularPrice")
    println("🔥 Black Friday Checkout Price: $$blackFridayPrice")
}
