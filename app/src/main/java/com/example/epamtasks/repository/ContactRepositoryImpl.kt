package com.example.epamtasks.repository

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.example.epamtasks.data.Contact

class ContactRepositoryImpl(context: Context) : ContactRepository {

    private val resolver = context.contentResolver

    override fun getContactList(): List<Contact> {
        val contacts = ArrayList<Contact>()

        val project = arrayOf(
            ContactsContract.Data.CONTACT_ID, ContactsContract.Data.DISPLAY_NAME,
            ContactsContract.Data.DATA1
        )

        val selection = "${ContactsContract.Data.MIMETYPE} = ?"

        val selectionArgs = arrayOf(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)

        val cursorProvider = resolver.query(
            ContactsContract.Data.CONTENT_URI,
            project,
            selection,
            selectionArgs,
            null
        )

        cursorProvider?.use { cursor ->
            cursor.takeIf {
                it.count > 0
            }?.apply {
                moveToFirst()
                while (moveToNext()) {
                    val id = getString(0)
                    val name = getString(1)
                    val phone = getString(2)
                    contacts.add(Contact(id, name, listOf(phone)))
                }
            }
        }

        return contacts
    }

    override fun getContactById(id: String): Contact {
        val contact = Contact()

        val project = arrayOf(
            ContactsContract.Data.CONTACT_ID, ContactsContract.Data.DISPLAY_NAME
        )

        val selection = "${ContactsContract.Data.CONTACT_ID} = ?"
        val selectionArgs = arrayOf(id)

        val cursorProvider = resolver.query(
            ContactsContract.Data.CONTENT_URI,
            project,
            selection,
            selectionArgs,
            null
        )

        cursorProvider?.use {cursor->
            cursor.moveToFirst()
            val contactId = cursor.getString(0)
            contact.id = contactId
            contact.name = cursor.getString(1)
            contact.phone = getPhones(contactId)
            contact.email = getEmails(contactId)
        }

        Log.d("myLogs", "${contact.phone.size}, ${contact.email.size}")
        return contact
    }

    private fun getPhones(id: String): List<String>{
        val phones = ArrayList<String>()

        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DATA1)
        val selection = "${ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID} = ?"
        val selectionArgs = arrayOf(id)

        val emailCursor = resolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        emailCursor?.use {cursor->
            cursor.takeIf {it.count > 0}?.apply {
                moveToFirst()
                while (moveToNext()){
                    phones += cursor.getString(0)
                }
            }
        }

        return phones
    }

    private fun getEmails(id: String): List<String>{
        val emails = ArrayList<String>()

        val projection = arrayOf(ContactsContract.CommonDataKinds.Email.DATA1)
        val selection = "${ContactsContract.CommonDataKinds.Email.CONTACT_ID} = ?"
        val selectionArgs = arrayOf(id)

        val emailCursor = resolver.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        emailCursor?.use {cursor->
            cursor.takeIf {it.count > 0}?.apply {
                moveToFirst()
                while (moveToNext()){
                    emails += cursor.getString(0)
                }
            }
        }

        return emails
    }
}