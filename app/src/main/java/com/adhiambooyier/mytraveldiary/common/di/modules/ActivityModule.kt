package com.adhiambooyier.mytraveldiary.common.di.modules

import com.adhiambooyier.mytraveldiary.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun provideMainActivity(): MainActivity
}