package com.example.epamtasks.ui.list

import com.example.epamtasks.data.Contact

class ContactListContract {
    interface View{
        fun showListContact(contactList: List<Contact>)
    }

    interface Presenter<T: View>{
        fun attach(view: T)
        fun getListContacts()
    }
}