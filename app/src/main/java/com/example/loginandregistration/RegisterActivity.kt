package com.example.loginandregistration

import android.content.Intent
import android.media.Rating
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner = findViewById(R.id.custom_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            this,
            R.array.roles_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }

        val reg_btn = findViewById<Button>(R.id.btn_register)
        val login_btn = findViewById<Button>(R.id.btn_login)
        val user = findViewById<EditText>(R.id.et_user_verify)
        val pass = findViewById<EditText>(R.id.et_pass_verify)
        val email = findViewById<EditText>(R.id.et_email_verify)
        val rating = findViewById<RatingBar>(R.id.rb_id)
        val c_spinner = findViewById<Spinner>(R.id.custom_spinner)

        reg_btn.setOnClickListener {

            val username = user.text.toString().trim()
            val password = pass.text.toString().trim()
            val userEmail = email.text.toString().trim()
            val userRole = c_spinner.selectedItem.toString()
            val userRating = rating.rating

            if (username.isNotEmpty() && password.isNotEmpty() && userEmail.isNotEmpty() && userRole.isNotEmpty()) {

                var sharedPref = getSharedPreferences("UserData",MODE_PRIVATE)
                val editor = sharedPref.edit()

                editor.putString("username", username)
                editor.putString("password", password)
                editor.putString("email", userEmail)
                editor.putString("role", userRole)
                editor.putFloat("rating", userRating)

                editor.apply()

                Toast.makeText(this, "Registration Successful for $username with Role $userRole", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        login_btn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}