package com.curiousapps.mvi_architecture.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curiousapps.mvi_architecture.ui.main.MainFragment
import com.curiousapps.mvi_architecture.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMainFragment()
    }

    private fun showMainFragment(){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                MainFragment(), "MainFragment")
            .commit()
    }
}
