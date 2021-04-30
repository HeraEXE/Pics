package com.hera.pics.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hera.pics.R
import com.hera.pics.adapters.ImageListAdapter
import com.hera.pics.viewmoldels.ImageViewModel

class ImageList : Fragment() {
    private lateinit var _view: View
    private lateinit var viewModel: ImageViewModel
    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Setting view.
        _view = inflater.inflate(R.layout.fragment_image_list, container, false)

        // Setting View Model.
        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        viewModel.getImageUri()

        // Setting recycler.
        val frame: FrameLayout? = _view.findViewById(R.id.frame)
        if (frame != null) {
            // If recycler isn't null it means that the app is running on large screen device.
            recycler.layoutManager = GridLayoutManager(activity, 3)
        } else {
            // It means that the app is running on small device.
            recycler = _view.findViewById(R.id.image_recycler)
            recycler.layoutManager = GridLayoutManager(activity, 2)
        }
        recycler.adapter = ImageListAdapter(viewModel.image)

        return _view
    }

    override fun onStart() {
        viewModel.image.observe(viewLifecycleOwner, Observer {
            recycler.adapter?.notifyDataSetChanged()
        })
        super.onStart()
    }
}