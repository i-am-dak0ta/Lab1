package com.dak0ta.learnity.feature.profile.presentation.di

import androidx.lifecycle.ViewModel
import com.dak0ta.learnity.core.di.ViewModelKey
import com.dak0ta.learnity.feature.profile.presentation.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfilePresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}
