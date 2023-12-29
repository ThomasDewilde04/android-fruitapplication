package com.example.fruitapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

/**
 * Room database class [FruitDb] for managing fruit-related data within the Android application.
 * Extends [RoomDatabase].
 */
@Database(entities = [dbFruit::class], version = 3, exportSchema = false)
abstract class FruitDb : RoomDatabase() {

    /**
     * Abstract function to provide access to the DAO (Data Access Object) for fruit-related operations.
     * @return [FruitDao] instance to interact with the fruit-related data.
     */
    abstract fun fruitDao(): FruitDao

    companion object {
        @Volatile
        private var Instance: FruitDb? = null

        /**
         * Retrieves the database instance using Singleton pattern.
         * @param context The application context.
         * @return [FruitDb] instance.
         */
        fun getDatabase(context: Context): FruitDb {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FruitDb::class.java, "fruit_database") // Database name
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}