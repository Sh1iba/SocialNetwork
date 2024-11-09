package com.example.social_network

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.social_network.data.model.LoginResponse
import com.example.social_network.databinding.FragmentSearchBinding

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val users = mutableListOf<LoginResponse>()

    // Метод для обновления данных в адаптере
    fun submitList(newUsers: List<LoginResponse>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    // ViewHolder для одного элемента списка
    class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val textViewFullName: TextView = view.findViewById(R.id.textViewFullName)
        private val textViewId: TextView = view.findViewById(R.id.textViewId)
        private val textViewProfilePictureUrl: TextView = view.findViewById(R.id.textViewProfilePictureUrl)

        // Метод для привязки данных к View
        fun bind(user: LoginResponse) {
            textViewFullName.text = user.fullName
            textViewId.text = "ID: ${user.userId}"
            textViewProfilePictureUrl.text = user.profilePictureUrl
        }
    }
}



