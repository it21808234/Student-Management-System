package com.example.madlabexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlabexam.database.StudentDatabase
import com.example.madlabexam.database.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter:StudentAdapter
    private lateinit var viewModel:MainActivityData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rvStdList)
        val repository=StudentRepository(StudentDatabase.getInstance(this))
        viewModel= ViewModelProvider(this)[MainActivityData::class.java]


        viewModel.data.observe(this){
            adapter = StudentAdapter(it,repository, viewModel)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllUgStudent()
            runOnUiThread {
                viewModel.setData(data)
            }
        }

        var btnHome:Button=findViewById(R.id.btnHome)
        btnHome.setOnClickListener {

            val intent= Intent(this,AddActivity::class.java)
            startActivity(intent)

        }

        var btnPG:Button=findViewById(R.id.btnPG)
        btnPG.setOnClickListener {

            val intent=Intent(this,PostGraduate::class.java)
            startActivity(intent)
        }

    }
}