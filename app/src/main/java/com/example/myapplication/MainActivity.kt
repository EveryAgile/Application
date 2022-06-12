package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.activities.ProjectActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.ResponsResult
import com.example.myapplication.models.SignUp
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

        //Agile 화면으로
        binding.buttonAgile.setOnClickListener {
            var intent = Intent(this, ProjectActivity::class.java)
            startActivity(intent)
        }
//
//        val call: Call<Users> = RetrofitClient.networkService.getUsers()
//        call.enqueue(object : Callback<Users> {
//            override fun onResponse(
//                call: Call<Users>,
//                response: Response<Users>
//            ) {
//                if (response.isSuccessful) {
//                    Log.d("회원 모드 조회", "성공 : ${response.body()}")
//                } else {
//                    Log.d("회원 모드 조회", "실패1 : ${response.errorBody()?.string()!!}")
//                }
//            }
//            override fun onFailure(call: Call<Users>, t: Throwable) {
//                Log.d("회원 모드 조회", "실패2 : $t")
//            }
//        })
        var singUp = SignUp("hojin180@gmail.com","hojin","1234")

        val call: Call<ResponsResult> = RetrofitClient.networkService.signUp(singUp)
        call.enqueue(object : Callback<ResponsResult> {
            override fun onResponse(
                call: Call<ResponsResult>,
                response: Response<ResponsResult>
            ) {
                if (response.isSuccessful) {
                    Log.d("회원 모드 조회", "성공 : ${response.body()}")
                } else {
                    Log.d("회원 모드 조회", "실패1 : ${response.errorBody()?.string()!!}")
                }
            }
            override fun onFailure(call: Call<ResponsResult>, t: Throwable) {
                Log.d("회원 모드 조회", "실패2 : $t")
            }
        })
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}