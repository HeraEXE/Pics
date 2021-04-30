package com.hera.pics.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hera.pics.R
import com.hera.pics.ui.MainActivity

class Image : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_image, container, false)

        val imageUri = arguments?.getString(IMAGE_URI)

        val image: ImageView = view.findViewById(R.id.image)

        activity?.let {
            Glide.with(it)
                .load(imageUri)
                .error(R.drawable.baseline_cloud_off_24)
                .into(image)
        }


        return view
    }

    companion object {
        const val IMAGE_URI = "image_uri"
    }
}