package com.example.loginandregistration.utils

import android.app.Activity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.loginandregistration.R

fun Toast.showCustomToast(message:String, activity: Activity)
{
    val toastLayout = activity.layoutInflater.inflate(
        R.layout.custom_toast_layout,
        activity.findViewById(R.id.id_custom_layout)
    )

    val textToast = toastLayout.findViewById<TextView>(R.id.tv_custom_toast)
    textToast.text = message

    val icon = toastLayout.findViewById<ImageView>(R.id.iv_custom_toast)
    icon.setImageResource(R.drawable.notification)

    this.apply{
        setGravity(Gravity.BOTTOM,0,100)
        duration = Toast.LENGTH_SHORT
        view = toastLayout
        show()
    }
}