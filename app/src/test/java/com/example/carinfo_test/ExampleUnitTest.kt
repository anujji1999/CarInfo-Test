package com.example.carinfo_test

import com.example.carinfo_test.data.networking.CarInfoCommunicator
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

    }

    @Test
    fun testApi(){
        val response = runBlocking { CarInfoCommunicator.api.getCelebs() }
        assertNotNull(response.body())
    }
}