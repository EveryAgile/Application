package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adpaters.ProductListAdapter
import com.example.myapplication.adpaters.SprintListAdapter
import com.example.myapplication.databinding.ActivityProductBinding
import com.example.myapplication.models.*
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")

        var name = binding.textProductName
        var endTime = binding.textDueDate
        var dess = binding.editIntroduction
        var spin = binding.spinnerPrioirity

        val productId = intent.getLongExtra("productId",0)
        Log.d("productId2", productId.toString())
        getProductContent(accessToken, productId, name, endTime, dess, spin)

        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.buttonAddBacklog.setOnClickListener {
            var intent = Intent(this, SprintCreateActivity::class.java)
            intent.putExtra("productId", productId)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(this)
        val SprintAdapter = SprintListAdapter()

        binding.ListViewJob.layoutManager = layoutManager
        binding.ListViewJob.adapter = SprintAdapter
        SprintAdapter .notifyDataSetChanged()

        val call: Call<BacklogList> =
            RetrofitClient.networkService.getSprintList(accessToken,productId)
        call.enqueue(object : Callback<BacklogList> {
            override fun onResponse(
                call: Call<BacklogList>,
                response: Response<BacklogList>
            ) {
                if (response.isSuccessful) {
                    var sprintList: MutableList<BacklogResponseDto>? = response.body()?.list

                    if (sprintList != null && sprintList.size > 0) {
                        for (i in 0 until sprintList.size) {
                            SprintAdapter.SprintListData.add(sprintList[i])
                            SprintAdapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.d("Product 조회", "생성된 프로덕트백로그가 없습니다")
                    }

                    Log.d("스프린트", "성공 : ${response.body()}")
                } else {
                    Log.d("스프린트", "실패1 : ${response.errorBody()?.string()!!}")
                }
            }

            override fun onFailure(call: Call<BacklogList>, t: Throwable) {
                Log.d("스프린트", "실패2 : $t")
            }
        })

    }


    private fun getProductContent(
        accessToken: String,
        productId: Long,
        name: EditText,
        endTime: EditText,
        dess: EditText,
        spin: TextView
    ) {
        runOnUiThread {
            val call: Call<SprintContent> =
                RetrofitClient.networkService.getProductContent(accessToken, productId)
            call.enqueue(object : Callback<SprintContent> {
                override fun onResponse(
                    call: Call<SprintContent>,
                    response: Response<SprintContent>
                ) {
                    if (response.isSuccessful) {
                        var productList: SprintResponseDto? = response.body()?.data
                        name.setText(productList?.sprintName.toString())
                        endTime.setText(productList?.endTime.toString())
                        dess.setText(productList?.decription.toString())
                        spin.text = "상"

                        Log.d("a", productList.toString())

                        Log.d("로그인", "성공 : ${response.body()}")
                    } else {
                        Log.d("로그인", "실패1 : ${response.errorBody()?.string()!!}")
                    }
                }

                override fun onFailure(call: Call<SprintContent>, t: Throwable) {
                    Log.d("로그인", "실패2 : $t")
                }
            })
        }
    }

}