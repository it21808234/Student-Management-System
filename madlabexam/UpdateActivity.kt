package com.example.madlabexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.text.set
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madlabexam.addFormValidation.FormData
import com.example.madlabexam.addFormValidation.ValidationResult
import com.example.madlabexam.database.Student
import com.example.madlabexam.database.StudentDatabase
import com.example.madlabexam.database.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateActivity : AppCompatActivity() {
    lateinit var edtStudentId: EditText
    lateinit var edtName: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var spnDgree: Spinner
    var count=0
    private lateinit var repository: StudentRepository

    private lateinit var viewModel:MainActivityData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val repository= StudentRepository(StudentDatabase.getInstance(this))

        edtStudentId=findViewById(R.id.editTextText)
        edtName=findViewById(R.id.editTextText2)
        edtEmail=findViewById(R.id.editTextText3)
        edtPassword=findViewById(R.id.editTextText4)
        spnDgree=findViewById(R.id.spinner)
        viewModel= ViewModelProvider(this)[MainActivityData::class.java]

        val studentId=intent.getIntExtra("stu_id", -1)
        Toast.makeText(this,"${studentId}",Toast.LENGTH_SHORT).show()

          CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getStudent(studentId)
            edtStudentId.setText(data.studentId.toString())
            edtName.setText(data.name.toString())
            edtEmail.setText(data.email.toString())
            edtPassword.setText(data.password.toString())
            if(data.degree.toString()=="UG") {

                      spnDgree.setSelection(1)

            }else{
                spnDgree.setSelection(2)

            }


        }

        val btnUpdate:Button=findViewById(R.id.button)
        btnUpdate.setOnClickListener {
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
                    val tv: TextView = spnDgree.selectedView as TextView
                    tv.error =""
                    tv.text = spnDegreeValidation.errorMessage

                }
                is ValidationResult.Invalid -> TODO()
                ValidationResult.Valid -> {

                    count++
                }
            }

            if(count==5){

                this.repository=StudentRepository(StudentDatabase.getInstance(this))

                CoroutineScope(Dispatchers.IO).launch {
                    repository.update(edtStudentId.text.toString(),
                        edtName.text.toString(),
                        edtEmail.text.toString(),
                        edtPassword.text.toString(),
                        spnDgree.selectedItem.toString(),studentId)


                    val data=repository.getAllStudent()
                    runOnUiThread {
                        viewModel.setData(data)
                    }

                }

                displayAlert("Success","You have successfully updated")
            }
            else{
                count=0

            }


        }

        val btnView: Button =findViewById(R.id.button2)
        btnView.setOnClickListener{

            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
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