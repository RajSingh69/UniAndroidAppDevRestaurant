package com.example.mobileappdevcoursework

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * This class sets the right page to the guest fragments
 * @author Rajan Singh Bhamra - 2034215
 * @version V17
 */

class GuestTabsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    init {
        Log.d("Guest Adapter", "Guest Adapter Initialized")
    }

    override fun createFragment(index: Int): Fragment {
        return when (index) {
            0 -> GuestFragmentOverview()
            1 -> GuestFragmentReadReview()
            else -> GuestFragmentOverview()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}


