package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySigninBinding
import com.example.myapplication.databinding.ActivitySignupBinding

class SignInActivity : AppCompatActivity() {
    private var mBinding: ActivitySigninBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}