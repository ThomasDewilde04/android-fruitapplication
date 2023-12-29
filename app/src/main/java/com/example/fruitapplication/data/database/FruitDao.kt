package com.example.fruitapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface [FruitDao] responsible for defining operations to access fruit-related data in the database.
 */
@Dao
interface FruitDao {

    /**
     * Inserts a single fruit item into the database. If the item already exists, it will be replaced.
     * @param item The fruit item to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbFruit)

    /**
     * Retrieves all fruits from the database and provides them as a [Flow].
     * @return A [Flow] emitting a list of [dbFruit] items ordered by name in ascending order.
     */
    @Query("SELECT * FROM fruit ORDER BY name ASC")
    fun getFruits(): Flow<List<dbFruit>>

    /**
     * Retrieves a specific fruit from the database by its ID and provides it as a [Flow].
     * @param id The ID of the fruit to retrieve.
     * @return A [Flow] emitting a single [dbFruit] item corresponding to the provided ID.
     */
    @Query("SELECT * FROM fruit WHERE id = :id")
    fun getFruit(id: Int): Flow<dbFruit>
}