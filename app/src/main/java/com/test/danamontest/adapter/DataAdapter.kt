package com.test.danamontest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.danamontest.R
import com.test.danamontest.model.DataModel
import com.test.danamontest.util.Constant
import kotlinx.android.synthetic.main.item_data_from_user.view.*

class DataAdapter (
    private var listData: MutableList<DataModel?>,
    private val context: Context?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constant.VIEW_TYPE_ITEM) {
            val v =
                LayoutInflater.from(context).inflate(R.layout.item_data_from_user, parent, false)
            Item(v)
        } else {
            val v =
                LayoutInflater.from(context).inflate(R.layout.progress_loading, parent, false)
            LoadingHolder(v)
        }
    }


    fun addData(listData: MutableList<DataModel?>) {
        listData.let {
            this.listData.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listData.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        context?.let {
            listData[position]?.let { it1 ->
                (holder as Item).bindItem(
                    it1, context
                )
            }
        }
    }

    class Item(itemview: View) : RecyclerView.ViewHolder(itemview) {
        @SuppressLint("CheckResult", "SetTextI18n")
        fun bindItem(
            data: DataModel,
            context: Context?
        ) {
            itemView.title.text = data.title

            val requestOption = RequestOptions()
            requestOption.placeholder(R.drawable.ic_launcher_background)
            requestOption.error(R.drawable.ic_launcher_background)
            Glide.with(itemView.profile_image).setDefaultRequestOptions(requestOption)
                .load(data.thumbnailUrl).into(itemView.profile_image)

        }
    }

    class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}