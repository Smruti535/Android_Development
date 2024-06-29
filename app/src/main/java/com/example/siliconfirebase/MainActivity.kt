package com.example.siliconfirebase

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.siliconfirebase.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.firestore
import java.util.concurrent.TimeUnit
import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }



    val auth = FirebaseAuth.getInstance()
    val firebaseDB = Firebase.firestore
    var storedVerificationId: String? = null
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    fun signUP(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    println(user)
                } else {
                    println("User couldn't be created")
                }

            }
    }

    fun signIN(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    println("User logged In ${user}")
                } else {
                    println("User couldn't logged in")
                    println(task.exception?.message)
                }

            }
    }


    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    fun startPhoneNumberVerification(phoneNumber: String){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyWithCode(code: String){
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }
    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    fun addUserToFirebaseDB(name: String, age: Int){
        val isAdult = age>=18
        val firebaseUser =FirebaseUser(name, age, isAdult)
        firebaseDB.collection("users")
            .add(firebaseUser)
            .addOnSuccessListener { dRef ->
                Log.d(TAG, "Document added with ID: ${dRef.id}")
            }
            .addOnFailureListener{ e ->
                Log.w(TAG, "Document could not be added")
            }
    }

    fun updateUser(user: FirebaseUser, newUser: FirebaseUser) {
        firebaseDB.collection("users").document(user.userId)
            .update(
                mapOf(
                    "name" to newUser.name,
                    "age" to newUser.age,
                    "adultOrNot" to (newUser.age>=18)
                )
            )
            .addOnSuccessListener {
                Log.d(TAG, "Successfully updated")
            }
            .addOnFailureListener {
                Log.w(TAG, "Failed to update: ${it.message}")
            }

    }

    fun deleteUser(user: FirebaseUser){
        firebaseDB.collection("users").document(user.userId)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "Successfully deleted")
            }
            .addOnFailureListener {
                Log.w(TAG, "Unsuccessful delete operation")
            }
    }

    fun fetchFirebaseUsers( onResult: (List<FirebaseUser>)->Unit ){
        firebaseDB.collection("users")
            .get()
            .addOnSuccessListener { result ->
                val usersList = result.map{ document ->
                    document.toObject(FirebaseUser::class.java)
                    val user = document.toObject(FirebaseUser::class.java)
                    user.copy(userId=document.id)
                }
                onResult(usersList)
            }
            .addOnFailureListener{ e->
                Log.w(TAG, "Error getting data", e)
            }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "addUser") {
            composable("signup") { SignUpScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("otp") { OTPScreen(navController) }
            composable("addUser") { AddUserScreen(navController) }
            composable("allUsers") { AllUserScreen(navController) }
            //composable("updateUser") { UpdateUserScreen(navController) }
        }
    }

    @Composable
    fun SignUpScreen(navController: NavController) {
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email.value, onValueChange = {
                    email.value = it
                },
                label = { Text(text = "Enter Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password.value, onValueChange = {
                    password.value = it
                },
                label = { Text(text = "Enter Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { signUP(email.value, password.value) }) {
                Text(text = "SignUP")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Text(text = "Already have an account? ")
                Text(text = "Login!", modifier = Modifier.clickable {
                    navController.navigate("login")
                }, color = Color.Blue, textDecoration = TextDecoration.Underline)
            }
        }
    }

    @Composable
    fun LoginScreen(navController: NavController) {
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email.value, onValueChange = {
                    email.value = it
                },
                label = { Text(text = "Enter Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password.value, onValueChange = {
                    password.value = it
                },
                label = { Text(text = "Enter Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                signIN(email.value, password.value)
            }) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Text(text = "Don't have an account? ")
                Text(text = "Sign Up!", modifier = Modifier.clickable {
                    navController.navigate("signup")
                }, color = Color.Blue, textDecoration = TextDecoration.Underline)
            }
        }
    }

    @Composable
    fun OTPScreen(navController: NavController){
        val phoneNumber = remember { mutableStateOf("") }
        val otpCode = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = phoneNumber.value, onValueChange = {
                    phoneNumber.value = it
                },
                label = { Text(text = "Enter Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                startPhoneNumberVerification(phoneNumber.value)
            }) {
                Text(text = "Send OTP")
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = otpCode.value, onValueChange = {
                    otpCode.value = it
                },
                label = { Text(text = "Enter OTP") },
                modifier = Modifier.fillMaxWidth(),

            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(onClick = {
                verifyWithCode(otpCode.value)
            }) {
                Text(text = "Verify OTP")
            }
        }
    }

    @Composable
    fun AddUserScreen(navController: NavController){
        val name = remember { mutableStateOf("") }
        val age = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name.value, onValueChange = {
                    name.value = it
                },
                label = { Text(text = "Enter Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = age.value, onValueChange = {
                    age.value = it
                },
                label = { Text(text = "Enter Age") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
               addUserToFirebaseDB(name.value, age.value.toInt())
                name.value = ""
                age.value = ""
            }) {
                Text(text = "Add User")
            }
            Button(onClick = {
                navController.navigate("allUsers")
            }) {
                Text(text = "Show all users")
            }

        }
    }

    @Composable
    fun UpdateUserDialog(
        user: FirebaseUser, onDismiss: () -> Unit,
        onUpdate: (String, Int) -> Unit
    ) {
        var name by remember { mutableStateOf(user.name) }
        var age by remember { mutableStateOf(user.age.toString()) }

        AlertDialog(onDismissRequest = onDismiss, confirmButton = {  Button(
            onClick = {
                onUpdate(name, age.toIntOrNull() ?: user.age)
            }
        ) {
            Text("Update")
        }
        }, text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        })
    }

    @Composable
    fun AllUserScreen(navController: NavController){
        val userList = remember {
            mutableStateOf<List<FirebaseUser>>(emptyList())
        }
        var selectedUser = remember {
            mutableStateOf<FirebaseUser?>(null)
        }
        var showDialog = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(Unit) {
            fetchFirebaseUsers { users ->
                userList.value = users
            }
        }

        if (showDialog.value && selectedUser.value != null) {
            UpdateUserDialog(
                user = selectedUser.value!!,
                onDismiss = { showDialog.value = false },
                onUpdate = { name, age ->
                    updateUser(selectedUser.value!!, FirebaseUser(name, age))
                    showDialog.value = false
                    fetchFirebaseUsers {
                        userList.value=it
                    }
                }
            )
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ){
            items(userList.value){ user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Name: ${user.name},\nAge: ${user.age}",
                            fontSize = 30.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                        Column() {
                            Button(onClick = {
                                selectedUser.value = user
                                showDialog.value = true
                            }) {
                                Text(text = "Update")
                            }
                            Button(onClick = {
                                deleteUser(user)
                                fetchFirebaseUsers {
                                    userList.value = it
                                }
                            }) {
                                Text(text = "Delete")
                            }
                        }
                    }
                }
            }
        }
    }




}




