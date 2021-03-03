package com.test.danamontest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.test.danamontest.realm.User
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {
    private lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*REALM*/
        Realm.init(this);
        val configuration = RealmConfiguration.Builder()
            .name("User.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(configuration)
        realm = Realm.getDefaultInstance()
    }


    private fun saveDataToRealm(fullname: String, phoneNumber: String, username: String, password: String, result: Int?) {
        try {

            realm.beginTransaction()
            val currentIdNumber :Number? = realm.where(User::class.java).max("id")
            val nextID :Int

            nextID = if (currentIdNumber == null) {
                1
            } else {
                currentIdNumber.toInt() + 1
            }

            val user = User()
            user.fullname = fullname
            user.phoneNumber = phoneNumber
            user.username = username
            user.password = password
            user.id = nextID

            realm.copyToRealmOrUpdate(user)
            realm.commitTransaction()

            Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
        } catch (e:Exception) {
            Log.d("MASUK KESINI KAH ?", "saveDataToRealm: " + e)
            Toast.makeText(this, "GAGALLL $e", Toast.LENGTH_SHORT).show()

        }
    }
}