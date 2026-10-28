package Coroutines

import kotlinx.coroutines.*
import java.net.UnknownHostException
import java.net.SocketTimeoutException

// 1. Mocking HTTP exceptions to keep the script self-contained and runnable
open class ResponseException(val statusCode: Int, message: String) : Exception(message)
class ClientRequestException(statusCode: Int, message: String) : ResponseException(statusCode, message)
class ServerResponseException(statusCode: Int, message: String) : ResponseException(statusCode, message)
class SerializationException(message: String) : Exception(message)

// 2. Structural State wrapper
sealed interface ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>
    data class Failure(val message: String, val code: String) : ApiResult<Nothing>
}

// 3. Repository handling network simulations
class NetworkRepository {

    suspend fun executeApiCall(scenario: String): ApiResult<String> = withContext(Dispatchers.IO) {
        try {
            when (scenario) {
                "SUCCESS" -> {
                    delay(200)
                    return@withContext ApiResult.Success("{ 'status': 'Profile Updated' }")
                }
                "NO_INTERNET" -> throw UnknownHostException("Unable to resolve host: ://service.com")
                "TIMEOUT" -> throw SocketTimeoutException("Read timed out after 5000ms")
                "BAD_REQUEST" -> throw ClientRequestException(400, "Missing parameter: user_id")
                "UNAUTHORIZED" -> throw ClientRequestException(401, "Bearer token expired")
                "SERVER_CRASH" -> throw ServerResponseException(500, "NullPointerException in remote server controller")
                "PARSING_ERROR" -> throw SerializationException("Field 'created_at' expected String but found Int")
                else -> throw Exception("Unknown structural failure")
            }
        } catch (e: UnknownHostException) {
            return@withContext ApiResult.Failure("Network Unavailable: Please verify your cellular or Wi-Fi connection.", "NO_CONNECTION")
        } catch (e: SocketTimeoutException) {
            return@withContext ApiResult.Failure("Network Timeout: The remote server took too long to reply.", "TIMEOUT")
        } catch (e: ClientRequestException) {
            val dynamicMsg = when (e.statusCode) {
                401 -> "Authentication Failure: Please sign out and log back in."
                404 -> "Endpoint Missing: The requested resource could not be found."
                else -> "Invalid Request Params: ${e.localizedMessage}"
            }
            return@withContext ApiResult.Failure(dynamicMsg, "HTTP_${e.statusCode}")
        } catch (e: ServerResponseException) {
            return@withContext ApiResult.Failure("Remote Server Error: The platform engine failed internally. Try again later.", "HTTP_${e.statusCode}")
        } catch (e: SerializationException) {
            return@withContext ApiResult.Failure("Data Corruption: App parsing engine crashed due to dynamic payload structural changes.", "PARSING_ERROR")
        } catch (e: Exception) {
            return@withContext ApiResult.Failure("Unexpected Error: ${e.localizedMessage}", "GENERIC")
        }
    }
}

// 4. Execution Block
fun main() = runBlocking {
    val repo = NetworkRepository()
    val scenarios = listOf("SUCCESS", "NO_INTERNET", "TIMEOUT", "UNAUTHORIZED", "SERVER_CRASH", "PARSING_ERROR")

    scenarios.forEach { currentTest ->
        println("\n--- SIMULATING HTTP/NETWORK EVENT: $currentTest ---")
        val job = launch {
            when (val outcome = repo.executeApiCall(currentTest)) {
                is ApiResult.Success -> println("✅ Response Body Received: ${outcome.data}")
                is ApiResult.Failure -> {
                    println("❌ Request Aborted!")
                    println("   ↳ User Warning    : ${outcome.message}")
                    println("   ↳ Diagnosis Code  : ${outcome.code}")
                }
            }
        }
        job.join()
    }
}
