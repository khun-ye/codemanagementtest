package com.example.codemanagementtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codemanagementtest.data.local.WatchListDao
import com.example.codemanagementtest.data.local.entities.MovieEntity


@Database(entities = [MovieEntity::class], version = 2)
abstract class WatchListDatabase : RoomDatabase() {

    abstract fun watchListDao(): WatchListDao
}