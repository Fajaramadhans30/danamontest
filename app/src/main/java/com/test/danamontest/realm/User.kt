package com.test.danamontest.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class User (
    @PrimaryKey
    var id: Int? = null,
    var fullname: String? = null,
    var phoneNumber: String? = null,
    var address: String? = null,
    var username: String? = null,
    var password: String? = null
) : RealmObject()