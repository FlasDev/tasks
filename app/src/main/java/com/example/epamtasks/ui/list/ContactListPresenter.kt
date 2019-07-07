package com.example.epamtasks.ui.list

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.epamtasks.repository.ContactRepository

class ContactListPresenter(
    private val contactRepository: ContactRepository
) :
    ContactListContract.Presenter<ContactListFragment> {

    private lateinit var view: ContactListContract.View

    override fun attach(view: ContactListFragment) {
        this.view = view
    }

    override fun getListContacts() {
        view.showListContact(contactRepository.getContactList())
    }

}