package com.example.fruitapplication

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fruitapplication.data.database.FruitDao
import com.example.fruitapplication.data.database.FruitDb
import com.example.fruitapplication.data.database.asDbFruit
import com.example.fruitapplication.model.Fruit
import org.junit.Before
import org.junit.runner.RunWith
import android.content.Context
import androidx.room.Room
import com.example.fruitapplication.data.database.asDomainFruit
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import java.io.IOException
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals

/**
 * Instrumented test class for validating the functionality of [FruitDao] operations.
 * Uses AndroidX Test and JUnit4 for testing Room database operations like inserting and retrieving fruits.
 */
@RunWith(AndroidJUnit4::class)
class FruitDaoTest {
    private lateinit var fruitDao: FruitDao
    private lateinit var fruitDb: FruitDb

    // Two sample fruit objects for testing
    private var fruit1 = Fruit(
        "TestApple",
        -1,
        "TestFamily",
        "TestOrder",
        "TestFamily",
        null
    )
    private var fruit2 = Fruit(
        "TestBanana",
        -2,
        "TestFamily",
        "TestOrder",
        "TestFamily",
        null
    )

    /**
     * Suspended function to add a single [Fruit] into the [FruitDao] database.
     */
    private suspend fun addOneFruitToDb() {
        fruitDao.insert(fruit1.asDbFruit())
    }

    /**
     * Suspended function to add two [Fruit] objects into the [FruitDao] database.
     */
    private suspend fun addTwoFruitsToDb() {
        fruitDao.insert(fruit1.asDbFruit())
        fruitDao.insert(fruit2.asDbFruit())
    }

    /**
     * Setup method executed before each test method.
     * Creates an in-memory Room database instance.
     */
    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        fruitDb = Room.inMemoryDatabaseBuilder(context, FruitDb::class.java)
            .allowMainThreadQueries()
            .build()
        fruitDao = fruitDb.fruitDao()
    }

    /**
     * Teardown method executed after each test method.
     * Closes the in-memory Room database instance to clean up resources.
     */
    @After
    @Throws(IOException::class)
    fun closeDb() {
        fruitDb.close()
    }

    /**
     * Test method to verify the insertion of a single [Fruit] into the [FruitDao] database.
     * Uses [runBlocking] to execute suspending functions within the test method.
     * Asserts that the inserted fruit matches the retrieved fruit from the database.
     */
    @Test
    fun daoInert_insertFruitIntoDB() = runBlocking {
        addOneFruitToDb()
        val allItems = fruitDao.getFruits().first()
        assertEquals(allItems[0].asDomainFruit(), fruit1)
    }

    /**
     * Test method to verify retrieving all fruits from the [FruitDao] database.
     * Uses [runBlocking] to execute suspending functions within the test method.
     * Asserts that the retrieved fruits match the fruits inserted into the database.
     */
    @Test
    fun daoGetAllFruits_returnsAllFruitsFromDB() = runBlocking {
        addTwoFruitsToDb()
        val allItems = fruitDao.getFruits().first()
        assertEquals(allItems[0].asDomainFruit(), fruit1)
        assertEquals(allItems[1].asDomainFruit(), fruit2)
    }
}