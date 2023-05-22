package com.thaer.core.di

import android.content.Context
import com.thaer.core.domain.prefs.Prefs
import dagger.Module
import dagger.Provides

@Module
class SharedPrefModule {

    @Provides
    fun provideSharedPref(
        context: Context
    ): Prefs = Prefs(context)

}
