package com.example.madlabexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.madlabexam.addFormValidation.FormData
import com.example.madlabexam.addFormValidation.ValidationResult
import com.example.madlabexam.database.Student
import com.example.madlabexam.database.StudentDatabase
import com.example.madlabexam.database.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    lateinit var edtStudentId: EditText
    lateinit var edtName: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var spnDgree: Spinner
    private var count = 0

    private lateinit var viewModel:MainActivityData
    private lateinit var repository: StudentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        edtStudentId = findViewById(R.id.edtItNumber)
        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        spnDgree = findViewById(R.id.spnDgree)
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnView:Button=findViewById(R.id.btnView)

        btnAdd.setOnClickListener {
            val myForm = FormData(
                 edtStudentId.text.toString(),
                edtName.text.toString(),
                edtEmail.text.toString(),
                edtPassword.text.toString(),
                spnDgree.selectedItem.toString()

            )

            val studentIdValidation = myForm.validateStudentId()
            val nameValidation = myForm.validateName()
            val emailValidation = myForm.validateEmail()
            val passwordValidation = myForm.validatePassword()
            val spnDegreeValidation=myForm.validateDegree()

    when(studentIdValidation){
        is ValidationResult.Empty -> {
                edtStudentId.error = studentIdValidation.errorMessage
        }
        is ValidationResult.Invalid -> {
            edtStudentId.error = studentIdValidation.errorMessage
        }
        ValidationResult.Valid -> {
            count++
        }
    }

            when(nameValidation){
                is ValidationResult.Empty -> {
                    edtName.error = nameValidation.errorMessage
                }
                is ValidationResult.Invalid -> {

                }
                ValidationResult.Valid -> {
                    count++
                }
            }
       when(emailValidation){
           is ValidationResult.Empty -> {
               edtEmail.error = emailValidation.errorMessage

           }
           is ValidationResult.Invalid ->{

               edtEmail.error=emailValidation.errorMessage
           }
           ValidationResult.Valid -> {
               count++
           }
       }

when(passwordValidation){
    is ValidationResult.Empty ->{

        displayAlert("Error", passwordValidation.errorMessage)

    }
    is ValidationResult.Invalid -> {

        displayAlert("Error", passwordValidation.errorMessage)

    }
    ValidationResult.Valid -> {

        count++
    }
}
     when(spnDegreeValidation){
         is ValidationResult.Empty -> {
             val tv:TextView = spnDgree.selectedView as TextView
             tv.error =""
             tv.text = spnDegreeValidation.errorMessage

         }
          is ValidationResult.Invalid -> TODO()
         ValidationResult.Valid -> {

             count++
         }
}

            if(count==5){

                 repository=StudentRepository(StudentDatabase.getInstance(this))
                 viewModel=ViewModelProvider(this)[MainActivityData::class.java]

                CoroutineScope(Dispatchers.IO).launch {
                    repository.insert(Student(edtStudentId.text.toString(),
                        edtName.text.toString(),
                        edtEmail.text.toString(),
                        edtPassword.text.toString(),
                        spnDgree.selectedItem.toString()))

                    val data=repository.getAllStudent()
                    runOnUiThread {
                        viewModel.setData(data)
                    }

                    }

                displayAlert("Success","You have successfully registered")
            }
            else{
                count=0

            }


        }

        btnView.setOnClickListener {

            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

   val btnLogout:Button=findViewById(R.id.button7)
        btnLogout.setOnClickListener {
            val i=Intent(this,LoginActivity::class.java)
            startActivity(i)

        }




    }

    fun displayAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->

        }
        val dialog = builder.create()
        dialog.show()

    }
}







