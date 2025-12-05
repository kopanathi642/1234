package com.example.fitx

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout // Import this!
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    // 1. Declare Firebase and Google Sign In variables
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        // 2. Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // 3. Configure Google Sign In
        // Make sure your google-services.json is in the /app folder for this to work
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            // Paste the Client ID you found in your JSON file directly here:
            .requestIdToken("YOUR_CLIENT_ID_NUMBERS.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // --- INITIALIZE VIEWS ---
        val emailEditText = findViewById<EditText>(R.id.etEmail)

        // Note: In your XML, this is a TextInputEditText, so we cast it accordingly
        val passwordEditText = findViewById<TextInputEditText>(R.id.etPassword)

        val loginButton = findViewById<Button>(R.id.btnLogin)
        val forgotPasswordText = findViewById<TextView>(R.id.tvForgotPassword)

        // *** IMPORTANT FIX HERE ***
        // Your XML defines this as a LinearLayout, not a Button.
        val googleButton = findViewById<LinearLayout>(R.id.btnGoogleSignIn)

        // --- EXISTING EMAIL LOGIN LOGIC ---
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else if (email == "kumar" && password == "1") {
                // Hardcoded check for testing
                navigateToNextScreen()
            } else {
                // To use real Firebase Email login, uncomment this:
                /*
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                             navigateToNextScreen()
                        } else {
                             Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
                */
                Toast.makeText(this, "Invalid credentials (Hardcoded check failed)", Toast.LENGTH_SHORT).show()
            }
        }

        // --- GOOGLE SIGN IN LOGIC ---
        googleButton.setOnClickListener {
            signInWithGoogle()
        }

        // --- FORGOT PASSWORD ---
        forgotPasswordText.setOnClickListener {
            Toast.makeText(this, "Forgot Password clicked!", Toast.LENGTH_SHORT).show()
            // intent to ForgotPasswordActivity could go here
        }
    }

    // 4. Launch the Google Sign In Intent
    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    // 5. Handle the Result (Modern Way)
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Common error: 12500 (SHA-1 not added to Firebase Console)
                Toast.makeText(this, "Google sign in failed: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 6. Authenticate with Firebase using the Google Token
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    val user = auth.currentUser
                    Toast.makeText(this, "Welcome ${user?.displayName}", Toast.LENGTH_SHORT).show()
                    navigateToNextScreen()
                } else {
                    // If sign in fails
                    Toast.makeText(this, "Firebase Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToNextScreen() {
        val intent = Intent(this, GenderSelectActivity::class.java)
        // Clear back stack so user cannot go back to login screen
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}