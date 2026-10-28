package Coroutines

import kotlinx.coroutines.*
import java.sql.SQLException

// Mock PostgreSQL Exception to simulate a real driver error without needing a live database connection
class PSQLException(message: String, val sqlState: String) : SQLException(message)

// 1. Define our structural Result wrapper for the database operations
sealed interface DbResult<out T> {
    object Loading : DbResult<Nothing>
    data class Success<out T>(val data: T) : DbResult<T>
    data class Failure(val errorMessage: String, val sqlState: String?) : DbResult<Nothing>
}

// 2. Repository simulating PostgreSQL Interactions
class UserRepository {

    // Simulates an insert query. If shouldFail is true, it throws a mock duplicate key error.
    suspend fun insertUser(username: String, shouldFail: Boolean): DbResult<String> = withContext(Dispatchers.IO) {
        try {
            if (shouldFail) {
                // Simulating PostgreSQL Error Code 23505: unique_violation
                throw PSQLException("duplicate key value violates unique constraint \"users_username_key\"", "23505")
            }

            // Simulating successful query execution
            delay(500)
            return@withContext DbResult.Success("User '$username' successfully registered into PostgreSQL!")

        } catch (e: PSQLException) {
            // Target specific PostgreSQL state errors
            val userFriendlyMessage = when (e.sqlState) {
                "23505" -> "Registration failed: The username '$username' is already taken."
                "23503" -> "Foreign key violation: Referenced record does not exist."
                "57014" -> "Database query was cancelled due to a timeout."
                else -> "Database internal error: ${e.localizedMessage}"
            }
            return@withContext DbResult.Failure(userFriendlyMessage, e.sqlState)

        } catch (e: SQLException) {
            // General SQL connections issues (e.g., lost connection to server)
            return@withContext DbResult.Failure("Database connectivity issue: ${e.localizedMessage}", null)
        } catch (e: Exception) {
            // Catch-all for mapping errors or null pointers
            return@withContext DbResult.Failure("Unexpected system error: ${e.localizedMessage}", null)
        }
    }
}

// 3. Main execution block to demonstrate both cases
fun main() = runBlocking {
    val repository = UserRepository()

    println("--- CASE 1: Running WITHOUT Error ---")
    val successJob = launch {
        println("⏳ Status: Processing registration...")

        // Execute clean insert
        when (val result = repository.insertUser("alice_dev", shouldFail = false)) {
            is DbResult.Success -> println("✅ Result: ${result.data}")
            is DbResult.Failure -> println("❌ Result: Failed with message: ${result.errorMessage}")
            else -> {}
        }
    }
    successJob.join() // Wait for case 1 to finish

    println("\n--- CASE 2: Running WITH PostgreSQL SQL Error ---")
    val failureJob = launch {
        println("⏳ Status: Processing registration...")

        // Execute duplicate insert to force a Unique Constraint Violation
        when (val result = repository.insertUser("alice_dev", shouldFail = true)) {
            is DbResult.Success -> println("✅ Result: ${result.data}")
            is DbResult.Failure -> {
                println("❌ Result: Failed explicitly!")
                println("   ↳ Message   : ${result.errorMessage}")
                println("   ↳ SQL State : ${result.sqlState}")
            }
            else -> {}
        }
    }
    failureJob.join() // Wait for case 2 to finish
}
