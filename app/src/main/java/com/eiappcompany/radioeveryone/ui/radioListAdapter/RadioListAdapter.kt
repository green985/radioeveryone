package com.eiappcompany.radioeveryone.ui.radioListAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eiappcompany.base.BaseHolder
import com.eiappcompany.base.crowler.crowlerModel.RadioDataModelCrowler
import com.eiappcompany.radioeveryone.BR
import com.eiappcompany.radioeveryone.databinding.RadioChannelItemLayoutBinding

/**
Created by EiAppCompany
15-10-2019
14:56
 **/


class RadioListAdapter(
        var radioList: List<RadioDataModelCrowler>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            //Radio LAyout create
            return RadioChannelItemHolder(RadioChannelItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            //TODO Should be a add layout or something else
            return RadioChannelItemHolder(RadioChannelItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }


    override fun getItemCount(): Int {
        return radioList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RadioChannelItemHolder) {
            holder.doBindings(radioList[position])
            holder.bind()
        }
    }


    inner class RadioChannelItemHolder constructor(viewDataBinding: RadioChannelItemLayoutBinding) :
            BaseHolder<RadioDataModelCrowler, RadioChannelItemLayoutBinding>(viewDataBinding) {

        override fun bindingVariable(): Int {
            return BR.item
        }

        override fun bind() {

        }
    }

}