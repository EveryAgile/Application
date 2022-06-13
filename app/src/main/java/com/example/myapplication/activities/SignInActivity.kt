package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ActivitySigninBinding
import com.example.myapplication.models.SignIn
import com.example.myapplication.models.SignInResult
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInActivity : AppCompatActivity() {
    private var mBinding: ActivitySigninBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            var signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }

        binding.buttonSignIn.setOnClickListener {
            var email: String = binding.editTextEmail.text.toString()
            var password: String = binding.editTextPassword.text.toString()
            var signIn = SignIn(email, password)
            var accessToken = null
            val SignInCall: Call<SignInResult> = RetrofitClient.networkService.signIn(signIn)
            SignInCall.enqueue(object : Callback<SignInResult> {
                override fun onResponse(
                    call: Call<SignInResult>,
                    response: Response<SignInResult>
                ) {
                    if (response.isSuccessful) {
                        var accessToken = response.body()?.data?.accessToken
                        var mainIntent = Intent(this@SignInActivity, MainActivity::class.java)
                        mainIntent.putExtra("accessToken", accessToken)
                        startActivity(mainIntent)
                    } else {
                        Log.d("test", response.body().toString())
                        Toast.makeText(
                            applicationContext, "로그인 실패: 계정 정보가 옳지 않습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                override fun onFailure(call: Call<SignInResult>, t: Throwable) {
                    Log.d("회원 모드 조회", "실패2 : $t")
                }
            })
        }

    }
}