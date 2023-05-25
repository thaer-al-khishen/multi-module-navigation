package com.thaer.core.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.thaer.core.data.room.AppDatabase
import com.thaer.core.data.room.UserDao
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()
}
