package com.example.epamtasks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var texts: Array<String>

    private var button: Button? = null
    private var textView: TextView? = null
    private var linearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        texts = resources.getStringArray(R.array.button_labels)

        linearLayout = LinearLayout(this).create()


        textView = TextView(this)
            .create()

        button = Button(this)
            .create()

        button?.setOnClickListener {
            textView?.text = texts[abs(Random.nextInt() % texts.size)]
        }

        linearLayout?.apply {
            addView(textView)
            addView(button)
        }

        setContentView(linearLayout)
    }

    private fun Button.create(): Button {
        val buttonLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        text = getString(R.string.button_label)
        layoutParams = buttonLayoutParams

        return this
    }

    private fun TextView.create(): TextView {
        text = getString(R.string.main_text)
        layoutParams = createParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return this
    }

    private fun LinearLayout.create(): LinearLayout {
        val linearLayoutParams = createParams<LinearLayout.LayoutParams>(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        layoutParams = linearLayoutParams
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER

        return this
    }

    private inline fun <T : ViewGroup.LayoutParams> createParams(height: Int, width: Int): T {
        val params = ViewGroup.LayoutParams(height, width)
        @Suppress("UNCHECKED_CAST")
        return params as T
    }

    companion object {
        fun newIntent(packageContext: Context) =
            Intent(packageContext, MainActivity::class.java)
    }
}
