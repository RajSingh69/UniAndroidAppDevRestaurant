package com.example.mobileappdevcoursework

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FirstTabsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    init {
        Log.d("Adapter", "Adapter initialized")
    }

    override fun createFragment(index: Int): Fragment {
        return when (index) {
            0 -> FragmentOverview()
            1 -> FragmentAddReview()
            2 -> FragmentReadReview()
            3 -> FragmentMakeBooking()

            else -> FragmentOverview()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}


