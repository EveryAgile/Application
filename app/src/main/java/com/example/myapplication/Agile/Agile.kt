package com.example.myapplication.Agile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.AdapterView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.adpaters.adapter_Agile
import com.example.myapplication.create.Create_job
import com.example.myapplication.databinding.ActivityAgileBinding
import com.example.myapplication.model_Agile

class Agile : AppCompatActivity() {
    private var mBinding: ActivityAgileBinding? = null
    private val binding get() = mBinding!!
    var AgileList = arrayListOf<model_Agile>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = Intent(this, Create_job::class.java)
        mBinding = ActivityAgileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //+버튼 누르면 할일 만드는 페이지로 넘어감
        binding.createProject.setOnClickListener {
            intent.putExtra("is_new", true)
            startActivity(intent)
        }

        //ListView adapter
        val adapter = adapter_Agile(this, AgileList)
        binding.listViewAgile.adapter = adapter

        //ListView에서 item 클릭되었을 때 이벤트
        binding.listViewAgile.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as model_Agile
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()
        }
    }
    
    //create_job activity가 끝나고 돌아왔을 때 listView 업데이트
    override fun onResume(){
        super.onResume()
        if (intent.getSerializableExtra("model_agile") != null) {
            var model_agile = intent.getSerializableExtra("model_agile") as model_Agile
            AgileList.add(model_agile)
        }
        val adapter = adapter_Agile(this, AgileList)
        binding.listViewAgile.adapter = adapter
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

}