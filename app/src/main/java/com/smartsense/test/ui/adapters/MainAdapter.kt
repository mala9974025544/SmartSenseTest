package com.smartsense.test.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smartsense.test.R
import com.smartsense.test.database.entity.UserDB
import kotlinx.android.synthetic.main.item_layout.view.*


class MainAdapter(
    private var items: List<UserDB>?

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items?.get(position)?.let { (holder as UserViewHolder).bind(it) }
    }

    override fun getItemCount(): Int {
        return if (items != null) {
            items!!.size
        } else 0
    }

    fun replaceData(items: List<UserDB>?) {
        this.items = items
        notifyDataSetChanged()
    }
    inner  class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val imageView = view.imageViewAvatar
        private val userName = view.textViewUserName
        private val userEmail = view.textViewUserEmail
        fun bind(user: UserDB) {
            userName.text = user.first_name
            userEmail.text = user.email
            Glide.with(imageView.context)
                .load(user.avatar)
                //.placeholder(R.drawable.loading)
                .into(imageView);
        }



    }


}