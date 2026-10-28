//package Fundamental
//
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Window
//import androidx.compose.ui.window.application
//
//// Immutable model for stored table records
//data class EmployeeRecord(
//    val id: Int,
//    val name: String,
//    val age: Int,
//    val email: String,
//    val phoneNumber: String,
//    val address: String,
//    val salary: Double
//)
//
//// -------------------------------------------------------------
//// Separate Validation Handlers for each field
//// -------------------------------------------------------------
//
//fun validateName(value: String): Pair<Boolean, String> {
//    val trimmed = value.trim()
//    return when {
//        trimmed.isBlank() -> false to "Name cannot be empty."
//        !trimmed.all { it.isLetter() || it.isWhitespace() } -> false to "Name must contain only letters."
//        else -> true to ""
//    }
//}
//
//fun validateAge(value: String): Pair<Boolean, String> {
//    val parsed = value.trim().toIntOrNull()
//    return when {
//        parsed == null -> false to "Age must be a valid number."
//        parsed !in 18..65 -> false to "Age must be between 18 and 65."
//        else -> true to ""
//    }
//}
//
//fun validateEmail(value: String): Pair<Boolean, String> {
//    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
//    return when {
//        !value.trim().matches(emailRegex) -> false to "Enter a valid email address."
//        else -> true to ""
//    }
//}
//
//fun validatePhoneNumber(value: String): Pair<Boolean, String> {
//    val phoneRegex = Regex("^[6-9]\\d{9}\$")
//    return when {
//        !value.trim().matches(phoneRegex) -> false to "Enter a 10-digit number starting with 6-9."
//        else -> true to ""
//    }
//}
//
//fun validateAddress(value: String): Pair<Boolean, String> {
//    val trimmed = value.trim()
//    return when {
//        trimmed.length < 10 -> false to "Address must be at least 10 characters long."
//        else -> true to ""
//    }
//}
//
//fun validateSalary(value: String): Pair<Boolean, String> {
//    val parsed = value.trim().toDoubleOrNull()
//    return when {
//        parsed == null || parsed <= 0.0 -> false to "Salary must be a positive number."
//        else -> true to ""
//    }
//}
//
//// -------------------------------------------------------------
//// UI Layout
//// -------------------------------------------------------------
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FormValidationApp() {
//    // Input field values
//    var nameInput by remember { mutableStateOf("") }
//    var ageInput by remember { mutableStateOf("") }
//    var emailInput by remember { mutableStateOf("") }
//    var phoneInput by remember { mutableStateOf("") }
//    var addressInput by remember { mutableStateOf("") }
//    var salaryInput by remember { mutableStateOf("") }
//
//    // Error messages per field
//    var nameError by remember { mutableStateOf("") }
//    var ageError by remember { mutableStateOf("") }
//    var emailError by remember { mutableStateOf("") }
//    var phoneError by remember { mutableStateOf("") }
//    var addressError by remember { mutableStateOf("") }
//    var salaryError by remember { mutableStateOf("") }
//
//    // Table rows state
//    val employeeList = remember { mutableStateListOf<EmployeeRecord>() }
//
//    MaterialTheme {
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = { Text("Form Validation", fontWeight = FontWeight.Bold) },
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = MaterialTheme.colorScheme.primaryContainer,
//                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
//                    )
//                )
//            }
//        ) { paddingValues ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp)
//                    .verticalScroll(rememberScrollState()),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                // Input Form Card
//                Card(
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
//                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
//                ) {
//                    Column(
//                        modifier = Modifier.padding(16.dp).fillMaxWidth()
//                    ) {
//                        Text(
//                            text = "Add New Employee",
//                            style = MaterialTheme.typography.titleMedium,
//                            fontWeight = FontWeight.SemiBold,
//                            modifier = Modifier.padding(bottom = 8.dp)
//                        )
//
//                        // 1. Name Field
//                        OutlinedTextField(
//                            value = nameInput,
//                            onValueChange = { nameInput = it; nameError = "" },
//                            label = { Text("Name") },
//                            isError = nameError.isNotEmpty(),
//                            supportingText = { if (nameError.isNotEmpty()) Text(nameError) },
//                            singleLine = true,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//
//                        // 2. Age Field
//                        OutlinedTextField(
//                            value = ageInput,
//                            onValueChange = { ageInput = it; ageError = "" },
//                            label = { Text("Age (18-65)") },
//                            isError = ageError.isNotEmpty(),
//                            supportingText = { if (ageError.isNotEmpty()) Text(ageError) },
//                            singleLine = true,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//
//                        // 3. Email Field
//                        OutlinedTextField(
//                            value = emailInput,
//                            onValueChange = { emailInput = it; emailError = "" },
//                            label = { Text("Email Address") },
//                            isError = emailError.isNotEmpty(),
//                            supportingText = { if (emailError.isNotEmpty()) Text(emailError) },
//                            singleLine = true,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//
//                        // 4. Phone Number Field
//                        OutlinedTextField(
//                            value = phoneInput,
//                            onValueChange = { phoneInput = it; phoneError = "" },
//                            label = { Text("10-Digit Phone Number") },
//                            isError = phoneError.isNotEmpty(),
//                            supportingText = { if (phoneError.isNotEmpty()) Text(phoneError) },
//                            singleLine = true,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//
//                        // 5. Address Field
//                        OutlinedTextField(
//                            value = addressInput,
//                            onValueChange = { addressInput = it; addressError = "" },
//                            label = { Text("Address (Min 10 chars)") },
//                            isError = addressError.isNotEmpty(),
//                            supportingText = { if (addressError.isNotEmpty()) Text(addressError) },
//                            singleLine = true,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//
//                        // 6. Salary Field
//                        OutlinedTextField(
//                            value = salaryInput,
//                            onValueChange = { salaryInput = it; salaryError = "" },
//                            label = { Text("Salary (e.g. 50000)") },
//                            isError = salaryError.isNotEmpty(),
//                            supportingText = { if (salaryError.isNotEmpty()) Text(salaryError) },
//                            singleLine = true,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        // Submit Button
//                        Button(
//                            onClick = {
//                                val (nameValid, nErr) = validateName(nameInput)
//                                val (ageValid, aErr) = validateAge(ageInput)
//                                val (emailValid, eErr) = validateEmail(emailInput)
//                                val (phoneValid, pErr) = validatePhoneNumber(phoneInput)
//                                val (addrValid, adErr) = validateAddress(addressInput)
//                                val (salaryValid, sErr) = validateSalary(salaryInput)
//
//                                nameError = nErr
//                                ageError = aErr
//                                emailError = eErr
//                                phoneError = pErr
//                                addressError = adErr
//                                salaryError = sErr
//
//                                if (nameValid && ageValid && emailValid && phoneValid && addrValid && salaryValid) {
//                                    employeeList.add(
//                                        EmployeeRecord(
//                                            id = employeeList.size + 1,
//                                            name = nameInput.trim(),
//                                            age = ageInput.trim().toInt(),
//                                            email = emailInput.trim(),
//                                            phoneNumber = phoneInput.trim(),
//                                            address = addressInput.trim(),
//                                            salary = salaryInput.trim().toDouble()
//                                        )
//                                    )
//
//                                    // Reset fields after successful entry
//                                    nameInput = ""
//                                    ageInput = ""
//                                    emailInput = ""
//                                    phoneInput = ""
//                                    addressInput = ""
//                                    salaryInput = ""
//                                }
//                            },
//                            modifier = Modifier.align(Alignment.End)
//                        ) {
//                            Text("Print / Add to Table")
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // Table Header / Display Section
//                Text(
//                    text = "Submitted Records (${employeeList.size})",
//                    style = MaterialTheme.typography.titleMedium,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.align(Alignment.Start).padding(start = 8.dp, bottom = 8.dp)
//                )
//
//                // Scrollable Table
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 8.dp)
//                        .border(1.dp, Color.Gray)
//                        .horizontalScroll(rememberScrollState())
//                ) {
//                    Column {
//                        // Header Row
//                        Row(
//                            modifier = Modifier
//                                .background(MaterialTheme.colorScheme.primaryContainer)
//                                .border(1.dp, Color.LightGray)
//                        ) {
//                            TableCell(text = "ID", width = 50.dp, isHeader = true)
//                            TableCell(text = "Name", width = 140.dp, isHeader = true)
//                            TableCell(text = "Age", width = 70.dp, isHeader = true)
//                            TableCell(text = "Email", width = 200.dp, isHeader = true)
//                            TableCell(text = "Phone Number", width = 140.dp, isHeader = true)
//                            TableCell(text = "Address", width = 220.dp, isHeader = true)
//                            TableCell(text = "Salary", width = 120.dp, isHeader = true)
//                        }
//
//                        // Data Rows
//                        if (employeeList.isEmpty()) {
//                            Row(
//                                modifier = Modifier
//                                    .padding(16.dp)
//                                    .fillMaxWidth(),
//                                horizontalArrangement = Arrangement.Center
//                            ) {
//                                Text("No records added yet. Fill the form and click 'Print / Add to Table'.")
//                            }
//                        } else {
//                            employeeList.forEachIndexed { index, emp ->
//                                val rowBg = if (index % 2 == 0) Color.White else Color(0xFFF9F9F9)
//                                Row(
//                                    modifier = Modifier
//                                        .background(rowBg)
//                                        .border(0.5.dp, Color.LightGray)
//                                ) {
//                                    TableCell(text = emp.id.toString(), width = 50.dp)
//                                    TableCell(text = emp.name, width = 140.dp)
//                                    TableCell(text = emp.age.toString(), width = 70.dp)
//                                    TableCell(text = emp.email, width = 200.dp)
//                                    TableCell(text = emp.phoneNumber, width = 140.dp)
//                                    TableCell(text = emp.address, width = 220.dp)
//                                    TableCell(text = "₹%,.2f".format(emp.salary), width = 120.dp)
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun TableCell(
//    text: String,
//    width: androidx.compose.ui.unit.Dp,
//    isHeader: Boolean = false
//) {
//    Text(
//        text = text,
//        modifier = Modifier
//            .width(width)
//            .padding(10.dp),
//        fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
//        style = MaterialTheme.typography.bodyMedium
//    )
//}
//
//fun main() = application {
//    Window(
//        onCloseRequest = ::exitApplication,
//        title = "Form Validation"
//    ) {
//        FormValidationApp()
//    }
//}