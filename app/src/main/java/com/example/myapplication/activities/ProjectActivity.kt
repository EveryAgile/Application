package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adpaters.ProductListAdapter
import com.example.myapplication.adpaters.ProjectListAdapter
import com.example.myapplication.adpaters.adapter_Agile
import com.example.myapplication.databinding.ActivityAgileBinding
import com.example.myapplication.model_Agile
import com.example.myapplication.models.*
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectActivity : AppCompatActivity() {

    var AgileList = arrayListOf<model_Agile>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val projectId = intent.getLongExtra("projectId",0)
        var binding = ActivityAgileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")

        //+버튼 누르면 할일 만드는 페이지로 넘어감
        binding.createProject.setOnClickListener {
            var intent = Intent(this, ProductCreateActivity::class.java)
            intent.putExtra("projectId", projectId)
            Log.d("b", projectId.toString())
            startActivity(intent)
        }

        Log.d("token",accessToken.toString())

        val layoutManager = LinearLayoutManager(this)
        val productListAdapter = ProductListAdapter()

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = productListAdapter
        productListAdapter.notifyDataSetChanged()

        getProductList(accessToken, productListAdapter,projectId)

        var email = binding.editText
        binding.btnIn.setOnClickListener {
            var member = InviteRequestDto()
            member.memberEmail = email.text.toString()
            member.projectId = projectId
            val call: Call<CommonResult> = RetrofitClient.networkService.postMember(accessToken,member)
            call.enqueue(object : Callback<CommonResult> {
                override fun onResponse(
                    call: Call<CommonResult>,
                    response: Response<CommonResult>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.toString()?.let { Log.d("초대", it) }

                    } else {
                        Log.d("초대", "실패1 : ${response.errorBody()?.string()!!}")
                    }
                }

                override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                    Log.d("초대", "실패2 : $t")
                }
            })
        }
        productListAdapter.notifyDataSetChanged()
    }

    private fun getProductList(
        accessToken: String,
        productListAdapter: ProductListAdapter,
        projectId: Long
    ) {

            val call: Call<SprintList> = RetrofitClient.networkService.getProductList(accessToken,projectId)
            call.enqueue(object : Callback<SprintList> {
                override fun onResponse(
                    call: Call<SprintList>,
                    response: Response<SprintList>
                ) {
                    if (response.isSuccessful) {
                        var productList: MutableList<SprintListItem>? = response.body()?.list
                        Log.d("productlist",productList.toString())
                        if (productList != null && productList.size > 0) {
                            for (i in 0 until productList.size) {
                                productListAdapter.productListData.add(productList[i])
                                productListAdapter.notifyDataSetChanged()
                            }
                        } else {
                            Log.d("Product 조회", "생성된 프로덕트백로그가 없습니다")
                        }
                        response.body()?.toString()?.let { Log.d("로그인", it) }

                    } else {
                        Log.d("로그인", "실패1 : ${response.errorBody()?.string()!!}")
                        Log.d("로그인", accessToken.toString())
                    }
                }

                override fun onFailure(call: Call<SprintList>, t: Throwable) {
                    Log.d("로그인", "실패2 : $t")
                }
            })
            productListAdapter.notifyDataSetChanged()
    }

    //create_job activity가 끝나고 돌아왔을 때 listView 업데이트
    override fun onResume(){
        super.onResume()
        if (intent.getSerializableExtra("model_agile") != null) {
            var model_agile = intent.getSerializableExtra("model_agile") as model_Agile
            AgileList.add(model_agile)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}