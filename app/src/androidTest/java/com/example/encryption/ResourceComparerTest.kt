package com.example.encryption

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {
    private lateinit var resourceComparer: ResourceComparer

    @Before // WILL EXECUTE THIS METHOD BEFORE EACH AND EVERY TEST CASE
    fun setup(){
        resourceComparer = ResourceComparer()
    }


    @After
    fun tearDown(){
        //FOR DB
    }
    @Test
    fun stringResourceSameAsGivenString_returnsTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context,R.string.app_name, "Encryption")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceSameAsGivenString_returnsFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context,R.string.app_name, "enc")
        assertThat(result).isFalse()
    }
}