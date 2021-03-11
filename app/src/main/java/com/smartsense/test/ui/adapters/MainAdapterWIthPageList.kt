package com.smartsense.test.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smartsense.test.R
import com.smartsense.test.database.entity.User
import com.smartsense.test.database.entity.UserDB
import com.smartsense.test.ui.activities.UserDetailActivity
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapterWIthPageList(
    val contex: Context
) : PagedListAdapter<UserDB, MainAdapterWIthPageList.UserViewHolder>(
    USER_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return UserViewHolder(view)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user!!)
    }
    inner  class UserViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        private val imageView = view.imageViewAvatar
        private val userName = view.textViewUserName
        private val userEmail = view.textViewUserEmail
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            rediretMainActivity()
        }
        fun bind(user: UserDB) {
            userName.text = user.first_name
            userEmail.text = user.email
            Glide.with(imageView.context)
                .load(user.avatar)
                //.placeholder(R.drawable.loading)
                .into(imageView);
        }



    }

    private fun rediretMainActivity(user:User) {
        val intent = Intent(context, UserDetailActivity::class.java)
        intent.putExtra("user", user)
        context.startActivity(intent)
        Bungee.slideLeft(context)
    }


    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<UserDB>() {
            override fun areItemsTheSame(oldItem: UserDB, newItem: UserDB): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: UserDB, newItem: UserDB): Boolean =
                newItem == oldItem
        }
    }
}