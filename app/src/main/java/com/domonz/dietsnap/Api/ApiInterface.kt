package com.domonz.dietsnap.Api

import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("/food_info/?format=json")
    fun getFoodInfo(): Call<Any>

}