package com.example.epamtasks.adapter.holder

import com.example.epamtasks.adapter.listener.OnItemClickListener
import com.example.epamtasks.data.Contact
import com.example.epamtasks.databinding.ContactItemBinding

class ContactViewHolder(
    private val binding: ContactItemBinding,
    listener: OnItemClickListener?): BaseViewHolder<Contact
        ,ContactItemBinding>(binding, listener) {

    override fun onBind(t: Contact) {
        binding.apply {
            contactName.text = t.name
            contactPhone.text = t.phone[0]
        }
    }
}