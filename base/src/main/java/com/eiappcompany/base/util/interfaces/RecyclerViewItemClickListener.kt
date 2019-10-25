package com.eiappcompany.base.util.interfaces

import android.view.View

/**
Created by EiAppCompany
25-10-2019
15:46
 **/
interface RecyclerViewItemClickListener<T> {

    fun listItemClick(listItem: T, it: View, adapterPosition: Int)
}