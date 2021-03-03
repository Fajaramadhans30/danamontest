package com.test.danamontest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.danamontest.adapter.UserAdapter
import com.test.danamontest.realm.User
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_list.*

class AdminActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var userList: ArrayList<User>
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        /*REALM*/
        Realm.init(this);
        val configuration = RealmConfiguration.Builder()
            .name("User.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(configuration)
        realm = Realm.getDefaultInstance()

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvList.layoutManager = linearLayoutManager

        getAllUser()
    }

    private fun getAllUser() {
        userList.clear()
        userList = ArrayList()

        val result: RealmResults<User> = realm.where<User>(User::class.java).findAll()
        rvList.adapter = UserAdapter(result, this)
        rvList.adapter!!.notifyDataSetChanged()
    }
}