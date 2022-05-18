package com.example.everyagile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.everyagile.databinding.ActivityCreateProjectBinding

class CreateProjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}