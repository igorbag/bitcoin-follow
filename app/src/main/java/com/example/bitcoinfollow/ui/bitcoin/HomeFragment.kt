package com.example.bitcoinfollow.ui.bitcoin

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bitcoinfollow.R
import com.example.bitcoinfollow.model.bitcoin.BitcoinInfo
import com.example.bitcoinfollow.utils.Result
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class HomeFragment : Fragment() {

    private val bitcoinViewModel: BitcoinViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitcoinInfo = bitcoinViewModel.getBitcoinMarketPriceChart()
        setUpViewModel(bitcoinInfo)
        setupLineChartData()
    }

    private fun setUpViewModel(bitcoinInfo: MutableLiveData<Result<BitcoinInfo>>) {
        bitcoinInfo.observe(this, Observer {
            when (it) {
                is Result.Loading -> onLoading()
                is Result.Success -> onSuccess(it.data, bitcoinInfo)
                is Result.Error -> onError(bitcoinInfo)

            }

        })
    }


    private fun onLoading() {
        Log.d("Log -> ", "Loading ....")
    }

    private fun onSuccess(
        bitcoinInfo: BitcoinInfo,
        observable: MutableLiveData<Result<BitcoinInfo>>
    ) {
        Log.d("Data ->", "Teste ....")
        observable.removeObservers(this)
    }

    private fun onError(observable: MutableLiveData<Result<BitcoinInfo>>) {
        observable.removeObservers(this)
    }


    private fun setupLineChartData() {
        val chartValues = ArrayList<Entry>()
        chartValues.add(Entry(0f, 30f))
        chartValues.add(Entry(1f, 2f))
        chartValues.add(Entry(2f, 4f))
        chartValues.add(Entry(3f, 6f))
        chartValues.add(Entry(4f, 8f))
        chartValues.add(Entry(5f, 10f))
        chartValues.add(Entry(6f, 22f))
        chartValues.add(Entry(7f, 12.5f))
        chartValues.add(Entry(8f, 22f))
        chartValues.add(Entry(9f, 32f))
        chartValues.add(Entry(10f, 54f))
        chartValues.add(Entry(11f, 28f))

        val lineDataSet: LineDataSet
        lineDataSet = LineDataSet(chartValues, "DataSet 1")
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.color = Color.WHITE

        lineDataSet.setCircleColor(Color.WHITE)
        lineDataSet.lineWidth = 2f
        lineDataSet.setDrawCircleHole(false)
        lineDataSet.setDrawCircles(true)
        lineDataSet.setCircleColor(Color.WHITE)
        lineDataSet.lineWidth = 2f
        lineDataSet.circleRadius = 5f
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_graph)
        lineDataSet.fillDrawable = drawable


        lineDataSet.isHighlightEnabled = false

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)
        val data = LineData(dataSets)

        // set data
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.xAxis.isEnabled = false
        lineChart.axisLeft.isEnabled = true
        lineChart.axisRight.isEnabled = false
        lineChart.axisLeft.axisLineColor = ContextCompat.getColor(
            requireContext(),
            R.color.colorLineAxisChart
        )
        lineChart.axisLeft.textColor = ContextCompat.getColor(
            requireContext(),
            R.color.textBlueLight
        )

        lineChart.setScaleEnabled(false)
        lineChart.data = data


    }
}
