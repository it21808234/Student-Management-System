package com.example.madlabexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.madlabexam.database.StudentDatabase
import com.example.madlabexam.database.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DisplayActivity : AppCompatActivity() {
    lateinit var edtStudentId: TextView
    lateinit var edtName: TextView
    lateinit var edtEmail: TextView
    lateinit var edtPassword: TextView
    lateinit var edtDgree: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val repository = StudentRepository(StudentDatabase.getInstance(this))
        val studentId = intent.getIntExtra("std_id", -1)

        edtStudentId = findViewById(R.id.textView5)
        edtName = findViewById(R.id.textView6)
        edtEmail = findViewById(R.id.textView7)
        edtPassword = findViewById(R.id.textView8)
        edtDgree = findViewById(R.id.textView9)

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getStudent(studentId)
            edtStudentId.setText(data.studentId.toString())
            edtName.setText(data.name.toString())
            edtEmail.setText(data.email.toString())
            edtPassword.setText(data.password.toString())
            edtDgree.setText(data.degree.toString())

        }

            val btnLogout:Button=findViewById(R.id.button9)

        btnLogout.setOnClickListener {
                    val i=Intent(this,LoginActivity::class.java)
                    startActivity(i)



                }

        }


    }
