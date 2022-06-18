package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySprintBinding
import com.example.myapplication.models.BacklogList
import com.example.myapplication.models.BacklogResponseDto
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SprintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySprintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")
        val backlogId = intent.getLongExtra("backlogId",0)

        var backlogName = binding.textSprintName
        var endTime = binding.textDueDate
        var storyP = binding.textStory
        var intro = binding.editIntroduction
        var manDay = binding.editDay

//        val call: Call<BacklogList> =
//            RetrofitClient.networkService.getSprint(accessToken,backlogId)
//        call.enqueue(object : Callback<BacklogList> {
//            override fun onResponse(
//                call: Call<BacklogList>,
//                response: Response<BacklogList>
//            ) {
//                if (response.isSuccessful) {
//                    var sprintList: MutableList<BacklogResponseDto>? = response.body()?.list
//                    backlogName.setText(sprintList.n)
//                    endTime.setText(productList?.endTime.toString())
//                    storyP.setText(productList?.decription.toString())
//                    intro.setText()
//                    manDay.setText()
//                    Log.d("스프린트", "성공 : ${response.body()}")
//                } else {
//                    Log.d("스프린트", "실패1 : ${response.errorBody()?.string()!!}")
//                }
//            }

//            override fun onFailure(call: Call<BacklogList>, t: Throwable) {
//                Log.d("스프린트", "실패2 : $t")
//            }
//        })

    }
}