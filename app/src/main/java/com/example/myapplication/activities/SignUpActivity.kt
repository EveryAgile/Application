package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ActivitySignupBinding
import com.example.myapplication.models.SignIn
import com.example.myapplication.models.SignInResult
import com.example.myapplication.models.SignUp
import com.example.myapplication.models.SignUpResult
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private var mBinding: ActivitySignupBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignupFinish.setOnClickListener {
            var email: String = binding.editTextSignupEmail.text.toString()
            var password: String = binding.editTextSignupPassword.text.toString()
            var name: String = binding.editTextSignupName.text.toString()
            var signUp = SignUp(email, name, password)
            var accessToken = null
            val SignUpCall: Call<SignUpResult> = RetrofitClient.networkService.signUp(signUp)
            SignUpCall.enqueue(object : Callback<SignUpResult> {
                override fun onResponse(
                    call: Call<SignUpResult>,
                    response: Response<SignUpResult>
                ) {
                    if (response.isSuccessful) {
                        var signInIntent = Intent(this@SignUpActivity, SignInActivity::class.java)
                        startActivity(signInIntent)
                    } else {
                        Log.d("test", response.body().toString())
                        Toast.makeText(
                            applicationContext, "로그인 실패: 계정 정보가 옳지 않습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                    Log.d("회원 모드 조회", "실패2 : $t")
                }
            })
        }
    }
}