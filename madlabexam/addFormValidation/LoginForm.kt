package com.example.madlabexam.addFormValidation

class LoginForm(
    private var studentID:String,

    private var password:String

) {

    fun validateStudentId(): ValidationResult {
        return if(studentID.isEmpty()){
            ValidationResult.Empty("Student ID is empty")
        }else if(!studentID.equals("IT5555")){
            ValidationResult.Invalid("Should be starting with Iinvalied userID")
        }else{
            ValidationResult.Valid
        }
    }

    fun validatePassword():ValidationResult{
        return if(password.isEmpty()){
            ValidationResult.Empty("password is empty")
        }else if(!password.equals("1234")){
            ValidationResult.Invalid("invalied password")
        }else{
            ValidationResult.Valid
        }
    }
}