package com.task.virginmoney.presentation.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.virginmoney.R
import com.task.virginmoney.databinding.ViewItemContactBinding
import com.task.virginmoney.extensions.concatenate
import com.task.virginmoney.presentation.contact.model.ContactItem

class ContactListAdapter(private val onItemClicked: (contactItem: ContactItem) -> Unit?) : ListAdapter<ContactItem, ContactListAdapter.ContactItemViewHolder>(
    ContactDiffUtilsCallbck()
){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactItemViewHolder {
        return ContactItemViewHolder(
            ViewItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
        val contactItem = getItem(position)
        holder.bindData(contactItem)
        holder.itemView.setOnClickListener {
            onItemClicked.invoke(contactItem)
        }
    }

    inner class ContactItemViewHolder(val binding: ViewItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(contactItem: ContactItem) {
            with(binding) {
                Glide.with(root.context)
                    .load(contactItem.avatar)
                    .placeholder(R.drawable.ic_person_place_holder)
                    .circleCrop()
                    .error(R.drawable.ic_person_place_holder)
                    .into(ivAvatar)
                tvPersonName.text = contactItem.firstName.concatenate(contactItem.lastName)
            }
        }
    }

    private class ContactDiffUtilsCallbck : DiffUtil.ItemCallback<ContactItem>() {
        override fun areItemsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
}