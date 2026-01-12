package com.example.loginandregistration

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.loginandregistration.utils.showCustomToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity()
{

    val EXTRA_MESSAGE = "com.example.loginandregistration.MESSAGE"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val login_btn = findViewById<Button>(R.id.btn_login)
        val register_btn = findViewById<Button>(R.id.btn_register)

        val username = findViewById<EditText>(R.id.et_username)
        val password = findViewById<EditText>(R.id.et_password)

        login_btn.setOnClickListener {
            val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)

            val savedUser = sharedPref.getString("username",null)
            val savedPass = sharedPref.getString("password", null)

            val inputUser = username.text.toString().trim()
            val inputPass = password.text.toString().trim()

            if (savedUser == null || savedPass == null)
            {
                Toast.makeText(this,"No Registered User found!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(inputUser == savedUser && inputPass == savedPass)
            {
                lifecycleScope.launch {
                    delay(2000)

                    Toast(this@MainActivity).showCustomToast(
                        "Welcome $savedUser", this@MainActivity
                    )

                    startActivity(Intent(this@MainActivity, WelcomeActivity::class.java).putExtra(EXTRA_MESSAGE, savedUser))
                    finish()
                }
            }
            else
            {
                Toast.makeText(this,"Invalid Creds!", Toast.LENGTH_SHORT).show()
            }
        }

        register_btn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}