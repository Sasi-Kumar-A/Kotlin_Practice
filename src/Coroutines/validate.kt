package Coroutines

data class Student (
    var name: String = "",
    var dept: String ="",
    var regno: String = "",
    var phno:String = "",
    var email: String ="",
    var sub: String = ""
)

fun handleName(student: Student){
    while(true){
        println("Enter Name (letters and spaces only): ")
        val input = readlnOrNull()?.trim().orEmpty()

        val isValid = input.isNotBlank() && input.all { it.isLetter() || it.isWhitespace() }
        if(isValid){
            val name = input
        }
    }
}
