package com.example.ui

import retrofit2.Call
import retrofit2.http.GET

interface Myinterface {
    @GET("products")
    fun productData() : Call<MyData>
}