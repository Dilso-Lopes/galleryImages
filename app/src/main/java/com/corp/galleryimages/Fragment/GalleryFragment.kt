package com.corp.galleryimages.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.corp.galleryimages.Assistance.GridAdapter
import com.corp.galleryimages.Assistance.RetrofitNet
import com.corp.galleryimages.Entity.EndpointLink
import com.corp.galleryimages.Entity.Picture
import com.corp.galleryimages.R
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    val BASE_URL: String = "https://api.imgur.com/"
    val USER_KEY: String = "Client-ID 1ceddedc03a5d71"
    private lateinit var  adapter: GridAdapter
    private lateinit var recyclerView: RecyclerView
    private var data: ArrayList<Picture> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.gallery_fragment, container, false)
        recyclerView = view.findViewById(R.id.galleryItems)
        var gridLayout = GridLayoutManager(context, 4)
        recyclerView.layoutManager = gridLayout
        recyclerView.setHasFixedSize(true)
        loadImages()

        return view
    }

    fun loadImages(){
        val retrofitPage = RetrofitNet.retrofitInstance(BASE_URL)
        val linkImage = retrofitPage.create(EndpointLink::class.java)

        linkImage.getEndpointLink(USER_KEY).enqueue(object : Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("Acontecu um Erro!!")

            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if(response.isSuccessful) {
                    var e = JSONObject(response.body()?.toString())
                    var h = e.getJSONArray("data")
                    for (i in 0 until h.length()) {
                        val item = h.getJSONObject(i)
                        val figure = Picture()
                        if (item.getBoolean("is_album")) {
                            figure.id = item.getString("cover")

                        } else {
                            figure.id = item.getString("id")

                        }
                        data.add(figure)

                    }
                    activity?.runOnUiThread {
                        try {
                            adapter = GridAdapter(data)
                            adapter.notifyDataSetChanged()
                            recyclerView.adapter = adapter

                        } catch ( e : Exception){
                            Log.i(TAG, "Error: "+e.localizedMessage)

                        }

                    }

                }else{
                    println("Acontecu um Erro!!")

                }

            }

        })

    }

}