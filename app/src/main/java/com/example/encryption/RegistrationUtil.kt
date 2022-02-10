package com.example.encryption

import com.example.encryption.callbacks.CallBack
import com.example.encryption.utility.Encryption

object RegistrationUtil {


    private val existingUsers = listOf("Khan", "Shan")

    /**
     * the input is not valid if..
     * ... the username or password is empty
     * ... the username is already taken
     * ... the confirmed is not the same as the real password
     * ... the password contains less than 2 digits
     */
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmedPassword: String
    ) : Boolean{
        if(username.isEmpty() || password.isEmpty()){
            return false
        }

        if(username in existingUsers){
            return false
        }

        if(password != confirmedPassword){
            return false
        }

        if(password.count { it.isDigit() } < 2){
            return false
        }

        return true
    }


    fun validate(keyLength:Int,data:String,chum:String):String{
        var mResult :String = ""
        Encryption.encrypt(keyLength,data,chum,object : CallBack {
            override fun callback(result: Any) {
                mResult =  result.toString()
            }
        })
        return mResult
    }
}