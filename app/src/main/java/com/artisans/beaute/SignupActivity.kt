package com.artisans.beaute

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val firstNameEditText: EditText = findViewById(R.id.FirstName)
        val lastNameEditText: EditText = findViewById(R.id.LastName)
        val emailEditText: EditText = findViewById(R.id.Email)
        val addressEditText: EditText = findViewById(R.id.Address)
        val passwordEditText: EditText = findViewById(R.id.Password)
        val signUpButton: Button = findViewById(R.id.SignUpButton)
        val loginTextView: TextView = findViewById(R.id.LoginTextView)

        signUpButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                navigateToLogin()
            }
        }

        loginTextView.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
