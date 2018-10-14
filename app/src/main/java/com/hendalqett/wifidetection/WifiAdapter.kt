package com.hendalqett.wifidetection

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.header_list_item.view.*
import kotlinx.android.synthetic.main.show_more_list_item.view.*
import kotlinx.android.synthetic.main.wifi_list_item.view.*

/**
 * Created by hend on 10/13/18.
 */
class WifiAdapter(private val wifiNetworksList: List<Any>, val itemClickedListener: OnItemClickedListener, val buttonClickedListener: OnButtonClickedListener?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_WIFI = 0
        const val TYPE_BUTTON = 1
        const val TYPE_HEADER = 2
    }

    class WifiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: WifiNetwork, itemClickedListener: OnItemClickedListener) = with(itemView) {
            textViewWifi.text = item.name
            imageViewWifi.setImageResource(getSignalImage(item.level))
            setOnClickListener {
                itemClickedListener.onClicked(item)
            }
        }

        private fun getSignalImage(level: Int): Int {

            return when (level) {
                in 0 downTo -50 -> R.drawable.ic_signal_wifi_4_bar_black
                in -51 downTo -60 -> R.drawable.ic_signal_wifi_3_bar_black
                in -61 downTo -70 -> R.drawable.ic_signal_wifi_2_bar_black
                in -71 downTo -80 -> R.drawable.ic_signal_wifi_1_bar_black
                else -> R.drawable.ic_signal_wifi_0_bar_black
            }

        }
    }

    class ShowMoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(buttonClickedListener: OnButtonClickedListener?) = with(itemView) {

            buttonShowMore.setOnClickListener {
                buttonClickedListener?.onClicked()
            }
        }
    }

    class HeaderViewHolder(textView: TextView) : RecyclerView.ViewHolder(textView) {
        fun bind(headerText: String) = with(itemView) {
            textViewListHeader.text = headerText
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_WIFI -> WifiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.wifi_list_item, parent, false))
            TYPE_BUTTON -> ShowMoreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.show_more_list_item, parent, false))
            else -> {
                HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_list_item, parent, false) as TextView)
            }
        }
    }

    override fun getItemCount(): Int {
        return wifiNetworksList.size
    }

    override fun getItemViewType(position: Int): Int {
        val type = when (wifiNetworksList[position]) {
            is WifiNetwork -> TYPE_WIFI
            is Int -> TYPE_BUTTON
            else -> TYPE_HEADER

        }
        return type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            wifiNetworksList[position] is WifiNetwork -> (holder as WifiViewHolder).bind(wifiNetworksList[position] as WifiNetwork, itemClickedListener)
            wifiNetworksList[position] is Int -> (holder as ShowMoreViewHolder).bind(buttonClickedListener)
            else -> (holder as HeaderViewHolder).bind(wifiNetworksList[position] as String)
        }


    }

    interface OnItemClickedListener {
        fun onClicked(network: WifiNetwork)
    }

    interface OnButtonClickedListener {
        fun onClicked()
    }


}

