package com.example.epamtasks

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.epamtasks.fragments.FirstFragment
import com.example.epamtasks.fragments.SecondFragment
import com.example.epamtasks.fragments.ThirdFragment
import com.example.epamtasks.fragments.base.ColorFragment
import kotlin.random.Random

class MainActivity : AppCompatActivity(), FirstFragment.FragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(FirstFragment.newInstance(), R.id.firstContainer)
        replaceFragment(SecondFragment.newInstance(), R.id.secondContainer)
        replaceFragment(ThirdFragment.newInstance(), R.id.thirdContainer)
    }

    private fun replaceFragment(fragment: Fragment, res: Int) {
        supportFragmentManager.transaction { replace(res, fragment) }
    }

    private fun removeFragment(fragment: Fragment) {
        supportFragmentManager.transaction { remove(fragment) }
    }

    private inline fun FragmentManager.transaction(transaction: FragmentTransaction.() -> Unit) {
        beginTransaction().apply {
            transaction()
            commit()
        }
    }


    override fun onSwapFragment() {
        val firstFragment = supportFragmentManager.findFragmentById(R.id.secondContainer)!!
        val secondFragment = supportFragmentManager.findFragmentById(R.id.thirdContainer)!!

        val saveFirstState = supportFragmentManager.saveFragmentInstanceState(firstFragment)
        val saveSecondState = supportFragmentManager.saveFragmentInstanceState(secondFragment)

        val secondFragmentInstance = SecondFragment.newInstance()
        val thirdFragmentInstance = ThirdFragment.newInstance()

        if (firstFragment is SecondFragment){
            secondFragmentInstance.setInitialSavedState(saveFirstState)
            thirdFragmentInstance.setInitialSavedState(saveSecondState)
            replaceFragment(secondFragmentInstance, R.id.thirdContainer)
            replaceFragment(thirdFragmentInstance, R.id.secondContainer)
        }else{
            secondFragmentInstance.setInitialSavedState(saveSecondState)
            thirdFragmentInstance.setInitialSavedState(saveFirstState)
            replaceFragment(secondFragmentInstance, R.id.secondContainer)
            replaceFragment(thirdFragmentInstance, R.id.thirdContainer)
        }
    }

    override fun onChangeColor() {
        val firstFragment = supportFragmentManager.findFragmentById(R.id.secondContainer) as ColorFragment
        firstFragment.background = Color.argb(
            255, Random.nextInt(256)
            , Random.nextInt(256),
            Random.nextInt(256)
        )

        val secondFragment = supportFragmentManager.findFragmentById(R.id.thirdContainer) as ColorFragment
        secondFragment.background = Color.argb(
            255, Random.nextInt(256)
            , Random.nextInt(256),
            Random.nextInt(256)
        )
    }
}
