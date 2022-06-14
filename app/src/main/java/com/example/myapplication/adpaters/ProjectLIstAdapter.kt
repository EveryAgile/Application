package com.example.myapplication.adpaters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.activities.ProjectActivity
import com.example.myapplication.databinding.ListProjectBinding
import com.example.myapplication.models.CommonResult
import com.example.myapplication.models.ProjectCreate
import com.example.myapplication.models.ProjectListItem
import com.example.myapplication.models.ProjectResponseDto
import retrofit2.Callback

class ProjectListViewHolder(val binding: ListProjectBinding): RecyclerView.ViewHolder(binding.root){
}
class ProjectListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var projectListData = mutableListOf<ProjectResponseDto>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return ProjectListViewHolder(ListProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ProjectListViewHolder).binding
        binding.buttonProject.text = projectListData[position].projectName

        binding.buttonProject.setOnClickListener {
            Log.d("projectId", projectListData[position].projectId.toString())
            Intent(holder.itemView.context, ProjectActivity::class.java).apply {
                putExtra("projectId", projectListData[position].projectId)
                putExtra("projectName", projectListData[position].projectName)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { holder.itemView.context.startActivity(this) }
        }
    }
    override fun getItemCount(): Int{
        return  projectListData?.size ?: 0
    }
}