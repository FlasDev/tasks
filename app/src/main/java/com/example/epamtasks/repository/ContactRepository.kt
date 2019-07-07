package com.example.epamtasks.repository

import com.example.epamtasks.data.Contact

interface ContactRepository {
    fun getContactList(): List<Contact>
    fun getContactById(id: String): Contact?
}