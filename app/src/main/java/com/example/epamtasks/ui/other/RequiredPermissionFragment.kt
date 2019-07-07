package com.example.epamtasks.ui.other


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.epamtasks.R


class RequiredPermissionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_required_permission, container, false)
    }

    companion object {
        fun newInstance() = RequiredPermissionFragment()
    }

}
