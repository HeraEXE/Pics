package com.hera.pics.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hera.pics.R
import com.hera.pics.ui.fragments.Image

class ImageListAdapter(val image: MutableLiveData<Array<String>>) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    // View Holder.
    class ViewHolder(view: View, parent: ViewGroup) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_view)
        val context = parent.context
    }

    // On Create View Holder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item_view, parent, false)
        return ViewHolder(view, parent)
    }

    // Get Item Count.
    override fun getItemCount(): Int {
        return image.value?.size ?: 0
    }

    // On Bind View Holder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.context)
            .load(image.value?.get(position))
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            val bundle = bundleOf(
                Image.IMAGE_URI to image.value?.get(position)
            )
            Navigation.findNavController(holder.imageView).navigate(R.id.action_imageList_to_image, bundle)
        }
    }
}