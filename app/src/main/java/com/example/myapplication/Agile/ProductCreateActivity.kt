package com.example.myapplication.Agile

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.adpaters.adapter_backlog
import com.example.myapplication.databinding.ActivityCreateSprintBinding
import com.example.myapplication.model_backlog
import java.util.*

class ProductCreateActivity : AppCompatActivity() {
    var BacklogList = arrayListOf<model_backlog>(
        model_backlog(R.drawable.ic_circledcheck, "test1", "3"),
        model_backlog(R.drawable.ic_circledcheck, "test1", "2"),
        model_backlog(R.drawable.ic_circledcheck, "test1", "1")
    )

    private var mBinding: ActivityCreateSprintBinding? = null
    private val binding get() = mBinding!!
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCreateSprintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val priorities = arrayOf("하", "중", "상")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, priorities)
        binding.spinnerPrioirity.adapter = spinnerAdapter

        var intent_Agile = Intent(this, ProjectCreateActivity::class.java)
        val is_new = intent.getBooleanExtra("is_new", true)

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
            val dialogView = layoutInflater.inflate(R.layout.invitation_dialog, null)
            val name = dialogView.findViewById<EditText>(R.id.edit_name)

            builder.setView(dialogView).setPositiveButton("Add"){
                    dialogInterface, i->
                if (binding.textTeamMember.text.toString() == "배정하기"){
                    binding.textTeamMember.setText(name.text.toString() + " ")
                }else {
                    binding.textTeamMember.setText(
                    binding.textTeamMember.text.toString() + name.text.toString() + " ")
                }
            }
                .setNegativeButton("Cancel"){
                        dialogInterface, i->
                }
            name.setOnClickListener {
                name.setText("")
            }
            builder.show()
        }

        //ListView adapter
        val adapter = adapter_backlog(this, BacklogList)
        binding.ListViewJob.adapter = adapter

        //뒤로 돌아가기 버튼 누르면 돌아가기
        binding.buttonBack.setOnClickListener{
            startActivity(intent_Agile)
        }

        //완료버튼
        binding.buttonFinish.setOnClickListener{
            startActivity(intent_Agile)
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}