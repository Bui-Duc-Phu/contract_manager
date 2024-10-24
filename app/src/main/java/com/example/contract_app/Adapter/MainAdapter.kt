package com.example.contract_app.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contract_app.databinding.ItemCustomBinding
import com.example.contract_app.database.User

class MainAdapter : PagingDataAdapter<User, MainAdapter.UserViewHolder>(UserComparator) {

    class UserViewHolder(private val binding: ItemCustomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User?) {
            user?.let {
                binding.textViewName.text = it.name
                binding.textViewEmail.text = it.email
                binding.textViewPhone.text = it.phone
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object {
        private val UserComparator = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
}
