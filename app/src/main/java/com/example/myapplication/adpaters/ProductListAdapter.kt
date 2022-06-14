package com.example.myapplication.adpaters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.activities.ProductActivity
import com.example.myapplication.activities.ProjectActivity
import com.example.myapplication.databinding.ListProductBacklogBinding
import com.example.myapplication.databinding.ListProjectBinding
import com.example.myapplication.models.ProjectResponseDto
import com.example.myapplication.models.SprintResponseDto

class ProductListViewHolder(val binding: ListProductBacklogBinding): RecyclerView.ViewHolder(binding.root){
}
class ProductListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var productListData = mutableListOf<SprintResponseDto>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return ProductListViewHolder(ListProductBacklogBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ProductListViewHolder).binding
        binding.name.text = productListData[position].sprintName

        binding.name.setOnClickListener {
            Intent(holder.itemView.context, ProductActivity::class.java).apply {
                putExtra("productId", productListData[position].sprintId)
//                putExtra("projectName", projectListData[position].projectName)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { holder.itemView.context.startActivity(this) }
        }
    }
    override fun getItemCount(): Int{
        return  productListData?.size ?: 0
    }
}