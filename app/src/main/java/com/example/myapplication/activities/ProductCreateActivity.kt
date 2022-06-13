package com.example.myapplication.activities

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.adpaters.adapter_backlog
import com.example.myapplication.databinding.ActivityCreateProductBacklogBinding
import com.example.myapplication.model_backlog
import com.example.myapplication.models.*
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProductCreateActivity : AppCompatActivity() {
    private var mBinding: ActivityCreateProductBacklogBinding? = null
    private val binding get() = mBinding!!
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCreateProductBacklogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var BacklogList = arrayListOf<model_backlog>()
        var intent_Agile = Intent(this, ProjectActivity::class.java)
        var accessToken = intent.getStringExtra("accessToken")
        var projectId = intent.getIntExtra("projectId", 7)
        var isNew = intent.getBooleanExtra("isNew", false)
        var sprint = intent.getParcelableExtra<Sprint>("sprint")
        //스피너 설정
        val priorities = arrayOf("하", "중", "상")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, priorities)
        binding.spinnerPrioirity.adapter = spinnerAdapter

        //새로 만드는 버튼을 눌러서 이 화면 온게 아니라면 DB에서 데이터 불러옴
        if (!isNew){

            val sprintMembersCall: Call<sprintMembersResult> =
                RetrofitClient.networkService.sprintMembers(accessToken=accessToken, sprint?.sprintId)
            sprintMembersCall.enqueue(object : Callback<sprintMembersResult> {
                override fun onResponse(
                    call: Call<sprintMembersResult>,
                    response: Response<sprintMembersResult>
                ){
                    if (response.isSuccessful) {
                        Log.d("스프린트 담당자", "성공 : ${response.body()}")
                        var personel = response.body()?.list
                        var tempString = ""
                        for (i: Int in 0 until personel!!.size){
                            if (i == 0) {
                                tempString += personel[i].email
                            }else{
                                tempString += ", " + personel[i].email
                            }
                        }
                        binding.textTeamMember.setText(tempString)
                    } else {
                        Log.d("스프린트 담당자", "실패 :${response.errorBody()?.string()!!}")
                    }
                }
                override fun onFailure(call: Call<sprintMembersResult>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            binding.editProjectName.setText(sprint?.sprintName)
            binding.editIntroduction.setText(sprint?.description)
            binding.textDueDate.setText(sprint?.endTime)
            if (sprint?.importance == "LOW") {
                binding.spinnerPrioirity.setSelection(0)
            }else if (sprint?.importance == "DEFAULT"){
                binding.spinnerPrioirity.setSelection(1)
            }else{
                binding.spinnerPrioirity.setSelection(2)
            }
        }

        //마감일 선택
        binding.buttonDate.setOnClickListener{
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)

            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.textDueDate.setText("${year}년 ${month+1}월 ${dayOfMonth}일")
                }
            }, year, month, date)
            dlg.show()
        }

        //job에 할당할 인원 지정
        binding.buttonPersonnel.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_invitation, null)
            val email = dialogView.findViewById<EditText>(R.id.edit_name)
            builder.setView(dialogView).setPositiveButton("Add"){
                    dialogInterface, i->
                val projectMembersCall: Call<InquiryMembersResult> =
                    RetrofitClient.networkService.inquiryMembers(accessToken=accessToken, projectId)
                projectMembersCall.enqueue(object : Callback<InquiryMembersResult> {
                    override fun onResponse(
                        call: Call<InquiryMembersResult>,
                        response: Response<InquiryMembersResult>
                    ){
                        if (response.isSuccessful) {
                            Log.d("프로젝트 멤버 소속", "성공 : ${response.body()}")
                            var members = response.body()?.list
                            for (i: Int in 0 until members!!.size){
                                var flag = false
                                if (members[i].email == email.text.toString()){
                                    if (binding.textTeamMember.text.toString() == "배정하기") {
                                        binding.textTeamMember.setText(email.text.toString())
                                    } else {
                                        binding.textTeamMember.setText(
                                            binding.textTeamMember.text.toString() + ", " + email.text.toString()
                                        )
                                    }
                                    break
                                }
                                if (!flag){
                                    Toast.makeText(applicationContext, "No such member", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Log.d("프로젝트 멤버 소속", "실패 : ${response.errorBody()?.string()!!}")
                        }
                    }
                    override fun onFailure(call: Call<InquiryMembersResult>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })

            }
                .setNegativeButton("Cancel"){
                        dialogInterface, i->
                }
            email.setOnClickListener {
                email.setText("")
            }
            builder.show()
        }

        //ListView adapter
        val adapter = adapter_backlog(this, BacklogList)
        binding.ListViewJob.adapter = adapter

        //뒤로 돌아가기 버튼 누르면 돌아가기
        binding.buttonBack.setOnClickListener{
            intent_Agile.putExtra("accessToken", accessToken)
            startActivity(intent_Agile)
        }

        //완료버튼
        binding.buttonFinish.setOnClickListener{
            //스프린트 수정이 없어서 새로 만드는 것이 아니면 지우고 다시 만드는걸로할게요
            if (!isNew){
                val sprintDeleteCall: Call<SprintDeleteResult> =
                    RetrofitClient.networkService.sprintDelete(accessToken=accessToken, sprint?.sprintId)
                sprintDeleteCall.enqueue(object : Callback<SprintDeleteResult> {
                    override fun onResponse(
                        call: Call<SprintDeleteResult>,
                        response: Response<SprintDeleteResult>
                    ){
                        if (response.isSuccessful) {
                            Log.d("스프린트 삭제", "성공 : ${response.body()}")
                        } else {
                            Log.d("스프린트 삭제", "실패 :${response.errorBody()?.string()!!}")
                        }
                    }
                    override fun onFailure(call: Call<SprintDeleteResult>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }
            var sprintName: String = binding.editProjectName.text.toString()
            var description: String = binding.editIntroduction.text.toString()
            var importance: String = binding.spinnerPrioirity.selectedItem.toString()
            if (importance.equals("상")){
                importance = "HIGH"
            }else if (importance.equals("중")) {
                importance = "DEFAULT"
            }else{
                importance = "LOW"
            }
            var endTime: String = binding.textDueDate.text.toString()
            var users = binding.textTeamMember.text.toString().split(", ")
            var createSprint = CreateSprint(description, endTime, importance, projectId,
                sprintName, users)
            val createSprintCall: Call<CreateSprintResult> =
                RetrofitClient.networkService.createSprint(accessToken=accessToken, createSprint)
            createSprintCall.enqueue(object : Callback<CreateSprintResult> {
                override fun onResponse(
                    call: Call<CreateSprintResult>,
                    response: Response<CreateSprintResult>
                ){
                    if (response.isSuccessful) {
                        Log.d("스프린트 생성", "성공 : ${response.body()}")
                    } else {
                        Log.d("스프린트 생성", "실패 :${response.errorBody()?.string()!!}")
                    }
                }
                override fun onFailure(call: Call<CreateSprintResult>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
            intent_Agile.putExtra("accessToken", accessToken)
            startActivity(intent_Agile)
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}