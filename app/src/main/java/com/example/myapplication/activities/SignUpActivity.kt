package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.myapplication.databinding.ActivitySignupBinding
import com.example.myapplication.models.CommonResult
import com.example.myapplication.models.SignUpRequestDto
import com.example.myapplication.models.ProjectCreate
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var signUp = SignUpRequestDto()

        var email = binding.editTextSignupEmail
        var name = binding.editTextSignupName
        var password = binding.editTextSignupPassword

        signUp(binding, signUp, email, name, password)

    }

    private fun signUp(
        binding: ActivitySignupBinding,
        signUp: SignUpRequestDto,
        email: EditText,
        name: EditText,
        password: EditText
    ) {
        binding.buttonSignupFinish.setOnClickListener {
            signUp.email = email.text.toString()
            signUp.name = name.text.toString()
            signUp.password = password.text.toString()

            val call: Call<CommonResult> = RetrofitClient.networkService.signUp(signUp)
            call.enqueue(object : Callback<CommonResult> {
                override fun onResponse(
                    call: Call<CommonResult>,
                    response: Response<CommonResult>
                ) {
                    if (response.isSuccessful) {
                        Log.d("로그인", "성공 : ${response.body()}")
                        finish()
                    } else {
                        Log.d("로그인", "실패1 : ${response.errorBody()?.string()!!}")
                    }
                }

                override fun onFailure(call: Call<CommonResult>, t: Throwable) {
                    Log.d("로그인", "실패2 : $t")
                }
            })
        }
    }
}