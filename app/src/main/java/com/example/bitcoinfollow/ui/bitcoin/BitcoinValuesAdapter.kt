package com.example.bitcoinfollow.ui.bitcoin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcoinfollow.R
import com.example.bitcoinfollow.model.bitcoin.BitcoinValue
import kotlinx.android.synthetic.main.list_item_bitcoin_value.view.*
import java.text.SimpleDateFormat
import java.util.*


class BitcoinValuesAdapter(
    val bitcoins: List<BitcoinValue>,
    val clickListener: (BitcoinValue) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount() = bitcoins.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_bitcoin_value, parent, false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PartViewHolder).bind(bitcoins[position], clickListener)
    }


    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bitcoinValue: BitcoinValue, clickListener: (BitcoinValue) -> Unit) {
            itemView.tvNameOfMonth.text = convertTimeInMillisToBrDate(bitcoinValue)
            itemView.tvBitcoinRateValue.text = bitcoinValue.y.toString()
        }

        private fun convertTimeInMillisToBrDate(bitcoinValue: BitcoinValue): String? {
            val timeInMillis: Int = bitcoinValue.x
            val date = Date(timeInMillis * 1000L)
            val simpleDateFormater = SimpleDateFormat("dd/MM/yyyy")
            simpleDateFormater.timeZone = TimeZone.getTimeZone("GMT-4")
            val dateFormated = simpleDateFormater.format(date)
            return dateFormated
        }
    }
}
