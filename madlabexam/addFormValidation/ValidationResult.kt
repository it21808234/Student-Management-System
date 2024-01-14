package com.example.madlabexam.addFormValidation

sealed interface ValidationResult{
    data class Empty(val errorMessage:String):ValidationResult
    data class Invalid(val errorMessage: String):ValidationResult
    object Valid:ValidationResult
}