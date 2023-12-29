package com.example.fruitapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room


@Database(entities = [dbFruit::class], version = 3, exportSchema = false)
abstract class FruitDb : RoomDatabase() {

    abstract fun fruitDao(): FruitDao

    companion object {
        @Volatile
        private var Instance: FruitDb? = null

        fun getDatabase(context: Context): FruitDb {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FruitDb::class.java, "fruit_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}