package Debuging

data class BankAccount(
    val accountId: String,
    val ownerName: String,
    var balance: Double,
    var isActive: Boolean = true
)

class BankService {
    val accounts = mutableListOf<BankAccount>()

    fun addAccount(account: BankAccount) {
        accounts.add(account)
    }

    /**
     * Deactivates all accounts with zero or negative balance.
     */
    fun removeInactiveAccounts() {
        // BUG #1 HINT: Modifying a MutableList while iterating over it with forEach
        // will throw a ConcurrentModificationException at runtime.
        for (acc in accounts) {
            if (acc.balance <= 0.0) {
                accounts.remove(acc)
            }
        }
    }

    /**
     * Looks up an account by ID.
     */
    fun findAccount(targetId: String): BankAccount? {
        // BUG #2 HINT: Notice how trimming, casing, or string comparison affects lookups.
        for (acc in accounts) {
            if (acc.accountId == targetId) {
                return acc
            }
        }
        return null
    }

    /**
     * Calculates transfer fees based on basis points.
     * 50 basis points = 0.5% (50 / 10000).
     */
    fun calculateTransferFee(amount: Double, basisPoints: Int): Double {
        // BUG #3 HINT: Look closely at integer division here.
        // What is (basisPoints / 10000) when basisPoints is 50?
        val feeRate = (basisPoints / 10000).toDouble()
        return amount * feeRate
    }

    /**
     * Transfers money between two accounts.
     */
    fun transferFunds(senderId: String, receiverId: String, amount: Double, feeBasisPoints: Int): Boolean {
        val sender = findAccount(senderId)
        if (sender == null || !sender.isActive) {
            println("Transfer failed: Invalid sender.")
            return false
        }

        val fee = calculateTransferFee(amount, feeBasisPoints)
        val totalDebit = amount + fee

        if (sender.balance < totalDebit) {
            println("Transfer failed: Insufficient funds.")
            return false
        }

        // BUG #4 HINT: Sender's balance is deducted BEFORE confirming recipient exists.
        // If recipient fails, money vanishes without rollback!
        sender.balance -= totalDebit

        val receiver = findAccount(receiverId)
        if (receiver == null || !receiver.isActive) {
            println("Transfer failed: Receiver account '$receiverId' not found.")
            return false
        }

        receiver.balance += amount
        println("Transfer successful: $$amount transferred from ${sender.ownerName} to ${receiver.ownerName} (Fee: $$fee).")
        return true
    }
}

fun main() {
    val bank = BankService()

    // Setup accounts
    val alice = BankAccount("ACC_101", "Alice", 500.0)
    val bob = BankAccount("ACC_102", "Bob", 150.0)
    val emptyAccount = BankAccount("ACC_103", "Charlie", 0.0)

    bank.addAccount(alice)
    bank.addAccount(bob)
    bank.addAccount(emptyAccount)

    println("=== Scenario 1: Calculating Fee & Transfer ===")
    // Testing fee: 50 basis points on $100 should be $0.50 fee
    val fee = bank.calculateTransferFee(100.0, 50)
    println("Calculated fee: $$fee (Expected: $0.50)")

    println("\n=== Scenario 2: Transfer to a Misspelled/Missing Account ===")
    // Receiver ID has lowercase "acc_102 " with accidental trailing space
    println("Alice balance before: $${alice.balance}")
    bank.transferFunds("ACC_101", "acc_102 ", 100.0, 50)
    println("Alice balance after: $${alice.balance} (Did Alice lose money even though transfer failed?)")

    println("\n=== Scenario 3: Purging Inactive Accounts ===")
    // Remove accounts with <= 0 balance
    bank.removeInactiveAccounts()
    println("Remaining accounts count: ${bank.accounts.size}")
}