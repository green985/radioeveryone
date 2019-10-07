package com.eiappcompany.radioeveryone.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eiappcompany.radioeveryone.di.vmDi.RadioEveryoneModelFactory
import com.eiappcompany.radioeveryone.di.vmDi.ViewModelKey
import com.eiappcompany.radioeveryone.ui.main.MainVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
Created by EiAppCompany
04-10-2019
16:45
 **/

@Module
abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindsRadioEveryoneModelFactory(radioEveryoneModelFactory: RadioEveryoneModelFactory):
            ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MainVM::class)
    internal abstract fun bindsMainViewModel(viewModel: MainVM): ViewModel

}