package com.example.mobileappdevcoursework

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * This class is the setup class for the guest app bar
 * @author Rajan Singh Bhamra - 2034215
 * @version V17
 */

class GuestAppBarSetup : AppCompatActivity(){

    /**
     * When this class is created, it sets the layout to the xml file required.
     * It gets the layout and the pager. It calls the tab heading titles from the
     *      string file.
     * The right title is then set on the corresponding desired page.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guest_app_bar_layout)

        val guestToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.guest_toolbar)
        setSupportActionBar(guestToolbar)

        val guestTabLayout = findViewById<TabLayout>(R.id.guest_tab_layout)
        val guestViewPager = findViewById<ViewPager2>(R.id.guest_pager)

        val tabTitles = resources.getStringArray(R.array.guest_TabTitles)
        val guestPagerAdapter = GuestTabsPagerAdapter(this)

        guestViewPager.adapter = guestPagerAdapter

        TabLayoutMediator(guestTabLayout, guestViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = tabTitles[0]
                1 -> tab.text = tabTitles[1]
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.guest_toolbar_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }
}