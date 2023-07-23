package com.example.codemanagementtest.di

import android.content.Context
import androidx.room.Room
import com.example.codemanagementtest.data.local.WatchListDao
import com.example.codemanagementtest.data.local.WatchListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WatchListDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WatchListDatabase::class.java,
            "watch_list_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWatchListDao(
        db: WatchListDatabase
    ): WatchListDao {
        return db.watchListDao()
    }
}