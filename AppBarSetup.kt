package com.example.mobileappdevcoursework


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AppBarSetup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_main_layout)

        val myToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.first_toolbar)
        setSupportActionBar(myToolbar)

        val tabLayout = findViewById<TabLayout>(R.id.first_tab_layout)
        val viewPager = findViewById<ViewPager2>(R.id.first_pager)
        Log.d("ViewPager2", "ViewPager2 found hiiiiiiiii")

        val tabTitles = resources.getStringArray(R.array.tabTitles)
        val pagerAdapter = FirstTabsPagerAdapter(this)

        viewPager.adapter = pagerAdapter

        Log.d("ViewPager2", "Adapter set hiiiiiiiii")

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = tabTitles[0]
                1 -> tab.text = tabTitles[1]
                2 -> tab.text = tabTitles[2]
                3 -> tab.text = tabTitles[3]
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.first_toolbar_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }
}