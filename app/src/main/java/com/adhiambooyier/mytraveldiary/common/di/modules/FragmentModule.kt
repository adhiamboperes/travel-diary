package com.adhiambooyier.mytraveldiary.common.di.modules

import com.adhiambooyier.mytraveldiary.presentation.EntryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeEntryListFragment(): EntryListFragment
}
