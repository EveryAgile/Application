package com.example.myapplication.activities

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCreateProductBacklogBinding
import com.example.myapplication.models.ProjectResponseDto
import com.example.myapplication.models.SprintCreate
import com.example.myapplication.models.SprintRequestDto
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProductCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateProductBacklogBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val projectId = intent.getLongExtra("projectId",0)
        Log.d("projectid", projectId.toString())
        var accessToken = SignInActivity.prefs.getString("ACCESS_TOKEN","")

        var product = SprintRequestDto()
        var productName = binding.textProductName
        var intro = binding.editIntroduction
        var dueDate = binding.textDueDate
        var spinner = binding.spinnerPrioirity
        var users = mutableListOf<String>()

        val priorities = arrayOf("LOW", "DEFAULT", "HIGH")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, priorities)
        binding.spinnerPrioirity.adapter = spinnerAdapter

        var intent_Agile = Intent(this, ProjectActivity::class.java)
        val is_new = intent.getBooleanExtra("is_new", true)

        //마감일 선택
        choiceDueDate(binding, dueDate)

        //job에 할당할 인원 지정
        assignMember(binding, users)



        //뒤로 돌아가기 버튼 누르면 돌아가기
        binding.buttonBack.setOnClickListener{
            startActivity(intent_Agile)
        }

        //완료버튼
        registerProduct(
            binding,
            product,
            productName,
            intro,
            dueDate,
            spinner,
            projectId,
            users,
            accessToken
        )
    }

    private fun registerProduct(
        binding: ActivityCreateProductBacklogBinding,
        product: SprintRequestDto,
        productName: EditText,
        intro: EditText,
        dueDate: EditText,
        spinner: Spinner,
        projectId: Long,
        users: MutableList<String>,
        accessToken: String
    ) {
        binding.buttonFinish.setOnClickListener {


            product.sprintName = productName.text.toString()
            product.description = intro.text.toString()
            product.endTime = dueDate.text.toString()
            product.importance = spinner.selectedItem.toString()
            product.projectId = projectId
            product.users = users

            val call: Call<SprintCreate> =
                RetrofitClient.networkService.postProduct(accessToken, product)
            call.enqueue(object : Callback<SprintCreate> {
                override fun onResponse(
                    call: Call<SprintCreate>,
                    response: Response<SprintCreate>
                ) {
                    if (response.isSuccessful) {
                        Log.d("로그인", "성공 : ${response.body()}")
                        finish()
                    } else {
                        Log.d("로그인", "실패1 : ${response.errorBody()?.string()!!}")
                    }
                }

                override fun onFailure(call: Call<SprintCreate>, t: Throwable) {
                    Log.d("로그인", "실패2 : $t")
                }
            })

            finish()
        }
    }

    private fun assignMember(
        binding: ActivityCreateProductBacklogBinding,
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
        binding: ActivityCreateProductBacklogBinding,
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