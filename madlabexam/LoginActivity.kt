package com.example.madlabexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    val loginFragment=UserLoginFragment()
    val adminFragment=AdminLoginFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn:Button=findViewById(R.id.button5)
        btn.setOnClickListener {

            loadLogin()

        }

        val btnAdmin:Button=findViewById(R.id.button6)
        btnAdmin.setOnClickListener {

            loadAdmin()
        }

    }
    private fun loadLogin(){
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment == null){

            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,loginFragment).commit()
        }else{

            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,loginFragment).commit()
        }
    }

    private fun loadAdmin(){
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment == null){

            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,adminFragment).commit()
        }else{

            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,adminFragment).commit()
        }
    }

}