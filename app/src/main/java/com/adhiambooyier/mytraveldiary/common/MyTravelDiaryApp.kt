package com.adhiambooyier.mytraveldiary.common

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.adhiambooyier.mytraveldiary.common.room.DiaryDatabase
import com.adhiambooyier.mytraveldiary.common.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class MyTravelDiaryApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var errorLogger: ErrorLogger

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        database = DiaryDatabase.invoke(this)
        setUpDagger()
        RxJavaPlugins.setErrorHandler { e ->
            var error = e
            // RxJava wraps such errors with the UndeliverableException
            // but we want to process the wrapped exception
            if (e is UndeliverableException) {
                error = e.orCause()
            }

            errorLogger.logException(
                throwable = error,
                message = "Exception handled by RxJavaPlugins.setErrorHandler",
                severity = ErrorLogger.Severity.WARNING
            )
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun setUpDagger() {
        DaggerAppComponent
            .factory()
            .create(this)
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    companion object {
        lateinit var instance: MyTravelDiaryApp
        lateinit var database: DiaryDatabase
    }
}
