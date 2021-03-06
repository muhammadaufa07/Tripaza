package com.tripaza.tripaza.api

import androidx.viewbinding.BuildConfig
import com.tripaza.tripaza.helper.Constants.API_BASE_URL
import com.tripaza.tripaza.helper.Constants.MAP_API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        fun getApiService():ApiService{
            val loggingInterceptor = if (BuildConfig.DEBUG){
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            }else{
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            }

            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
        fun getMapApiService():ApiService{
            val loggingInterceptor = if (BuildConfig.DEBUG){
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            }else{
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            }

            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(MAP_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}