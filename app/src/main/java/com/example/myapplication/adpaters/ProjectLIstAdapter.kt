package com.example.myapplication.adpaters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.activities.ProjectActivity
import com.example.myapplication.activities.SignInActivity
import com.example.myapplication.databinding.ListProjectBinding
import com.example.myapplication.models.*
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")

class ProjectListViewHolder(val binding: ListProjectBinding): RecyclerView.ViewHolder(binding.root){
}
class ProjectListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")
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
        Log.d("adapter", accessToken)
        binding.btnDeleteProject.setOnClickListener {
            val call: Call<CommonResult> = RetrofitClient.networkService.deleteProject(accessToken,projectListData[position].projectId)
            call.enqueue(object : Callback<CommonResult> {
                override fun onResponse(
                    call: Call<CommonResult>,
                    response: Response<CommonResult>
                ) {
                    if (response.isSuccessful) {
                        Log.d("삭제", "성공 : ${response.body()}")
                    } else {
                        Log.d("삭제", "실패1 : ${response.errorBody()?.string()!!}")
                    }
                }
                override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                    Log.d("삭제", "실패2 : $t")
                }
            })
        }
    }

    override fun getItemCount(): Int{
        return  projectListData?.size ?: 0
    }
}