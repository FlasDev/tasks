package com.example.epamtasks.adapter.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.epamtasks.adapter.listener.OnItemClickListener

abstract class BaseViewHolder<in T, V : ViewDataBinding>(
    binding: V,
    listener: OnItemClickListener? = null
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            listener?.onClick(adapterPosition)
        }
    }

    abstract fun onBind(t: T)

    companion object {
        fun <T : ViewDataBinding> from(parent: ViewGroup, res: Int): T {
            val inflater = LayoutInflater.from(parent.context)
            return DataBindingUtil.inflate(
                inflater, res, parent, false
            )
        }
    }
}