package com.corp.galleryimages.Entity

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface EndpointLink {
    @GET("/3/gallery/search/?q=cats")
    fun getEndpointLink(@Header("Authorization") key : String) : Call<JsonObject>

}