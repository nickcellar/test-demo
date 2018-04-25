package com.nicholasworkshop.tinklabstest.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nicholasworkshop.tinklabstest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarView)
        containerView.adapter = MainFragmentPagerAdapter(this, supportFragmentManager)
        tabsView.setupWithViewPager(containerView)
    }
}
