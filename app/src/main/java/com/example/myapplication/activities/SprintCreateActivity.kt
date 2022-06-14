package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityCreateSprintBacklogBinding
import com.example.myapplication.models.SprintRequestDto

class SprintCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateSprintBacklogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val projectId = intent.getIntExtra("projectId",0)
        val projectName = intent.getIntExtra("projectName",0)

        var sprint = SprintRequestDto()
        var description = binding.editIntroduction
        var endTime = binding.textDueDate
        var importance = binding.textStory
        var sprintName = binding.textSprintName

        var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")

        binding.buttonFinish.setOnClickListener{

            sprint.projectId = projectId.toLong()
            sprint.sprintName = sprintName.text.toString()
            sprint.description = description.text.toString()
//            sprint.endTime =

        }
    }
}