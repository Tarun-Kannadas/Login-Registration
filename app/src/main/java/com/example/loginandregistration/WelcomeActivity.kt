package com.example.loginandregistration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class WelcomeActivity : AppCompatActivity() {

    private val TAG = "LifecycleDemo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d(TAG, "onCreate: Activity is being created")

        val user_display = findViewById<TextView>(R.id.user)
        val role_display = findViewById<TextView>(R.id.user_role)

        val sharedPref = getSharedPreferences("UserData",MODE_PRIVATE)

        val getUser = sharedPref.getString("username", null)
        val getRole = sharedPref.getString("role",null)

        user_display.text = getUser
        role_display.text = getRole
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity is becoming visible")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity is in the foreground and interactive")
    }

}