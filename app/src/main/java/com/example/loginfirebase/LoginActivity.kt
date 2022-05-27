package com.example.loginfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import android.view.Window
import android.view.WindowManager
import android.os.Build
import androidx.annotation.ColorInt

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        //    window.statusBarColorTo(R.color.colorPrimary)
        //}
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun Window.setStatusBarColorTo(color: Int){
        //this.ClearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //this.statusBarColor = contextCompact.getColor(baseContext, color)
    }
}


























