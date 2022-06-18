package com.example.myapplication.activities

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.EditText
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCreateProductBacklogBinding
import com.example.myapplication.databinding.ActivityCreateSprintBacklogBinding
import com.example.myapplication.models.*
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SprintCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateSprintBacklogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sprint = BacklogRequestDto()

        val productId = intent.getLongExtra("productId",0)

        var endTime = binding.textDueDate
        var importance = binding.textStory
        var sprintName = binding.textSprintName
        var manDay = binding.editDay
        var users = mutableListOf<String>()

        choiceDueDate(binding, endTime)
        assignMember(binding, users)

        var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")

        createeSprint(
            binding,
            sprint,
            sprintName,
            endTime,
            manDay,
            importance,
            productId,
            accessToken
        )
    }

    private fun createeSprint(
        binding: ActivityCreateSprintBacklogBinding,
        sprint: BacklogRequestDto,
        sprintName: EditText,
        endTime: EditText,
        manDay: EditText,
        importance: EditText,
        productId: Long,
        accessToken: String
    ) {
        binding.buttonFinish.setOnClickListener {

            sprint.backlogName = sprintName.text.toString()
            sprint.endTime = endTime.text.toString()
            sprint.manDay = manDay.text.toString().toDouble()
            sprint.storyPoint = importance.text.toString().toDouble()
            sprint.sprintId = productId


            22
            val call: Call<CreateBacklog> =
                RetrofitClient.networkService.postSprint(accessToken, sprint)
            call.enqueue(object : Callback<CreateBacklog> {
                override fun onResponse(
                    call: Call<CreateBacklog>,
                    response: Response<CreateBacklog>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.toString()?.let { Log.d("로그인", it) }

                    } else {
                        Log.d("로그인", "실패1 : ${response.errorBody()?.string()!!}")
                    }
                }

                override fun onFailure(call: Call<CreateBacklog>, t: Throwable) {
                    Log.d("로그인", "실패2 : $t")
                }
            })
        }
    }

    private fun assignMember(
        binding: ActivityCreateSprintBacklogBinding,
        users: MutableList<String>
    ) {
        binding.buttonPersonnel.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_invitation, null)
            val name = dialogView.findViewById<EditText>(R.id.edit_name)

            builder.setView(dialogView).setPositiveButton("Add") { dialogInterface, i ->
                if (binding.textTeamMember.text.toString() == "배정하기") {
                    binding.textTeamMember.setText(name.text.toString() + " ")
                    users.add(name.text.toString())
                } else {
                    binding.textTeamMember.setText(
                        binding.textTeamMember.text.toString() + name.text.toString() + " "
                    )
                    users.add(name.text.toString())
                }
            }
                .setNegativeButton("Cancel") { dialogInterface, i ->
                }
            name.setOnClickListener {
                name.setText("")
            }
            builder.show()
        }
    }

    private fun choiceDueDate(
        binding: ActivityCreateSprintBacklogBinding,
        dueDate: EditText
    ) {
        binding.buttonDate.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)

            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    dueDate.setText("${year}년 ${month + 1}월 ${dayOfMonth}일")
                }
            }, year, month, date)
            dlg.show()
        }
    }
}