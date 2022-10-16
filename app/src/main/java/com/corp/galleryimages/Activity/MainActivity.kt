package com.corp.galleryimages.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.corp.galleryimages.Entity.Picture
import com.corp.galleryimages.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadImages()
    }

    private fun loadImages() {
        val httpClient = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.imgur.com/3/gallery/search/?q=cats")
            .header("Authorization", "Client-ID 1ceddedc03a5d71")
            .build()
        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Error: "+e)

            }

            override fun onResponse(call: Call, response: Response) {
                val data = JSONObject(response.body()!!.string())
                val items = data.getJSONArray("data")
                val figures: MutableList<Picture> = ArrayList()
                for (i in 0 until items.length()) {
                    val item = items.getJSONObject(i)
                    val figure = Picture()
                    if (item.getBoolean("is_album")) {
                        figure.id = item.getString("cover")

                    } else {
                        figure.id = item.getString("id")

                    }
                    figures.add(figure)
                    runOnUiThread { addPhotos(figures)}

                }

            }

        })

    }

    private class ViewImages(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var figure: AppCompatImageView? = null

    }

    private fun addPhotos(portrait: List<Picture>) {
        galleryItems.layoutManager = GridLayoutManager(this, 4)
        val adapter: RecyclerView.Adapter<ViewImages> = object : RecyclerView.Adapter<ViewImages>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewImages {
                val viewRecycler = ViewImages(layoutInflater.inflate(R.layout.item_image, null))
                viewRecycler.figure = viewRecycler.itemView.findViewById(R.id.imageView)

                return viewRecycler
            }

            override fun onBindViewHolder(holder: ViewImages, position: Int) {
                Picasso.get()
                    .load("https://i.imgur.com/" + portrait[position].id + ".jpg")
                    .into(holder.figure)

            }

            override fun getItemCount(): Int {
                return portrait.size

            }

        }

        galleryItems.adapter = adapter

    }
    
}

