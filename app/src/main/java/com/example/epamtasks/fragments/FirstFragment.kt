package com.example.epamtasks.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.epamtasks.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    private var callback: FragmentCallback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            callback = (context as FragmentCallback)
        }catch (e: ClassCastException){
            e.printStackTrace()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swapFragmentButton.setOnClickListener {
            callback?.onSwapFragment()
        }
        changeColorButton.setOnClickListener {
            callback?.onChangeColor()
        }
    }

    interface FragmentCallback{
        fun onSwapFragment()
        fun onChangeColor()
    }

    companion object {
        fun newInstance() = FirstFragment()
    }
}
