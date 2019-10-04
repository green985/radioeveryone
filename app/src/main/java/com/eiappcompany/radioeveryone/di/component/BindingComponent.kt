package com.eiappcompany.radioeveryone.di.component

import androidx.databinding.DataBindingComponent
import com.eiappcompany.radioeveryone.binding.ImageBindingAdapter
import com.eiappcompany.radioeveryone.di.module.BindingModule
import com.eiappcompany.radioeveryone.di.scope.BindingScope
import dagger.Component

@BindingScope
@Component(dependencies = [AppComponent::class], modules = [BindingModule::class])
interface BindingComponent : DataBindingComponent {

    override fun getImageBindingAdapter(): ImageBindingAdapter
}
