package com.corp.galleryimages.Assistance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.*
import androidx.recyclerview.widget.RecyclerView
import com.corp.galleryimages.Entity.Picture
import com.corp.galleryimages.R
import com.squareup.picasso.Picasso
import java.lang.Exception

class GridAdapter(val imagens: ArrayList<Picture>): RecyclerView.Adapter<GridAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardImage:  ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load("https://i.imgur.com/" + imagens[position].id + ".jpg")
                .into(holder.cardImage, object : com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        if(imagens != null){
                            holder.cardImage.visibility

                        }else{
                            holder.cardImage.isInvisible

                        }

                    }

                    override fun onError(e: Exception?) {

                    }

                })

    }

    override fun getItemCount(): Int {

        return imagens.size
    }

}