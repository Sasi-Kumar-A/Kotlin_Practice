//package Fundamental
//
//sealed class Result{
//    data class Success(val name : String) : Result()
//    data class Failure(val failure : String) : Result()
//    object Error : Result()
//}
//
//
//fun main(){
//    fun getresult(result : Result){
//        when(result){
//            is Result.Success -> {
//                println("Success: ${result.name}")
//            }
//            is Result.Failure -> println("Failure: ${result.failure}")
//
//            Result.Error -> println("Error: ....")
//        }
//    }
//
//    Result.Success("Hello")
//}


// 1. Define the restricted hierarchy using the 'sealed' modifier
sealed class NetworkResult {
    // A subclass that only represents a state (no unique data needed)
    object Loading : NetworkResult()

    // A subclass that holds data on success
    data class Success(val data: String) : NetworkResult()

    // A subclass that holds an exception on failure
    data class Failure(val exception: Exception) : NetworkResult()
}

fun renderUI(result: NetworkResult) {
    // 2. The magic happens here with 'when' expressions!
    // Because NetworkResult is sealed, Kotlin knows there are ONLY 3 possible types.
    // You do NOT need an 'else' branch!
    when (result) {
        is NetworkResult.Loading -> {
            println("Showing progress bar...")
        }
        is NetworkResult.Success -> {
            println("Data loaded successfully: ${result.data}")
        }
        is NetworkResult.Failure -> {
            println("Error occurred: ${result.exception.message}")
        }
    }
}

fun main() {
    val currentStatus = NetworkResult.Failure(Exception("DataNotFound"))
    renderUI(currentStatus) // Output: Data loaded successfully: Welcome to Kotlin!
}
