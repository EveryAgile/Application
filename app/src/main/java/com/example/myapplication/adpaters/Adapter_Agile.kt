package com.example.myapplication.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.model_Agile

class adapter_Agile(val context: Context, val AgileList: ArrayList<model_Agile>) : BaseAdapter(){
    override fun getCount(): Int {
        return AgileList.size
    }

    override fun getItem(position: Int): Any {
        return AgileList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.product_backlog_list_model, null)

        val thumbnail = view.findViewById<ImageView>(R.id.thumbnail)
        val name = view.findViewById<TextView>(R.id.name)
        val personel1 = view.findViewById<ImageView>(R.id.imageView_personel1)
        val personel2 = view.findViewById<ImageView>(R.id.imageView_personel2)

        val agile = AgileList[position]
        thumbnail.setImageResource(agile.thumbnail)
        name.text = agile.name
        personel1.setImageResource(agile.personel1)
        personel2.setImageResource(agile.personel2)

        return view
    }

}