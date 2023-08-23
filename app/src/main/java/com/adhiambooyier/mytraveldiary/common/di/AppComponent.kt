package com.adhiambooyier.mytraveldiary.common.di

import android.app.Application
import com.adhiambooyier.mytraveldiary.common.MyTravelDiaryApp
import com.adhiambooyier.mytraveldiary.common.di.modules.ActivityModule
import com.adhiambooyier.mytraveldiary.common.di.modules.AppModule
import com.adhiambooyier.mytraveldiary.common.di.modules.FragmentModule
import com.adhiambooyier.mytraveldiary.common.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        AndroidInjectionModule::class
    ]
)

interface AppComponent : AndroidInjector<MyTravelDiaryApp> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }
}
