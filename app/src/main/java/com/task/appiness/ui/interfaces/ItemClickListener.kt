package com.task.appiness.ui.interfaces

import android.view.View
import com.task.appiness.data.network.response.Hierarchy

interface ItemClickListener {
    fun onItemClick(view: View?, hierarchy: Hierarchy, type: String)
    fun getPosition(position: Int)
}