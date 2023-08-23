package com.adhiambooyier.mytraveldiary.common.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adhiambooyier.mytraveldiary.common.di.ViewModelFactory
import com.adhiambo.movieguide.common.di.ViewModelKey
import com.adhiambooyier.mytraveldiary.presentation.EntriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(EntriesViewModel::class)
   internal abstract fun provideMoviesViewModel(moviesViewModel: EntriesViewModel): ViewModel
}