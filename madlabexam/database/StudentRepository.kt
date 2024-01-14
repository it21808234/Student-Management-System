package com.example.madlabexam.database

class StudentRepository(
    private val db:StudentDatabase
){

    suspend fun insert(student:Student) = db.getStudentDao().insertStudent(student)
    fun getAllStudent():List<Student> = db.getStudentDao().getAllStudent()
    fun getAllUgStudent():List<Student> = db.getStudentDao().getAllUgStudent("UG")
    fun getAllPgStudent():List<Student> = db.getStudentDao().getAllPgStudent("PG")
    suspend fun delete(student: Student) = db.getStudentDao().deleteStudent(student)
    fun getStudent(id:Int) = db.getStudentDao().getStudent(id)
    fun update(s:String,n:String,e:String,p:String,d:String,id: Int) = db.getStudentDao().update(s,n,e,p,d,id)
    fun getId(studentId:String,password:String):Boolean= db.getStudentDao().getId(studentId, password)
    fun getStudent(name:String,password:String):Student =db.getStudentDao().getStudent(name, password)

}