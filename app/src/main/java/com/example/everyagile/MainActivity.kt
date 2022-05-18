package com.example.everyagile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.everyagile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val addButton = binding.fabButton
            setContentView(binding.root)

        addButton.setOnClickListener{
            Toast.makeText(this, "ADD!", Toast.LENGTH_SHORT).show()
        }
    }
}