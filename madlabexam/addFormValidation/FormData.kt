package com.example.madlabexam.addFormValidation

import android.util.Patterns
import java.util.regex.Pattern

class FormData(
    private var studentID:String,
    private var name:String,
    private var email:String,
    private var password:String,
    private var degree:String
) {
    fun validateStudentId(): ValidationResult {
        return if(studentID.isEmpty()){
            ValidationResult.Empty("Student ID is empty")
        }else if(!studentID.startsWith("IT")){
            ValidationResult.Invalid("Should be starting with IT")
        }else if(studentID.length<10){
            ValidationResult.Invalid("Student ID should have 10 characters")
        }else if(studentID.length>10){
            ValidationResult.Invalid("Student ID should have 10 characters")
        }else{
            ValidationResult.Valid
        }
    }
    fun validateName(): ValidationResult {
        return if(name.isEmpty()){
            ValidationResult.Empty("Name is empty")
        }else{
            ValidationResult.Valid
        }
    }
    fun validateEmail(): ValidationResult {
        return if(email.isEmpty()){
            ValidationResult.Empty("Email field is empty")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidationResult.Invalid("Enter valid Email address")
        }else{
            ValidationResult.Valid
        }
    }
    fun validatePassword():ValidationResult{
        return if(password.isEmpty()){
            ValidationResult.Empty("password is empty")
        }else if(password.length<10){
            ValidationResult.Invalid("password should have 10 characters")
        }else if(password.length>10){
            ValidationResult.Invalid("password should have 10 characters")
        }else{
            ValidationResult.Valid
        }
    }
    fun validateDegree(): ValidationResult {
        return if(degree.isEmpty()){
            ValidationResult.Empty("Degree is empty")
        }else{
            ValidationResult.Valid
        }
    }
}