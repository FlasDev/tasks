package com.example.epamtasks.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.epamtasks.R
import com.example.epamtasks.adapter.ContactListAdapter
import com.example.epamtasks.adapter.listener.OnItemClickListener
import com.example.epamtasks.data.Contact
import com.example.epamtasks.databinding.FragmentContactListBinding
import com.example.epamtasks.repository.ContactRepositoryImpl
import com.example.epamtasks.ui.base.BaseFragment
import com.example.epamtasks.ui.detail.ContactDetailFragment

class ContactListFragment : BaseFragment<FragmentContactListBinding>(), ContactListContract.View {

    private lateinit var contactAdapter: ContactListAdapter

    override fun initUI() {
        contactAdapter = ContactListAdapter()
        contactAdapter.listener = object : OnItemClickListener{
            override fun onClick(position: Int) {
                contactAdapter.listItem?.get(position)?.id?.let {
                    openDetailContact(it)
                }
            }
        }
        binding.contactList.apply {
            adapter = contactAdapter
            hasFixedSize()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val presenter = ContactListPresenter(ContactRepositoryImpl(context!!)).apply {
            attach(this@ContactListFragment)
        }
        presenter.getListContacts()

    }

    override fun showListContact(contactList: List<Contact>) {
        contactAdapter.listItem = contactList
    }

    override fun getLayout(): Int = R.layout.fragment_contact_list

    private fun openDetailContact(id: String){
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, ContactDetailFragment.newInstance(id))
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {
        fun newInstance(): Fragment = ContactListFragment()
    }
}
