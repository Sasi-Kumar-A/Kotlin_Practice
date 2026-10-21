package Fundamental

data class Student(
    val name: String ,
    val age : Int,
    val phno : Long,
    val email : String,
    val activeStatus : Boolean
)

fun main(){
    println("Fill the form...")
    println("Enter Name")
    val name = readln()

    println("Enter Age")
    val age = readln().toInt() // to

    println("Enter Phone Number")
    val phno = readln().toLong()

    println("Enter Email")
    val email = readln()

    println("Enter Active Status")
    val activeStatus  = readln()

    val details = Student(
        name = name,
        age = age,
        phno = phno,
        email = email,
        activeStatus = activeStatus.toBoolean()
    )
    println(details)
}

