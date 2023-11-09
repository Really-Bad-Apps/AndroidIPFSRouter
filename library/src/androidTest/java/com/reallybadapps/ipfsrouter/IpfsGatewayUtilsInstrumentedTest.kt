package com.reallybadapps.ipfsrouter

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class IpfsGatewayUtilsInstrumentedTest {

    @Before
    fun setUp() = runBlocking {
        // runBlocking will block the thread until the coroutine inside it is completed
        nodeCheck() // Assuming nodecheck is a suspend function
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.reallybadapps.ipfsrouter.test", appContext.packageName)
    }

    @Test
    fun testGetFastestNode() {
        val fastestNode = getFastestNode()
        assertNotNull( fastestNode)
    }
}