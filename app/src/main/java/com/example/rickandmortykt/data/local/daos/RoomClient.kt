package com.example.rickandmortykt.data.local.daos

import android.content.Context
import androidx.room.Room
import com.example.rickandmortykt.data.local.AppDatabase

class RoomClient {

    fun provideRoomDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()

    fun provideCharacterDao(appDatabase: AppDatabase) =
        appDatabase.characterDao()

    fun provideLocationDao(appDatabase: AppDatabase) =
        appDatabase.locationDao()

    fun provideEpisodeDao(appDatabase: AppDatabase) =
        appDatabase.episodeDao()


}