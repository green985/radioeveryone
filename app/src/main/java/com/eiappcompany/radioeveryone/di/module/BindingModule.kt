package com.eiappcompany.radioeveryone.di.module

import com.eiappcompany.radioeveryone.binding.ImageBindingAdapter
import com.eiappcompany.radioeveryone.di.scope.BindingScope
import dagger.Module
import dagger.Provides

@Module
class BindingModule {

    @BindingScope
    @Provides
    internal fun provideImageBindingAdapter() = ImageBindingAdapter()
}
