package com.example.loginfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import android.view.Window
import android.view.WindowManager
import android.os.Build
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.util.Log

class LoginActivity : AppCompatActivity() {

    private lateinit var edIdent: EditText
    private lateinit var edPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var sqLiteHelper: SQLiteHelper
    private var std: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        sqLiteHelper = SQLiteHelper(this)

        btnLogin.setOnClickListener { signIn() }

    }

    private fun addUser() {
        val ident = edIdent.text.toString()
        val password = edPassword.text.toString()
        val name = "Test"

        if(ident.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter required field ", Toast.LENGTH_SHORT).show()
        } else {
            val std = UserModel(name = name, ident = ident, password = password)
            val status = sqLiteHelper.insertUser(std)
            //Check insert success or not success
            if (status > -1) {
                Toast.makeText(this, "User Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signIn(){
        val ident = edIdent.text.toString()
        val password = edPassword.text.toString()
        val stdList = sqLiteHelper.userLogin(ident, password)

        if(stdList.size > 0){
            //Log.d("TAG", "${stdList}")
            changeToMenu()

        } else {
            Toast.makeText(this, "User or Password Incorrect", Toast.LENGTH_SHORT).show()
        }
    }

    fun changeToMenu() {
        val intent = Intent(this, MainActivity::class.java)

        intent.change()

    }

    fun Intent.change() {
        startActivity(this)
        finish()
    }

    private fun clearEditText() {
        edIdent.setText("")
        edPassword.setText("")
        edIdent.requestFocus()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun Window.setStatusBarColorTo(color: Int){
        //this.ClearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //this.statusBarColor = contextCompact.getColor(baseContext, color)
    }

    private fun initView() {
        edIdent = findViewById(R.id.edIdent)
        edPassword = findViewById(R.id.edPassword)
        btnLogin = findViewById(R.id.btnLogin)
    }
}


























