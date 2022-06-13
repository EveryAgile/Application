package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.adpaters.adapter_Agile
import com.example.myapplication.databinding.ActivityAgileBinding
import com.example.myapplication.model_Agile
import com.example.myapplication.models.InquireSprintsResult
import com.example.myapplication.models.Sprint
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectActivity : AppCompatActivity() {
    private var mBinding: ActivityAgileBinding? = null
    private val binding get() = mBinding!!
    var agileModelList = arrayListOf<model_Agile>()
    var agileList: List<Sprint>? = listOf<Sprint>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var accessToken = intent.getStringExtra("accessToken")
        var projectId: Int = 7

        mBinding = ActivityAgileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inquireSprintsCall: Call<InquireSprintsResult> =
            RetrofitClient.networkService.inquirySprints(accessToken=accessToken, projectId)
        inquireSprintsCall.enqueue(object : Callback<InquireSprintsResult> {
            override fun onResponse(
                call: Call<InquireSprintsResult>,
                response: Response<InquireSprintsResult>
            ){
                if (response.isSuccessful) {
                    Log.d("스프린트 조회", "성공 : ${response.body()}")
                    agileList = response.body()?.list
                    if (agileList != null) {
                        for (i: Int in 0 until agileList!!.size){
                            agileModelList.add(
                                model_Agile(R.drawable.ic_circledcheck,
                                    agileList!![i].sprintName.toString(), R.drawable.ic_person, R.drawable.ic_person)
                            )
                        }
                        updateAdapter()
                    }
                } else {
                    Log.d("스프린트 조회", "실패 :${response.errorBody()?.string()!!}")
                }
            }
            override fun onFailure(call: Call<InquireSprintsResult>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        //+버튼 누르면 스프린트 만드는 페이지로 넘어감
        binding.createProject.setOnClickListener {
            var productCreateIntent = Intent(this, ProductCreateActivity::class.java)
            productCreateIntent.putExtra("isNew", true)
            productCreateIntent.putExtra("projectId", projectId)
            productCreateIntent.putExtra("accessToken", accessToken)
            startActivity(productCreateIntent)
        }

        //ListView에서 item 클릭되었을 때 이벤트
        binding.listViewAgile.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
            var productCreateIntent = Intent(this, ProductCreateActivity::class.java)
            productCreateIntent.putExtra("isNew", false)
            productCreateIntent.putExtra("sprint", agileList?.get(position))
            productCreateIntent.putExtra("accessToken", accessToken)
            startActivity(productCreateIntent)
        }
    }

    fun updateAdapter(){
        var adapter = adapter_Agile(this, agileModelList)
        binding.listViewAgile.adapter = adapter
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

}