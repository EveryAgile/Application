package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityProductBinding
import com.example.myapplication.models.SprintContent
import com.example.myapplication.models.SprintCreate
import com.example.myapplication.models.SprintResponseDto
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

        val productId = intent.getIntExtra("productId",0)

        val call: Call<SprintContent> = RetrofitClient.networkService.getProductContent(accessToken,productId)
        call.enqueue(object : Callback<SprintContent> {
            override fun onResponse(
                call: Call<SprintContent>,
                response: Response<SprintContent>
            ) {
                if (response.isSuccessful) {
                    var productList: SprintResponseDto? = response.body()?.list
                    name.setText(productList?.sprintName.toString())
                    endTime.setText(productList?.endTime.toString())
                    dess.setText(productList?.decription.toString())
                    spin.text = productList?.decription.toString()
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