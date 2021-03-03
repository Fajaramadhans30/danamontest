package com.test.danamontest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.test.danamontest.realm.User
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity()  {
    private lateinit var realm: Realm
    private lateinit var userList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*REALM*/
        Realm.init(this);
        val configuration = RealmConfiguration.Builder()
            .name("User.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(configuration)
        realm = Realm.getDefaultInstance()


        buttonLogin.setOnClickListener {
            if(editTextUserName.text.toString().equals("admin") && editTextPassword.text.toString() == "admin"){
                val intentDetail = Intent(it.context, AdminActivity::class.java)
                startActivity(intentDetail)
            } else if (checkUser("","")){
                val intentDetail = Intent(it.context, UserActivity::class.java)
                startActivity(intentDetail)
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun checkUser(username: String, password: String): Boolean {
        val realmObjects: RealmResults<User> =
            realm.where(User::class.java).findAll()
        for (myRealmObject in realmObjects) {
            if (username == myRealmObject.username && password == myRealmObject.password) {
                return true
            }
        }
        return false
    }
}