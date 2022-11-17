package com.corp.galleryimages.Assistance

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNet {
    companion object{
        fun retrofitInstance(path: String) : Retrofit{
            return Retrofit.Builder()
                    .baseUrl(path)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        }

    }

}