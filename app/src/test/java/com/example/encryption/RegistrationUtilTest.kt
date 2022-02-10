package com.example.encryption


import com.example.encryption.callbacks.CallBack
import com.example.encryption.utility.Encryption
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * inside this class we can define our test cases for the registration util object
 */
class RegistrationUtilTest{


    @Test
    fun `empty username returns false` (){
         val result = RegistrationUtil.validateRegistrationInput(
             "",
             "123",
             "123"
         )
        //IMPORT IT FROM TRUTH LIBRARY
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true` (){
        val result = RegistrationUtil.validateRegistrationInput(
            "Awais",
            "123",
            "123"
        )
        //IMPORT IT FROM TRUTH LIBRARY
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns true` (){
        val result = RegistrationUtil.validateRegistrationInput(
            "Khan",
            "123",
            "123"
        )
        //IMPORT IT FROM TRUTH LIBRARY
        assertThat(result).isFalse()
    }


    @Test
    fun `validate encryption returns string`(){
        val result = RegistrationUtil.validate(16,"Aqib Javed", "ABCDEFGHIJKLMNOT")
        assertThat(result).isEqualTo("/Q8fgbxPIABOtY0zcMA0fg==")

    }


    //SIMILARLY WE CAN WRITE THE REMAINING THREE CASES
}