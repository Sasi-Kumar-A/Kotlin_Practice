package Fundamental

data class Employee(
    var name: String = "",
    var age: Int = 0,
    var email: String = "",
    var phoneNumber: String = "",
    var address: String = "",
    var salary: Double = 0.0
)

// 1. Name handler: reads input, validates, and sets into the model
fun handleName(employee: Employee) {
    while (true) {
        print("Enter Name (letters and spaces only): ")
        val input = readlnOrNull()?.trim().orEmpty()

        val isValid = input.isNotBlank() && input.all { it.isLetter() || it.isWhitespace() }
        if (isValid) {
            employee.name = input
            println("✓ Name recorded successfully.\n")
            break
        } else {
            println("✗ Invalid name. Name cannot be empty and must contain only letters.\n")
        }
    }
}

// 2. Age handler: reads input, validates range, and sets into the model
fun handleAge(employee: Employee) {
    while (true) {
        print("Enter Age (18 to 65): ")
        val input = readlnOrNull()?.trim()
        val parsedAge = input?.toIntOrNull()

        val isValid = parsedAge != null && parsedAge in 18..65
        if (isValid) {
            employee.age = parsedAge!!
            println("✓ Age recorded successfully.\n")
            break
        } else {
            println("✗ Invalid age. Please enter a valid number between 18 and 65.\n")
        }
    }
}

// 3. Email handler: reads input, validates pattern, and sets into the model
fun handleEmail(employee: Employee) {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    while (true) {
        print("Enter Email Address (e.g., user@example.com): ")
        val input = readlnOrNull()?.trim().orEmpty()

        val isValid = input.matches(emailRegex)
        if (isValid) {
            employee.email = input
            println("✓ Email recorded successfully.\n")
            break
        } else {
            println("✗ Invalid email format. Please enter a valid email address.\n")
        }
    }
}

// 4. Phone number handler: reads input, validates 10-digit number, and sets into the model
fun handlePhoneNumber(employee: Employee) {
    val phoneRegex = Regex("^[6-9]\\d{9}\$")
    while (true) {
        print("Enter 10-digit Phone Number: ")
        val input = readlnOrNull()?.trim().orEmpty()

        val isValid = input.matches(phoneRegex)
        if (isValid) {
            employee.phoneNumber = input
            println("✓ Phone number recorded successfully.\n")
            break
        } else {
            println("✗ Invalid phone number. Must be a 10-digit number starting with 6, 7, 8, or 9.\n")
        }
    }
}

// 5. Address handler: reads input, validates min length, and sets into the model
fun handleAddress(employee: Employee) {
    while (true) {
        print("Enter Address (minimum 10 characters): ")
        val input = readlnOrNull()?.trim().orEmpty()

        val isValid = input.length >= 10
        if (isValid) {
            employee.address = input
            println("✓ Address recorded successfully.\n")
            break
        } else {
            println("✗ Invalid address. Address must be at least 10 characters long.\n")
        }
    }
}

// 6. Salary handler: reads input, validates positive decimal number, and sets into the model
fun handleSalary(employee: Employee) {
    while (true) {
        print("Enter Salary (positive number): ")
        val input = readlnOrNull()?.trim()
        val parsedSalary = input?.toDoubleOrNull()

        val isValid = parsedSalary != null && parsedSalary > 0.0
        if (isValid) {
            employee.salary = parsedSalary!!
            println("✓ Salary recorded successfully.\n")
            break
        } else {
            println("✗ Invalid salary. Please enter a numeric amount greater than 0.\n")
        }
    }
}

fun printEmployeeDetails(employee: Employee) {
    println("==========================================")
    println("        REGISTERED EMPLOYEE DETAILS       ")
    println("==========================================")
    println("Name         : ${employee.name}")
    println("Age          : ${employee.age}")
    println("Email        : ${employee.email}")
    println("Phone Number : ${employee.phoneNumber}")
    println("Address      : ${employee.address}")
    println("Salary       : ₹%,.2f".format(employee.salary))
    println("==========================================")
}

fun main() {
    println("=== Terminal Employee Registration Form ===\n")
    val employee = Employee()

    // Invoking each field handler sequentially
    handleName(employee)
    handleAge(employee)
    handleEmail(employee)
    handlePhoneNumber(employee)
    handleAddress(employee)
    handleSalary(employee)

    // Output all stored data
    printEmployeeDetails(employee)
}