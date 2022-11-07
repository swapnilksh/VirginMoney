package com.task.virginmoney.presentation.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.virginmoney.R
import com.task.virginmoney.common.getTextColorForRoomAvailability
import com.task.virginmoney.common.getTextForRoomAvailability
import com.task.virginmoney.databinding.ViewItemRoomBinding
import com.task.virginmoney.extensions.concatenate
import com.task.virginmoney.presentation.room.model.RoomItem

class RoomListAdapter : ListAdapter<RoomItem,RoomListAdapter.RoomItemViewHolder>(RoomDiffUtilsCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoomItemViewHolder {
        return RoomItemViewHolder(
            ViewItemRoomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RoomItemViewHolder, position: Int) {
        val roomItem = getItem(position)
        holder.bindData(roomItem)
    }

    inner class RoomItemViewHolder(private val binding: ViewItemRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(roomItem: RoomItem) {
            with(binding) {
                tvRoom.text =
                    this.root.context.getString(R.string.text_room).concatenate(roomItem.id)
                tvOccupancy.text = roomItem.maxOccupancy
                tvAvailability.text =
                    getTextForRoomAvailability(roomItem.isOccupied, binding.root.context)
                tvAvailability.setTextColor(
                    ContextCompat.getColor(
                        this.root.context,
                        getTextColorForRoomAvailability(roomItem.isOccupied)
                    )
                )
            }
        }
    }

    class RoomDiffUtilsCallback : DiffUtil.ItemCallback<RoomItem>() {
        override fun areItemsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: RoomItem, newItem: RoomItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
