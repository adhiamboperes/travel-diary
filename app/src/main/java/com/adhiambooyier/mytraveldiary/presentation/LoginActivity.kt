package com.adhiambooyier.mytraveldiary.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.adhiambooyier.mytraveldiary.R
import com.adhiambooyier.mytraveldiary.databinding.LoginActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.lifecycleOwner = this

        auth = Firebase.auth

        binding.loginButton.setOnClickListener { loginUser() }

        binding.loginSignupButton.setOnClickListener { signUpUser() }
    }

    private fun loginUser() {
        val email = binding.loginUsernameEditText.text.toString().trim()
        val password = binding.loginPasswordEditText.text.toString().trim()

        if (email.isNotBlank() && password.isNotBlank()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "loginUserWithEmail:success")
                        navigateToHome()
                    } else {
                        Log.w(TAG, "loginUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        } else {
            binding.loginUsernameEditText.error = "Please enter your email"
            binding.loginPasswordEditText.error = "Please enter your password"
        }
    }

    private fun signUpUser() {
        val email = binding.loginUsernameEditText.text.toString().trim()
        val password = binding.loginPasswordEditText.text.toString().trim()

        if (email.isNotBlank() && password.isNotBlank()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        navigateToHome()
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        } else {
            binding.loginUsernameEditText.error = "Email is required"
            binding.loginPasswordEditText.error = "Password is required"
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}
