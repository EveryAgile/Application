package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityBurndownBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.lang.Math.exp


class BurndownActivity : AppCompatActivity() {
    private var mBinding: ActivityBurndownBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBurndownBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var burndownChart: LineChart = binding.burndown
        var entries = ArrayList<Entry>()
        for (x in 1..10){
            entries.add(Entry(x.toFloat(), exp((-1 * x.toDouble())).toFloat()))
        }
        var dataset = LineDataSet(entries, "left points")
        var data = LineData(dataset)
        burndownChart.data = data
    }
}