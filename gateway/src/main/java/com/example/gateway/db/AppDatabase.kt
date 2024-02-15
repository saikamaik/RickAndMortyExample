package com.example.gateway.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.entity.CharacterModel
import com.example.domain.entity.Episode
import com.example.domain.entity.TypeConverterEpisode
import com.example.domain.entity.TypeConverterLocation
import com.example.domain.entity.TypeConverterOrigin

@Database(entities = [CharacterModel::class, Episode::class], version = 3)
@TypeConverters(
    TypeConverterOrigin::class,
    TypeConverterLocation::class,
    TypeConverterEpisode::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAppDao(): AppDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "character_n_episodes_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE!!

            }
        }
    }
}