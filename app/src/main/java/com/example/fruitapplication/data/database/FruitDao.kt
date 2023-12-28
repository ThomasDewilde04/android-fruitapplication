package com.example.fruitapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FruitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbFruit)

    @Query("SELECT * FROM fruit ORDER BY name DESC")
    fun getFruits(): Flow<List<dbFruit>>
}