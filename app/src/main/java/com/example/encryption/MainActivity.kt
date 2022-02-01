package com.example.encryption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.encryption.callbacks.CallBack
import com.example.encryption.utility.Encryption
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chum = "ABCDEFGHIJKLMNOT"
        val chum192 = "ABCDEFGHIJKLMNOTWERTYUIO"
        val chum256 = "ABCDEFGHIJKLMNOTABCDEFGHIJKLMNOT"
         val data = "Aqib Javed"


        Encryption.encrypt(16,data,chum,object : CallBack {
            override fun callback(result: Any) {
                Log.d("TAG", "onCreate: encryption 128  $result")

                Encryption.decrypt(16, result.toString(),chum,object : CallBack {
                    override fun callback(result: Any) {
                        Log.d("TAG", "onCreate: decryption 128 $result")
                    }
                })

            }
        })
        Encryption.encrypt(24,data,chum192,object : CallBack {
            override fun callback(result: Any) {
                Log.d("TAG", "onCreate: encryption 192  $result")

                Encryption.decrypt(24, result.toString(),chum192,object : CallBack {
                    override fun callback(result: Any) {
                        Log.d("TAG", "onCreate: decryption 192 $result")
                    }
                })

            }
        })
        Encryption.encrypt(32,data,chum256,object : CallBack {
            override fun callback(result: Any) {
                Log.d("TAG", "onCreate: encryption 256  $result")

                Encryption.decrypt(32, result.toString(),chum256,object : CallBack {
                    override fun callback(result: Any) {
                        Log.d("TAG", "onCreate: decryption 256 $result")
                    }
                })

            }
        })
    }
}