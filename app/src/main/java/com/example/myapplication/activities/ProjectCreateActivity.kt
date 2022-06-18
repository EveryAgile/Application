package com.example.myapplication.activities

import android.R
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.activities.SignInActivity
import com.example.myapplication.databinding.ActivityProjectCreateBinding
import com.example.myapplication.models.ProjectCreate
import com.example.myapplication.models.ProjectRequestDto
import com.example.myapplication.models.Type
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProjectCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProjectCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var project = ProjectRequestDto()
        var projectName = binding.editName
        var projectStart = binding.textStartDate
        var projecEnd = binding.textEndDate
        var projectType = binding.spinType

        var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")

        val priorities = arrayOf("DEFAUT", "DEVELOP")
        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, priorities)
        binding.spinType.adapter = spinnerAdapter

        btnStartDate(binding, projectStart)

        btnEndDate(binding, projecEnd)

        createProject(
            binding,
            project,
            projectName,
            projectStart,
            projecEnd,
            projectType,
            accessToken
        )
    }

    private fun createProject(
        binding: ActivityProjectCreateBinding,
        project: ProjectRequestDto,
        projectName: EditText,
        projectStart: EditText,
        projecEnd: EditText,
        projectType: Spinner,
        accessToken: String
    ) {
        binding.btnCreate.setOnClickListener {

            project.projectName = projectName.text.toString()
            project.startTime = projectStart.text.toString()
            project.endTime = projecEnd.text.toString()
            project.type = projectType.selectedItem.toString()


            val call: Call<ProjectCreate> =
                RetrofitClient.networkService.postProject(accessToken, project)
            call.enqueue(object : Callback<ProjectCreate> {
                override fun onResponse(
                    call: Call<ProjectCreate>,
                    response: Response<ProjectCreate>
                ) {
                    if (response.isSuccessful) {
                        Log.d("로그인", "성공 : ${response.body()}")
                    } else {
                        Log.d("로그인", "실패1 : ${response.errorBody()?.string()!!}")
                        Log.d("로그인", accessToken.toString())
                    }
                }

                override fun onFailure(call: Call<ProjectCreate>, t: Throwable) {
                    Log.d("로그인", "실패2 : $t")
                }
            })
            finish()
        }
    }

    private fun btnEndDate(
        binding: ActivityProjectCreateBinding,
        projecEnd: EditText
    ) {
        binding.buttonEndDate.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)

            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    projecEnd.setText("${year}년 ${month + 1}월 ${dayOfMonth}일")
                }
            }, year, month, date)
            dlg.show()
        }
    }

    private fun btnStartDate(
        binding: ActivityProjectCreateBinding,
        projectStart: EditText
    ) {
        binding.buttonStartDate.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)

            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    projectStart.setText("${year}년 ${month + 1}월 ${dayOfMonth}일")
                }
            }, year, month, date)
            dlg.show()
        }
    }
}