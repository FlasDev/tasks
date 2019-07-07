package com.example.epamtasks.ui.detail

import com.example.epamtasks.data.Contact

class ContactDetailContract {
    interface View {
        fun showContact(contact: Contact)
    }

    interface Presenter<T : View> {
        fun getContact(id: String)
        fun attach(view: T)
    }
}