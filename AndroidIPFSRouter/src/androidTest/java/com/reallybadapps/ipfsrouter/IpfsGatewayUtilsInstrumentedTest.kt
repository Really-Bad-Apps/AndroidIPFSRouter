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
    fun testUrlTransformation() {
        val node = Node(host = "example.com", remote = true)
        val transformedUrl = transform("ipfs://QmPChd2hVbrJ6bfo3WBcTW4iZnpHm8TEzWkLHmLpXhF68A", node)
        assertEquals("https://example.com/ipfs/QmPChd2hVbrJ6bfo3WBcTW4iZnpHm8TEzWkLHmLpXhF68A", transformedUrl)
    }

    @Test
    fun testCidValidation() {
        assertTrue(isCID("QmPChd2hVbrJ6bfo3WBcTW4iZnpHm8TEzWkLHmLpXhF68A"))
        assertFalse(isCID("invalidCid"))
    }

    @Test
    fun testNodeCheck() = runBlocking {
        nodeCheck()
        assertTrue(nodeList.isNotEmpty())
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

    @Test
    fun testGetPreferredNode() {
        // Set up a preferred gateway
        setPreferredGateway(InstrumentationRegistry.getInstrumentation().targetContext, "auto")
        // Call getPreferredNode and assert the result
        val preferredNode = getPreferredNode(InstrumentationRegistry.getInstrumentation().targetContext)
        assertNotNull(preferredNode)
    }

    @Test
    fun testIsCID_withValidCID_returnsTrue() {
        val validCID = "QmPChd2hVbrJ6bfo3WBcTW4iZnpHm8TEzWkLHmLpXhF68A"
        assertTrue(isCID(validCID))
    }

    @Test
    fun testIsCID_withInvalidCID_returnsFalse() {
        val invalidCID = "NotACID"
        assertFalse(isCID(invalidCID))
    }

    @Test
    fun testIsBase32EncodedMultibase_withBase32CID_returnsTrue() {
        // Example CID in Base32 Encoding (you might need to replace this with an actual Base32 encoded CID)
        val base32CID = "bafybeiam2jkwnk42kyvgce33hekrmtr2bkmoozx5ttw66lwljizv2josam"
        assertTrue(isBase32EncodedMultibase(base32CID))
    }

    @Test
    fun testIsBase32EncodedMultibase_withNonBase32CID_returnsFalse() {
        val nonBase32CID = "QmPChd2hVbrJ6bfo3WBcTW4iZnpHm8TEzWkLHmLpXhF68A" // Regular CID
        assertFalse(isBase32EncodedMultibase(nonBase32CID))
    }
}