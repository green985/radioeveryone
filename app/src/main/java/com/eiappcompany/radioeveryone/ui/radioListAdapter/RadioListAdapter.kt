package com.eiappcompany.radioeveryone.ui.radioListAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eiappcompany.base.BaseHolder
import com.eiappcompany.base.crowler.crowlerModel.RadioDataModelCrowler
import com.eiappcompany.base.util.interfaces.RecyclerViewItemClickListener
import com.eiappcompany.radioeveryone.BR
import com.eiappcompany.radioeveryone.databinding.RadioChannelItemLayoutBinding
import java.lang.ref.WeakReference

/**
Created by EiAppCompany
15-10-2019
14:56
 **/


class RadioListAdapter(
    var radioList: List<RadioDataModelCrowler>,
    var listItemClick: RecyclerViewItemClickListener<RadioDataModelCrowler>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var addView: WeakReference<View>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //Radio LAyout create
        return RadioChannelItemHolder(
            RadioChannelItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
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
            getRowBinding()?.getRoot()?.setOnClickListener {
                getRowItem()?.let { rowItem ->
                    listItemClick.listItemClick(
                        rowItem,
                        it,
                        adapterPosition
                    )
                }
            }
            /*
            if (adapterPosition == 0) {
                getRowBinding()?.let {
                    it.addRoot.visibility = View.VISIBLE
                    if (addView.get() != null) {
                        it.addRoot.removeAllViews()
                        it.addRoot.addView(addView.get())
                    }
                }
            } else {
                getRowBinding()?.let {
                    it.addRoot.visibility = View.GONE
                }
            }

             */
        }
    }
}

/*
try {
                if (advertisementModel.getListItem().getAdsenseCode() == null) {
                    if (adView == null) {
                        adView = new AdView(itemView.getContext());
                        adView.setAdUnitId(advertisementModel.getListItem().getAdsenseCode());
                        adView.setAdSize(AdSize.SMART_BANNER);
                        AdRequest adRequest = new AdRequest.Builder().build();
                        adView.loadAd(adRequest);
                        adView.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                // Code to be executed when an ad finishes loading.
                                bannerLoad = true;
                            }

                        });
                    }
                }
            } catch (Exception e) {

            }
 */