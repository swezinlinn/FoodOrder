package com.android.order.presentation.adapter

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.order.R
import com.android.order.domain.model.OrderList
import com.android.order.util.*
import java.util.*


class OrderListAdapter()
    : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    private var list: ArrayList<OrderList> = ArrayList()
    private lateinit var context: Context
    private var lstHolders: MutableList<ViewHolder> = mutableListOf()

    constructor(context: Context, order: ArrayList<OrderList>) : this() {
        this.context = context
        this.list = order
        startUpdateTimer()
    }

    private val mHandler = Handler()
    private val updateRemainingTimeRunnable = Runnable {
        synchronized(lstHolders) {
            for (holder in lstHolders) {
                holder.updateTimeRemaining()
            }
        }
    }


    private fun startUpdateTimer() {
        val tmr = Timer()
        tmr.schedule(object : TimerTask() {
            override fun run() {
                mHandler.post(updateRemainingTimeRunnable)
            }
        }, 1000, 1000)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_order_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list[position],context)
        synchronized (lstHolders) {
            lstHolders.add(holder);
        }
        holder.updateTimeRemaining()
        holder.accept.setOnClickListener {
            updateIngredient(list[position])
        }

        holder.expire.setOnClickListener {
            updateIngredient(list[position])
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemOrderId: TextView
        var createdTime: TextView
        var eclipseTime: TextView
        var progressBar: ProgressBar
        var rlItem: RecyclerView
        var itemQuantity: TextView
        var accept: Button
        var expire: Button
        var divider: View
        var mModel : OrderList = OrderList()
        var mContext : Context? = null

        fun setData(item: OrderList,context: Context) {
            mModel = item
            mContext = context
            itemOrderId.text = context.getString(R.string.order_id,mModel.id.toString())
            createdTime.text = context.getString(R.string.created_at, getDateString(getDate(mModel.createdAt)))
            itemQuantity.text = context.getString(R.string.item_quantity,mModel.quantity.toString())
            val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            rlItem.layoutManager = mLayoutManager
            rlItem.setHasFixedSize(true)
            val adapter = ItemAdapter(context,mModel.item)
            rlItem.adapter = adapter
            progressBar.max = (getExpiredAt(mModel) - getCreatedAt(mModel)).toInt()
            updateTimeRemaining()
        }

        fun updateTimeRemaining() {
            val timeDiff: Long = getExpiredAt(mModel) - System.currentTimeMillis()
            val alertTime: Long = getAlertAt(mModel) - System.currentTimeMillis()
            if(alertTime < 0 && timeDiff > 0)   {
                playAlarm()
            }

            if (timeDiff > 0) {
                val seconds = (timeDiff / 1000).toInt() % 60
                val minutes = (timeDiff / (1000 * 60) % 60).toInt()
                val hours = (timeDiff / (1000 * 60 * 60) % 24).toInt()
                Log.d("OrderListAdapter","elipse time--->\"$hours hrs $minutes mins $seconds sec\"")
                eclipseTime.text = mContext?.getString(R.string.auto_reject,"$hours hrs $minutes mins $seconds sec")
                progressBar.progress = timeDiff.toInt()
                accept.makeVisible()
                eclipseTime.makeVisible()
                progressBar.makeVisible()
                expire.makeGone()
                divider.makeVisible()
            } else {
                eclipseTime.text = mContext?.getString(R.string.auto_reject,":")
                accept.makeGone()
                eclipseTime.makeInvisible()
                progressBar.makeInvisible()
                expire.makeVisible()
                divider.makeGone()
            }

        }

        private fun playAlarm() {
            var mediaPlayer: MediaPlayer? = null
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer.stop()
            }
            mediaPlayer =  MediaPlayer.create(mContext, R.raw.alarm)

            mediaPlayer?.start()
        }

        init {
            itemOrderId = itemView.findViewById(R.id.orderId)
            createdTime = itemView.findViewById(R.id.createdTime)
            eclipseTime= itemView.findViewById(R.id.autoReject)
            progressBar = itemView.findViewById(R.id.progressBar)
            rlItem= itemView.findViewById(R.id.rlItem)
            itemQuantity = itemView.findViewById(R.id.itemViewQuantity)
            accept = itemView.findViewById(R.id.btnAccept)
            expire = itemView.findViewById(R.id.btnExpire)
            divider = itemView.findViewById(R.id.divider)
        }
    }


    private fun updateIngredient(order: OrderList){
        list.remove(order)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}