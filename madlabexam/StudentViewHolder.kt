package com.example.madlabexam

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class StudentViewHolder(view: View):ViewHolder(view) {

  val vNumber:TextView=view.findViewById(R.id.vNumber)
    val vName:TextView=view.findViewById(R.id.vName)
    val vEmail:TextView=view.findViewById(R.id.vEmail)
    val vDegree:TextView=view.findViewById(R.id.vDgree)
    val vDelete: ImageView=view.findViewById(R.id.IDelete)
    val vUpdate:ImageView=view.findViewById(R.id.IUpdate)

}