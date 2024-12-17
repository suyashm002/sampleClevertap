package com.suyash.loginexample.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.clevertap.android.sdk.CleverTapAPI
import java.util.Date

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(LocalContext.current)

    fun handleLogin() {
        val profileUpdate = HashMap<String, Any>().apply {
            put("Name", username)
            put("Identity", "61026032")
            put("Email", "jack@gmail.com")
            put("Phone", "+14155551234")
            put("Gender", "M")
            put("DOB", Date())

            // Notification preferences
            put("MSG-email", false)
            put("MSG-push", true)
            put("MSG-sms", false)
            put("MSG-whatsapp", true)
// Arrays
            put("MyStuff", arrayListOf("bag", "shoes"))
            // or
            put("MyStuff", arrayOf("Jeans", "Perfume"))
        }

        // Send to CleverTap
        clevertapDefaultInstance?.onUserLogin(profileUpdate)

        // Navigate to next screen
        navController.navigate("home")
    }
            Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = if (passwordVisible) 
                VisualTransformation.None 
            else 
                PasswordVisualTransformation()
        )

        Button(
            onClick = { handleLogin()},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp)
        ) {
            Text("Login")
        }

        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?")
            TextButton(onClick = { navController.navigate("signup") }) {
                Text("Sign Up")
            }
        }
    }
}