package com.example.epamtasks.ui.detail

import com.example.epamtasks.repository.ContactRepository

class ContactDetailPresenter(
    private val contactRepository: ContactRepository
)
    : ContactDetailContract.Presenter<ContactDetailContract.View> {

    private lateinit var view: ContactDetailContract.View

    override fun getContact(id: String) {
        contactRepository.getContactById(id)?.let { view.showContact(it) }
    }

    override fun attach(view: ContactDetailContract.View) {
        this.view = view
    }
}