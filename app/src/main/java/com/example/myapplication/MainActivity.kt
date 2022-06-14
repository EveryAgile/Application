package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.activities.ProjectActivity
import com.example.myapplication.activities.SignInActivity
import com.example.myapplication.adpaters.ProjectListAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.ProjectCreate
import com.example.myapplication.models.ProjectListItem
import com.example.myapplication.models.ProjectResponseDto
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        val projectListAdapter = ProjectListAdapter()

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = projectListAdapter
        ProjectListAdapter().notifyDataSetChanged()

        var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")

        createProjectBtn()
        getProject(accessToken, projectListAdapter)
    }

    private fun getProject(
        accessToken: String,
        projectListAdapter: ProjectListAdapter
    ) {
        val call: Call<ProjectCreate> = RetrofitClient.networkService.getProject(accessToken)
        call.enqueue(object : Callback<ProjectCreate> {
            override fun onResponse(
                call: Call<ProjectCreate>,
                response: Response<ProjectCreate>
            ) {
                if (response.isSuccessful) {
                    var ProjectList: List<ProjectResponseDto>? = response.body()?.list
                    if (ProjectList != null && ProjectList.size > 0) {
                        for (i in 0 until ProjectList.size) {
                            projectListAdapter.projectListData.add(ProjectList[i])
                            projectListAdapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.d("Project 조회", "생성된 프로젝트가 없습니다")
                    }
                    response.body()?.toString()?.let { Log.d("로그인", it) }

                } else {
                    Log.d("로그인", "실패1 : ${response.errorBody()?.string()!!}")
                    Log.d("로그인", accessToken.toString())
                }
            }

            override fun onFailure(call: Call<ProjectCreate>, t: Throwable) {
                Log.d("로그인", "실패2 : $t")
            }
        })
    }

    private fun createProjectBtn() {
        binding.floatingActionButton.setOnClickListener {
            var intent = Intent(this, ProjectCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}