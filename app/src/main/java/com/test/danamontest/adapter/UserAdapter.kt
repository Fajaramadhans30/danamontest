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
import com.test.danamontest.realm.User
import com.test.danamontest.util.Constant
import io.realm.RealmResults
import kotlinx.android.synthetic.main.item_data_from_user.view.*

class UserAdapter(
    private val items: RealmResults<User>,
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

    override fun getItemCount(): Int {
        return items.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        context?.let {
            items[position]?.let { it1 ->
                (holder as Item).bindItem(
                    it1, context
                )
            }
        }
    }

    class Item(itemview: View) : RecyclerView.ViewHolder(itemview) {
        @SuppressLint("CheckResult", "SetTextI18n")
        fun bindItem(
            data: User,
            context: Context?
        ) {
            itemView.tvPhoneNumber.visibility = View.VISIBLE
            itemView.tvAddress.visibility = View.VISIBLE

            itemView.title.text = data.fullname
            itemView.tvPhoneNumber.text = data.phoneNumber
            itemView.tvAddress.text = data.address


            val requestOption = RequestOptions()
            requestOption.placeholder(R.drawable.ic_launcher_background)
            requestOption.error(R.drawable.ic_launcher_background)

        }
    }

    class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}