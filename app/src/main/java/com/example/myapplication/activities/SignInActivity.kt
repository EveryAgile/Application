package com.example.myapplication.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.ActivitySigninBinding
import com.example.myapplication.models.SignInRequestDto
import com.example.myapplication.models.CommenResult
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    companion object {
        lateinit var prefs: PrefsManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = PrefsManager(this)
        var signIn = SignInRequestDto()
        var email = binding.editTextEmail
        var password =binding.editTextPassword

        buttonSignIn(binding, signIn, email, password)

        signUpBtn(binding)
    }

    private fun signUpBtn(binding: ActivitySigninBinding) {
        binding.buttonSignUp.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    class PrefsManager(context: Context) {
        private val prefs = context.getSharedPreferences("pref_name", Context.MODE_PRIVATE)

        fun getString(key: String, defValue: String) : String {
            return prefs.getString(key, defValue).toString()
        }

        fun setString(key: String, value: String) {
            prefs.edit().putString(key, value).apply()
        }
    }

    private fun buttonSignIn(
        binding: ActivitySigninBinding,
        signIn: SignInRequestDto,
        email: EditText,
        password: EditText
    ) {
        binding.buttonSignIn.setOnClickListener {
            signIn.email = email.text.toString()
            signIn.password = password.text.toString()

            val call: Call<CommenResult> = RetrofitClient.networkService.signIn(signIn)
            call.enqueue(object : Callback<CommenResult> {
                override fun onResponse(
                    call: Call<CommenResult>,
                    response: Response<CommenResult>
                ) {
                    if (response.isSuccessful) {
                        prefs.setString("ACCESS_TOKEN", response.body()?.data?.accessToken.toString())
                        Log.d("로그인", "성공 : ${response.body()}")
                        callMain()
                    } else {
                        Log.d("로그인", "실패1 : ${response.errorBody()?.string()!!}")
                    }
                }

                override fun onFailure(call: Call<CommenResult>, t: Throwable) {
                    Log.d("로그인", "실패2 : $t")
                }
            })
        }
    }

    fun callMain(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}