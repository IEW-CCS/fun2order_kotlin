// Chris Modify

package com.iew.fun2order.ui.notifications

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iew.fun2order.R
import com.iew.fun2order.ui.my_setup.IAdapterOnClick
import com.iew.fun2order.ui.my_setup.listen
import com.iew.fun2order.utility.*
import kotlinx.android.synthetic.main.row_notification.view.*
import java.text.SimpleDateFormat


class AdapterRC_Notification(var context: Context, var lstItemsNotify:List<ItemsLV_Notify>, val IAdapterOnClick: IAdapterOnClick)  : RecyclerView.Adapter<AdapterRC_Notification.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 指定 layout
        val view = LayoutInflater.from(context).inflate(R.layout.row_notification,  null)
        return ViewHolder(view).listen()
        { pos, type ->
            IAdapterOnClick.onClick("Notify", pos,type)
        }
    }

    override fun getItemCount(): Int {
        return lstItemsNotify.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindModel( lstItemsNotify[position] )
    }

    // view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        @SuppressLint("SimpleDateFormat")
        fun bindModel(ItemsLV_Notify: ItemsLV_Notify){

            itemView.notifytitle.text = "來自 ${ItemsLV_Notify.notifyTitle} 的團購訊息"
            itemView.notifycategory.text = ItemsLV_Notify.notifycategory

            val sdfDecode = SimpleDateFormat("yyyyMMddHHmmssSSS")
            val sdfEncode = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
            val replyDateTime = sdfDecode.parse(ItemsLV_Notify.notifydatetime)
            val formatReplyDatetime = sdfEncode.format(replyDateTime).toString()

            when(ItemsLV_Notify.msgType)
            {
                NOTIFICATION_TYPE_ACTION_JOIN_ORDER ->
                {
                    itemView.messageTitle.text = "團購邀請"
                    itemView.messageTitle.setTextColor(Color.rgb(0, 122, 255))
                }
                NOTIFICATION_TYPE_MESSAGE_DUETIME->
                {
                    itemView.messageTitle.text = "團購催訂"
                    itemView.messageTitle.setTextColor(Color.rgb(177, 0, 28))
                }

                NOTIFICATION_TYPE_MESSAGE_INFORMATION ->
                {
                    itemView.messageTitle.text = "團購訊息"
                    itemView.messageTitle.setTextColor(Color.rgb(177, 0, 28))
                }

            }


            itemView.notifydatetime.text = formatReplyDatetime
            itemView.notifdesc.setText ( ItemsLV_Notify.desc)
            itemView.notifdesc.setOnClickListener(View.OnClickListener {
                IAdapterOnClick.onClick("Notify", adapterPosition,0)
                })

            if(ItemsLV_Notify.ackStatus == "" || ItemsLV_Notify.ackStatus == MENU_ORDER_REPLY_STATUS_WAIT)
            {
                itemView.acknowledge.text = "尚未回覆"
                itemView.acknowledge.setTextColor(Color.rgb(0, 122, 255))
                itemView.joinstatus.text = ""
            }
            else
            {
                itemView.acknowledge.text = "已回覆"
                itemView.acknowledge.setTextColor(Color.rgb(0, 122, 255))

                when(ItemsLV_Notify.ackStatus)
                {
                    MENU_ORDER_REPLY_STATUS_ACCEPT ->
                    {
                        itemView.joinstatus.text = "參加"
                        itemView.joinstatus.setTextColor(Color.rgb(0, 122, 255))
                    }
                    MENU_ORDER_REPLY_STATUS_REJECT ->
                    {
                        itemView.joinstatus.text = "不參加"
                        itemView.joinstatus.setTextColor(Color.rgb(177, 0, 28))
                    }

                    MENU_ORDER_REPLY_STATUS_EXPIRE ->
                    {
                        itemView.acknowledge.text = "訂單逾期"
                        itemView.joinstatus.text =  "沒有參加"
                        itemView.acknowledge.setTextColor(Color.rgb(177, 0, 28))
                        itemView.joinstatus.setTextColor(Color.rgb(177, 0, 28))
                    }
                }
            }

            if(ItemsLV_Notify.read == "N")
            {
                itemView.notifylayout.setBackgroundResource(R.drawable.shape_rectangle_lightblue)
            }
            else if(ItemsLV_Notify.read == "Y")
            {
                itemView.notifylayout.setBackgroundResource(R.drawable.shape_rectangle_red)
            }
        }


        private fun getImageDrawable(imageName: String): Drawable {
            val id = context.resources.getIdentifier(imageName, "drawable",
                context.packageName)
            return context.resources.getDrawable(id)
        }
    }
}








