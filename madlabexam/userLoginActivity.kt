package com.example.madlabexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.madlabexam.database.StudentDatabase
import com.example.madlabexam.database.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class userLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        val repository = StudentRepository(StudentDatabase.getInstance(this))

        val edtID: EditText = findViewById(R.id.editTextText8)
        val edtPassword: EditText = findViewById(R.id.editTextTextPassword)

        val button: Button = findViewById(R.id.button10)

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val data = repository.getStudent(edtID.text.toString(), edtPassword.text.toString())

                    if (data != null) {
                        runOnUiThread {
                            val intent = Intent(this@userLoginActivity, DisplayActivity::class.java)
                            intent.putExtra("std_id",data.id)
                            startActivity(intent)
                            Toast.makeText(this@userLoginActivity, "valid credentials", Toast.LENGTH_SHORT).show()
                        }

                    }else {
                        // User not found, login failed
                        runOnUiThread {
                            Toast.makeText(this@userLoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
