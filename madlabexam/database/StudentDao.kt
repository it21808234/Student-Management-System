package com.example.madlabexam.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student:Student)

    @Query("SELECT * FROM Student")
    fun getAllStudent():List<Student>

    @Query("SELECT * FROM Student WHERE degree LIKE :UG")
    fun getAllUgStudent(UG:String):List<Student>

    @Query("SELECT * FROM Student WHERE degree LIKE :PG")
    fun getAllPgStudent(PG:String):List<Student>

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT *  FROM Student WHERE  id=:id")
    fun getStudent(id:Int):Student

    @Query("UPDATE Student SET studentId=:s,name=:n,email=:e,password=:p,degree=:d  WHERE id=:id")
    fun update(s:String,n:String,e:String,p:String,d:String,id: Int)

    @Query("SELECT * FROM Student WHERE studentId Like:studentId AND password Like:password")
    fun getId(studentId:String,password:String):Boolean

    @Query("SELECT * FROM Student WHERE name Like:name AND password Like:password")
    fun getStudent(name:String,password:String):Student
}