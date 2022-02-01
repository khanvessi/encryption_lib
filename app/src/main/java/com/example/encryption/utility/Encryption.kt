package com.example.encryption.utility

import android.util.Base64
import android.util.Log
import com.example.encryption.callbacks.CallBack
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Encryption {

    private val characterEncoding = "UTF-8"
    private val cipherTransformation = "AES/CBC/PKCS5Padding"
    private val aesEncryptionAlgorithm = "AES"


    fun encrypt(keySize: Int, plainText: String, chum: String,callBack:CallBack) {
        try {
            val charset: Charset = Charsets.UTF_8
            val plainTextbytes = plainText.toByteArray(charset)
            val keyBytes = getKeyBytes(keySize, chum)
            val iv = getKeyBytes(16, chum)
            val result = Base64.encodeToString(encrypt(plainTextbytes, keyBytes!!, iv!!), Base64.NO_WRAP)
            callBack.callback(result);
//            return Base64.encodeToString(encrypt(plainTextbytes, keyBytes!!, iv!!), Base64.NO_WRAP)
        } catch (e: Exception) {
            callBack.callback(e.toString());
//            return e.toString()
        }
    }

    fun decrypt(keySize: Int, chiperText: String, chum: String,callBack:CallBack) {
        try {
            val plainTextbytes = Base64.decode(chiperText, Base64.NO_WRAP)
            val keyBytes = getKeyBytes(keySize, chum)
            val iv = getKeyBytes(16, chum)
            val result = String(decrypt(plainTextbytes, keyBytes!!, iv!!)!!)
            callBack.callback(result)
//            return String(decrypt(plainTextbytes, keyBytes!!, iv!!)!!)
        } catch (e: Exception) {
            callBack.callback(e.toString())
//            return e.toString()
        }
    }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun decrypt(
        plainText: ByteArray,
        key: ByteArray,
        initialVector: ByteArray
    ): ByteArray? {
        var plainText: ByteArray? = plainText
        val cipher = Cipher.getInstance(cipherTransformation)
        val secretKeySpec = SecretKeySpec(key, aesEncryptionAlgorithm)
        val ivParameterSpec = IvParameterSpec(initialVector)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
        plainText = cipher.doFinal(plainText)
        return plainText
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getKeyBytes(keySize: Int, key: String): ByteArray? {
        var keyBytes: ByteArray? = null
        if (keySize == 16) {
            keyBytes = ByteArray(16)
        } else if (keySize == 24) {
            keyBytes = ByteArray(24)
        } else if (keySize == 32) {
            keyBytes = ByteArray(32)
        }
        val parameterKeyBytes = key.toByteArray(charset(characterEncoding))
        System.arraycopy(
            parameterKeyBytes,
            0,
            keyBytes,
            0,
            Math.min(parameterKeyBytes.size, keyBytes!!.size)
        )
        return keyBytes
    }

    private fun encrypt(
        plainText: ByteArray,
        key: ByteArray,
        initialVector: ByteArray
    ): ByteArray? {
        var plainText: ByteArray? = plainText
        val cipher = Cipher.getInstance(cipherTransformation)
        val secretKeySpec = SecretKeySpec(key, aesEncryptionAlgorithm)
        val ivParameterSpec = IvParameterSpec(initialVector)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
        plainText = cipher.doFinal(plainText)
        return plainText
    }
}


