package com.example.epamtasks.adapter

import android.view.ViewGroup
import com.example.epamtasks.R
import com.example.epamtasks.adapter.holder.BaseViewHolder
import com.example.epamtasks.adapter.holder.ContactViewHolder
import com.example.epamtasks.data.Contact
import com.example.epamtasks.databinding.ContactItemBinding

class ContactListAdapter : BaseRecycleViewAdapter<Contact,
        ContactViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = BaseViewHolder.from<ContactItemBinding>(parent, R.layout.contact_item)
        return ContactViewHolder(binding, listener)
    }

}