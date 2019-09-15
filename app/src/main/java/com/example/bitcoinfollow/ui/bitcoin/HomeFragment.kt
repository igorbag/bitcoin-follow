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
import com.example.bitcoinfollow.model.bitcoin.BitcoinValue
import com.example.bitcoinfollow.utils.Result
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.bottom_sheet_list_values_bitcoin.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class HomeFragment : Fragment() {

    private val bitcoinViewModel: BitcoinViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitcoinInfo = bitcoinViewModel.getBitcoinMarketPriceChart()
        setUpViewModel(bitcoinInfo)
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
        bitcoinInfo.values
        setupLineChartData(bitcoinInfo.values)
        setUpAdapter(bitcoinInfo.values)
        observable.removeObservers(this)
    }

    private fun onError(observable: MutableLiveData<Result<BitcoinInfo>>) {
        observable.removeObservers(this)
    }

    //@TODO Extrair todo o gerenciamento do grafico utilizando Builder Pattern
    private fun setupLineChartData(listBitcoinValues: List<BitcoinValue>) {
        val chartValues = formatLineBarGraphInformation(listBitcoinValues)
        val dataSets = setUpDataSetGraph(chartValues)
        val data = LineData(dataSets)
        setUpLineChart(data)
    }

    private fun setUpLineChart(data: LineData) {
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
        lineChart.notifyDataSetChanged()
        lineChart.invalidate()
    }

    private fun setUpDataSetGraph(chartValues: ArrayList<Entry>): ArrayList<ILineDataSet> {
        val lineDataSet = LineDataSet(chartValues, "DataSet 1")
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

        lineDataSet.setCircleColor(Color.WHITE)
        lineDataSet.setCircleColor(Color.WHITE)
        lineDataSet.color = Color.WHITE
        lineDataSet.isHighlightEnabled = false
        lineDataSet.fillDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.shape_graph)
        lineDataSet.lineWidth = 2f
        lineDataSet.lineWidth = 2f
        lineDataSet.circleRadius = 5f
        lineDataSet.setDrawCircleHole(false)
        lineDataSet.setDrawCircles(true)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)
        return dataSets
    }

    private fun formatLineBarGraphInformation(listBitcoinValues: List<BitcoinValue>): ArrayList<Entry> {
        val chartValues = ArrayList<Entry>()

        listBitcoinValues.forEachIndexed { index, bitcoinValue ->
            chartValues.add(Entry(index.toFloat(), bitcoinValue.y))

        }
        return chartValues
    }

    private fun setUpAdapter(listBitcoinValues: List<BitcoinValue>) {
        rvBitcoinsValues.adapter = BitcoinValuesAdapter(listBitcoinValues) {

        }
    }
}
