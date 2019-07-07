package com.example.epamtasks.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.epamtasks.adapter.holder.BaseViewHolder
import com.example.epamtasks.adapter.listener.OnItemClickListener
import com.example.epamtasks.databinding.ContactItemBinding

abstract class BaseRecycleViewAdapter
<T, VH : BaseViewHolder<T, ContactItemBinding>>
    : RecyclerView.Adapter<VH>() {

    var listener: OnItemClickListener? = null

    var listItem: List<T>? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        listItem?.get(position)?.let {
            holder.onBind(it)
        }
    }

    override fun getItemCount(): Int = listItem?.size ?: 0
}