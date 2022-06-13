package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.activities.ProjectActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val accessToken: String? = intent.getStringExtra("accessToken")

        //Agile 화면으로
        binding.buttonAgile.setOnClickListener {
            var intent = Intent(this, ProjectActivity::class.java)
            intent.putExtra("accessToken", accessToken)
            var projectId: Int = 7
            intent.putExtra("projectId", projectId)
            startActivity(intent)
        }

        //프로젝트 생성
        /*var project = Projects("2022-06-15", "StonePotBibimbap", "2022-06-13", "DEVELOP")
        val CreateProjectCall: Call<ProjectsResult> = RetrofitClient.networkService.projects(accessToken=accessToken, project)
        CreateProjectCall.enqueue(object : Callback<ProjectsResult>{
            override fun onResponse(
                call: Call<ProjectsResult>,
                response: Response<ProjectsResult>
            ){
                if (response.isSuccessful) {
                    Log.d("프로젝트 생성", "성공 : ${response.body()}")
                } else {
                    Log.d("프로젝트 생성", "실패 : ${response.errorBody()?.string()!!}")
                }
            }
            override fun onFailure(call: Call<ProjectsResult>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })*/

    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}