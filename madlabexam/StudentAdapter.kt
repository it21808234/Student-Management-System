package com.example.madlabexam

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.madlabexam.database.Student
import com.example.madlabexam.database.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Array.get


class StudentAdapter(student:List<Student>, repository:StudentRepository, viewModel:MainActivityData):RecyclerView.Adapter<StudentViewHolder>() {

    var context: Context? = null
    val students = student
    val repository = repository
    val viewModel = viewModel


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.view_student, parent, false)
        context = parent.context
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.vNumber.text = students.get(position).studentId
        holder.vName.text = students.get(position).name
        holder.vEmail.text = students.get(position).email
        holder.vDegree.text = students.get(position).degree

        holder.vDelete.setOnClickListener {
            if (students.get(position).degree == "UG") {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(students.get(position))
                    val data = repository.getAllUgStudent()
                    withContext(Dispatchers.Main) {
                        viewModel.setData(data)
                    }
                }
                Toast.makeText(context, "under graduate student is  Deleted", Toast.LENGTH_LONG)
                    .show()

            } else {


                if (students.get(position).degree == "PG") {
                    CoroutineScope(Dispatchers.IO).launch {
                        repository.delete(students.get(position))
                        val data = repository.getAllPgStudent()
                        withContext(Dispatchers.Main) {
                            viewModel.setData(data)
                        }
                    }
                    Toast.makeText(context, "post graduate student  is Deleted", Toast.LENGTH_LONG)
                        .show()


                }
            }


        }

        holder.vUpdate.setOnClickListener {
            val id=students.get(position).id
            val intent=Intent(context,UpdateActivity::class.java)
            intent.putExtra("stu_id",id)
            context?.startActivity(intent)

        }
    }

}
