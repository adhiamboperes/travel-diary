package com.adhiambooyier.mytraveldiary.common.di.modules

import android.app.Application
import android.content.Context
import com.adhiambooyier.mytraveldiary.common.OpenForTesting
import com.adhiambooyier.mytraveldiary.common.DebugErrorLogger
import com.adhiambooyier.mytraveldiary.common.ErrorLogger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@OpenForTesting
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideErrorLogger(): ErrorLogger {
        return DebugErrorLogger()
    }
}
