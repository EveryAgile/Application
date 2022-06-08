package com.example.myapplication.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.model_backlog

class adapter_backlog(val context: Context, val BacklogList: ArrayList<model_backlog>) : BaseAdapter(){
    override fun getCount(): Int {
        return BacklogList.size
    }

    override fun getItem(position: Int): Any {
        return BacklogList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.sprint_backlog_list_model, null)

        val backlog_thumbnail = view.findViewById<ImageView>(R.id.backlog_thumbnail)
        val backlog_name = view.findViewById<TextView>(R.id.backlog_name)
        val backlog_time = view.findViewById<TextView>(R.id.backlog_time)

        val backlog = BacklogList[position]
        backlog_thumbnail.setImageResource(backlog.thumbnail)
        backlog_name.text = backlog.name
        backlog_time.text = backlog.time

        return view
    }

}