package Fundamental


import java.lang.reflect.Type

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
//
////  WITH @JvmName: This compiles perfectly!
//fun processValues(list: List<String>) {
//    println("Processing strings")
//}
//
//@JvmName("processIntValues") // Java will see this function as 'processIntValues'
//fun processValues(list: List<Int>) {
//    println("Processing integers")
//}
//
//fun main(){
//    processValues(listOf(1,2,4,7))
//
//}

//67. Properety Access Syntax

//class User{
//
//    fun add(a:Int, b : Int){
//        val sum = a+b
//    }
//    var name: String = "Manjurin
//
////        get() {
////            println("Getting the Name $field")
////            return field // if we use other then field it doesn't accept because of backing storage
////        }
////
////        set(value) {
////            println("Setting the Name $value")
////            field = value
////        }
//}

//fun main(){
//    val obj = User()
//    obj.name
//    obj.name="Martin"
//}

// 68 . Delegate
    //This is one type of getter and setter , offload the implementation of another class that has encapsulated

// 69. This keyword
//    this keyword is refers to the current object


//  70. apply function / scope function
//  config an object by providing the lambda expression {or} mainly used to initialize or configure an object
//  in concise or readable manner

//data class Emp(var name: String, var age: Int)
//
//fun main(){
//    val per = Emp("Martin", 19).apply { age += 6 }
//    println(per)
//}

//71. Default Argument

//fun emp1(name : String = "Charles"){
//    println("My name is $name")
//}
//
//fun main() {
//    emp1()
//    emp1("Zukuper")
//}

// 72. let and apply in scope function

//fun main(){
//    val name = "Monaliza"   //by the let function commonly used to check null. if we provide null value type parameter
//    val age = 10
//// apply function is to used to initialize and configure the data , giving additional information.
//    age.apply {  println(this+11) }
//    name?.let { println(it) }
//    println(age)
//}


// 73. function reference.

//fun iseven(num1 : Int): Boolean{
//    return num1 % 2 == 0
//}
//
//fun main(){
//    val num = listOf(1,2,3,56,43,4)
//
//    println(num.filter(::iseven))// this is the function reference / it will refer the function
//    // doesn't invoke the function.
//}

//74. downto keyword

//// this is used to cojunction with the .. range operator , it will move decending order.
//fun main(){
//    for(i in 10 downTo 1){
//        // downTo decending only, until only ascending and before the number assigned.
//        println(i)
//    }
//}

// 75. lazy evaluation
//  evaluation of an expression or computing only when it is needed or assigned for a first time.
// It is often used to optimize performance by avoiding unnecessuary computing.

//class User(val username: String) {
//
//    // This heavy database loading operation is DEFERRED
//    val profileData: String by lazy {
//        println("📡 Connecting to Database... (This runs ONLY ONCE)")
//        "Profile Details for $username" // This is saved as the value
//    }
//}
//
//fun main() {
//    println("1. Creating the User object...")
//    val user = User("Manjurin")
//
//    println("2. User object created successfully. (Notice no database message yet!)")
//    println("----------------------------------------")
//
//    // FIRST ACCESS: The lazy block executes right here
//    println("3. Accessing profileData for the FIRST time:")
//    println("Result: ${user.profileData}")
//
//    println("----------------------------------------")
//
//    // SECOND ACCESS: The lazy block does NOT execute. It uses the cached value.
//    println("4. Accessing profileData for the SECOND time:")
//    println("Result: ${user.profileData}")
//}


// 76. Closure
//   A closure can access and modify variable that are decleare outside of its own block,
//   even it is outside the scope block has already finished execution.

//fun main() {
//    var counter = 0 // Variable in the outer scope
//
//    // This lambda block forms a closure
//    val increment = {
//        counter++ // 👈 Captures and modifies the outer variable!
//        println("Current Count: $counter")
//    }
//
//    increment() // Output: Current Count: 1
//    increment() // Output: Output: Current Count: 2
//}

// 77.Until keyowrd
// it is used to execute the data in ascending order and then it will stop before the decleared size.

//fun main(){
//    for(i in 1 until 15){
//        println(i)
//    }
//}

//78 . with function.
//  • When you already have a fully constructed object and
//  you just want to perform actions or compute a different result using its properties.

//class Person {
//    var firstName: String = "Manjurin"
//    var lastName: String = "Martin"
//    var age: Int = 26
//}
//
//fun main() {
//    val person = Person()
//
//    // Pass the 'person' object as the first parameter to 'with'
//    val profileSummary: String = with(person) {
//        // Inside this block, 'this' refers to 'person'
//        // We can access firstName, lastName, and age directly!
//        println("Processing profile for $firstName...")
//
//        // The last line is the return value of the 'with' block
//        "Name: $firstName $lastName, Age: $age"
//    }
//
//    println("Summary: $profileSummary")
//}

// 97. Sealed and Abstrct class
//   Sealed Class - A sealed class is used to represent restricted class hierarchies,
//      where all possible subclasses are known and defined within the sealed class itself

//    Abstract Class - It can define both abstract and non-abstract methods,
//      providing a common interface and behavior that its subclasses must implement.
//
//sealed class Result // if we remove we need to declare the abstract/ open
//// By using the sealed class Only the subclasses you write inside that specific package are allowed to exist.
//data class Success(val message: String) : Result()
//data class Error(val error: String) : Result()
//
//fun processResult(result: Result) {
//    when (result) {
//        is Success -> println("Success: ${result.message}")
//        is Error -> println("Error: ${result.error}")
//        // when we remove the sealed we need to declare the else block or else not needed.
//    }
//}
//
//fun main() {
//    val success = Success("Operation succeeded")
//    val error = Error("Operation failed")
//
//    processResult(success) // Output: Success: Operation succeeded
//    processResult(error) // Output: Error: Operation failed
//}

//
//abstract class Shape { // if we remove the abstrace it become normal class
//    abstract fun calculateArea(): Double // if remove the abstract we need to give the body for it.
//}
//
//class Rectangle(val width: Double, val height: Double) : Shape() {// Must implement abstract method.
//    override fun calculateArea(): Double {
//        return width * height
//    }
//}
//
//class Circle(val radius: Double) : Shape() {
//    override fun calculateArea(): Double {
//        return Math.PI * radius * radius
//    }
//}
//
//fun main() {
//    val rectangle = Rectangle(5.0, 3.0)
//    val circle = Circle(2.0)
//
//    println("Rectangle area: ${rectangle.calculateArea()}") // Output: Rectangle area: 15.0
//    println("Circle area: ${circle.calculateArea()}") // Output: Circle area: 12.566370614359172
//}


// Scope Function

//
//Function	Context Object (this or it)	Return Value	Common Use Case
//let	it	Result of the last line	Null checks (?.let) and transforming data types.
//run	this	Result of the last line	Configuring an object and computing a final result.
//with	this	Result of the last line	Grouping multiple function calls on an existing object.
//apply	this	The original object	Object initialization and configuration.
//also	it	The original object	Additional side effects, like logging or printing data.

//
//fun main() {
//    data class Person(var name: String, var age: Int)
//
////let
//val nullableName: String? = "Manjulan"
//
//// Safe null check wrapper
//   val num = nullableName?.let {
//        println("The length is ${it.length}") // Output: The length is 8
//    }
//
//// Data Transformation
//val ageNextYear = Person("Martin", 25).let {
//    it.age + 1 // Returns 26
//}

//fun main(){
//    val obj = Person("Manoj", 29)
//    println(obj.age)
//}

// run
//    val person = Person("John", 20)
//
//    val greetingMessage = person.run {
//        age = 21 // Modifying property via 'this'
//        "Hello $name, you are now $age!" // Returns this String
//    }
//    println(greetingMessage) // Output: Hello John, you are now 21!

//with
//
//val person = Person("Alex", 30)
//
//// Pass the object directly into the function
//val summary = with(person) {
//    println("Reading properties...")
//    "Name: $name, Age: $age" // Returns this String
//}
//
////apply
//
//// Configures properties and returns the configured Person object
//val newPerson = Person("", 0).apply {
//    name = "Sarah" // updates 'this.name'
//    age = 28       // updates 'this.age'
//}
//
//println(newPerson.name) // Output: Sarah
//
//
////also
//
//val finalPerson = Person("David", 22).also {
//    // Great for intermediate logging in a chain of functions
//    println("Logged: User ${it.name} was successfully created.")
//}
//// finalPerson is still the Person object, NOT the println result.

//}

// 92. Protected Modifier
//  It will give the access to the class itself, if the class is inherite that can be access,
//  outside the class is not possible to access.
//
//open class Parent {
//    protected val protectedProperty = "Protected Property"
//}
//
//class Child : Parent() {
//    fun printProtectedProperty() {
//        println(protectedProperty) // Accessible in subclasses
//    }
//}
//
//class Other {
//    fun printProtectedProperty() {
//        val parent = Parent()
////        println(parent.protectedProperty) // Not accessible outside the class hierarchy
//    }
//}
//
//fun main() {
//    val child = Child()
//    child.printProtectedProperty() // Output: Protected Property
//}


// 85.  first / firstorNull
//
////  It is used to handle the empty collection.
//fun main(){
//    val li = listOf<Int>(10,32,44,2,12,4)
//    val st = setOf<Int>()
//
//    val y = st.firstOrNull()
//    val x = li.first()
//    println(x)
//    println(y)
//}

// 27. Exception Handling

//fun main() {
//    val dividend = 10
//    val divisor = 0
//
//    try {
//        println("Calculating division...")
//        val result = dividend / divisor // 👈 This throws an ArithmeticException
//        println("Result is $result")   // This line is skipped entirely
//    }
//    catch (e: ArithmeticException) {
//        // This block catches and handles the error safely
//        println("❌ Error Caught: You cannot divide a number by zero!")
//        println("Details: ${e.message}")
//    }
//    finally {
//        // This block ALWAYS executes no matter what
//        println("🧹 Cleanup: Division operation sequence completed.")
//    }
//}


// 23. Object Expression.

//an object expression is used to create an anonymous class on the fly.
// there are 3 types of use case
// Modifing/ Extending a class on fly

//open class MouseAdapter {
//    open fun mouseClicked() = println("Mouse clicked default action")
//}
//
//fun main() {
//    // Creating a one-off instance that overrides behavior
//    val customListener = object : MouseAdapter() {
//        override fun mouseClicked() {
//            println("💥 Boom! Custom mouse click logic executed!")
//        }
//    }
//
//    customListener.mouseClicked() // Output: 💥 Boom! Custom mouse click logic executed!
//}


//implementating interface directly

//interface ClickListener {
//    fun onClick()
//}
//
//class Button {
//    private var listener: ClickListener? = null
//
//    fun setOnClickListener(newListener: ClickListener) {
//        listener = newListener
//    }
//
//    fun simulateClick() = listener?.onClick()
//}
//
//fun main() {
//    val loginButton = Button()
//
//    // Using an object expression to implement the ClickListener interface
//    loginButton.setOnClickListener(object : ClickListener {
//        override fun onClick() {
//            println("User clicked the login button. Processing credentials...")
//        }
//    })
//
//    loginButton.simulateClick() // Output: User clicked the login button...
//}


// Pure Anonymous Objects
//
//fun main() {
//    // A pure anonymous object holding ad-hoc data
//    val temporaryUser = object {
//        val name: String = "Manjurin"
//        val role: String = "Administrator"
//        fun printInfo() = println("$name is logged in as $role")
//    }
//
//    temporaryUser.printInfo() // Output: Manjurin is logged in as Administrator
//}
