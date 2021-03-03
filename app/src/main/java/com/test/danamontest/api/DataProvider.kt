package com.test.danamontest.api

object DataProvider {
    fun dataProviderRepository():DataRepository{
        return  DataRepository(ApiService.create())
    }
}