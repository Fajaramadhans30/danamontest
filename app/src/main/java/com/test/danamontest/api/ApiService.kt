package com.test.danamontest.api

import com.test.danamontest.model.DataModel
import com.test.danamontest.util.API_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/photos")
    fun getData(): Observable<List<DataModel>>

    companion object Factory {
        fun create(): ApiService {

            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }

            val httpClient = OkHttpClient.Builder()
            // add your other interceptors …

            // add logging as last interceptor
            httpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_URL)
                .client(httpClient.build())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}