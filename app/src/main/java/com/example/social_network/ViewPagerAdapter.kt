package com.example.social_network

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.social_network.Fragments.ChatsFragment
import com.example.social_network.Fragments.ProfileFragment
import com.example.social_network.Fragments.SettingsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> ChatsFragment()
            2 -> SettingsFragment()
            else -> ProfileFragment()
        }
    }
}