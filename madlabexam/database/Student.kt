package com.example.madlabexam.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(

    var studentId:String?,
    var name:String?,
    var email:String?,
    var password:String?,
    var degree:String?

){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null

}
