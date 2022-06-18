package com.example.myapplication.adpaters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.activities.ProductActivity
import com.example.myapplication.activities.SprintActivity
import com.example.myapplication.databinding.ListProductBacklogBinding
import com.example.myapplication.databinding.ListSprintBinding
import com.example.myapplication.models.BacklogResponseDto
import com.example.myapplication.models.ProjectResponseDto
import com.example.myapplication.models.SprintList
import com.example.myapplication.models.SprintListItem

class SprintListViewHolder(val binding: ListSprintBinding): RecyclerView.ViewHolder(binding.root){
}
class SprintListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var SprintListData = mutableListOf<BacklogResponseDto>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return SprintListViewHolder(ListSprintBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as SprintListViewHolder).binding
        binding.textSprintName.text = SprintListData[position].backlogName
        binding.textStroyPorint.text = SprintListData[position].storyPoint.toString()
        binding.textView6.text = SprintListData[position].manDay.toString()
        binding.textSprintName.setOnClickListener {
            Intent(holder.itemView.context, SprintActivity::class.java).apply {
                putExtra("backlogId", SprintListData[position].backlogId)
//                Log.d("productId1", SprintListData[position].sprintId.toString())
//                putExtra("projectName", projectListData[position].projectName)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { holder.itemView.context.startActivity(this) }
        }
    }
    override fun getItemCount(): Int{
        return SprintListData?.size ?: 0
    }
}