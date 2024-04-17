package com.domonz.dietsnap.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.domonz.dietsnap.Fragments.HomeFragment
import com.domonz.dietsnap.Fragments.OtherFragments

class MainPagerAdapter(fm: FragmentManager, var tabCount:Int) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> OtherFragments()
            2 -> OtherFragments()
            3 -> OtherFragments()
            4 -> OtherFragments()
            else -> throw IllegalStateException("Invalid position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }
}