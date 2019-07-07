package com.example.epamtasks.ui.detail


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.epamtasks.R
import com.example.epamtasks.data.Contact
import com.example.epamtasks.databinding.FragmentContactDetailBinding
import com.example.epamtasks.repository.ContactRepositoryImpl
import com.example.epamtasks.ui.base.BaseFragment


class ContactDetailFragment : BaseFragment<FragmentContactDetailBinding>(), ContactDetailContract.View {

    override fun initUI() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val presenter = ContactDetailPresenter(ContactRepositoryImpl(context!!)).apply {
            attach(this@ContactDetailFragment)
        }

        arguments?.getString(ID_KEY)?.let {args->
            presenter.getContact(args)
        }
    }

    override fun showContact(contact: Contact) {
        binding.nameContact.text = contact.name
    }

    override fun getLayout(): Int = R.layout.fragment_contact_detail

    companion object {
        private const val ID_KEY = "contact_id"

        fun newInstance(id: String) = run {
            val fragment = ContactDetailFragment()
            val args = Bundle()
            args.putString(ID_KEY, id)
            fragment.arguments = args
            fragment
        }
    }
}
