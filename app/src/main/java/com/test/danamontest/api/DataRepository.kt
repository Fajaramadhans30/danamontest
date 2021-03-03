package com.test.danamontest.api

import com.test.danamontest.model.DataModel
import io.reactivex.Observable

class DataRepository(private val apiService: ApiService) {
    fun getData(): Observable<List<DataModel>> {
        return apiService.getData()
    }
}