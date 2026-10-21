package Fundamental

// 43. This is Primary Constructor
//class Person(val name: String, val age: Int) { // Primary Constructor
//
//    constructor():  //Secondary Constructor
//        this(readln(),0)
//    fun greet() {
//        println("Hello, my name is $name and I'm $age years old.")
//    }
//}
//
//fun main() {
//    val person = Person("John", 25)
//    println("Enter the Name: ")
//    val persons = Person()
//    person.greet() // Output: Hello, my name is John and I'm 25 years old.
//    persons.greet()
//}



// 45. Init and Constructor

//class user(val name : String,val age : Int){
//    init {
//        require(name.isNotBlank()){"Name must not be Blank"}
//        require(age >= 18){"Age must be in range from 18 to 100"}
//        println("User Initialized $name, $age")
//
//    }
//
//    override fun toString(): String {
//        return "user(name='$name', age='$age')"
//    }
//}
//
//fun main(){
//    val data = user("Ram",22)
//    println(data)
//}


// 100. Modifiers

//open class Parent{
//    public val publicVal = "I am the public"
//    private val privateVal = "I am the private"
//    protected val protectedVal = "I am the protected"
//    internal val internalVal = "I am the internal"
//
//
//    fun testAccess(){
//        println(privateVal)
//    }
//}
//
//class Child : Parent(){
//    fun checkModifier(){
//        println(publicVal)
//        println(internalVal)
//        println(protectedVal)
//
////        println(privateVal)
//    }
//}
//
//class unreachable(val data : Parent){
//    fun check(){
//        println(data.publicVal)
//        println(data.internalVal)
////        println(data.)
//    }
//}
//
//fun main(){
//    val child = Child()
//    child.checkModifier()
//    val parent = Parent()
//    parent.testAccess()
//
//    val child2 = unreachable(child)
//    child2.check()
//}


// 99 . Handle Concurrency
//class Counter {
//    private var count = 0
//
//    fun increment() {
//        synchronized(this) {
//            count++
//        getCount()
//        }
//    }
//
//    fun getCount() {
////        synchronized(this) {
//            println(count)
////        }
//    }
//}
//
//fun main() {
//    val counter = Counter()
//
//    // Thread 1
//    Thread {
//        for (i in 1..1000) {
//            counter.increment()
//        }
//    }.start()
//
//    // Thread 2
//    Thread {
//        for (i in 1..1000) {
//            counter.increment()
//        }
//    }.start()
//
//    Thread.sleep(1000) // Wait for threads to complete
//
////    println("Final count: ${counter.getCount()}") // Output: Final count: 2000
//}

// 98. String Interpolation
//fun main(){
//    println("Enter Name: ")
//    val names = readln()
//    println("Enter Age: ")
//    val age = readln().toInt()
//
//    println("My name is $names.length, and My age is $age")
//
//}


//90. Infix function and Regular Function
//
//infix fun Int.add( a : Int): Int{
//    return this + a
//}
//
//fun main(){
//    val add = 5 add 8
//    println(add)
//}




// 89. @JvmName

// ❌ WITHOUT @JvmName: This will throw a "Platform declaration clash" compiler error.
// fun processValues(list: List<String>) { ... }
// fun processValues(list: List<Int>) { ... }

//  WITH @JvmName: This compiles perfectly!
fun processValues(list: List<String>) {
    println("Processing strings")
}

@JvmName("processIntValues") // Java will see this function as 'processIntValues'
fun processValues(list: List<Int>) {
    println("Processing integers")
}

fun main(){
    processValues(listOf(1,2,4,7))

}