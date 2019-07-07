package com.example.epamtasks.fragments


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.epamtasks.R
import com.example.epamtasks.fragments.base.ColorFragment
import kotlinx.android.synthetic.main.fragment_second.*


class SecondFragment : ColorFragment() {

    override var background: Int = Color.WHITE
        set(value) {
            field = value
            textView.setBackgroundColor(value)
        }



    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COLOR_KEY, background)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        background = savedInstanceState?.getInt(COLOR_KEY) ?: Color.WHITE
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private const val COLOR_KEY = "COLOR"
        fun newInstance() = SecondFragment()
    }

}
