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
import spencerstudios.com.bungeelib.Bungee

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
            getItem(adapterPosition)?.let { rediretMainActivity(it) }
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

    private fun rediretMainActivity(user: UserDB) {
        val user= user.first_name?.let { user.avatar?.let { it1 ->
            user.email?.let { it2 ->
                User(user.id,
                    it1, it2, it)
            }
        } }

        val intent = Intent(contex, UserDetailActivity::class.java)
        intent.putExtra("user", user);
        contex.startActivity(intent)
        Bungee.slideLeft(contex)
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