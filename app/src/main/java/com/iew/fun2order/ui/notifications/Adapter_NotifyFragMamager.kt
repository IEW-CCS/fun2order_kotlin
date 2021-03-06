package com.iew.fun2order.ui.notifications

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.iew.fun2order.R

class Adapter_NotifyFragMamager(fragmentManager:FragmentManager, var context: Context): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> RootFragmentInvitation()
            1 -> RootFragmentShipping()
            else -> RootFragmentOthers()
        }

    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0 -> context.getString(R.string.NotifyInvite)
            1 -> context.getString(R.string.NotifyShipping)
            else -> context.getString(R.string.NotifyOthers)

        }
    }

}