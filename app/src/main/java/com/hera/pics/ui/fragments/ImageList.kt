package com.hera.pics.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
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
    private lateinit var noInternetImage: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Setting view.
        _view = inflater.inflate(R.layout.fragment_image_list, container, false)

        // Setting View Model.
        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)

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

        noInternetImage = _view.findViewById(R.id.no_internet_image)

        return _view
    }


    override fun onStart() {
        super.onStart()

        viewModel.getImageUri()
        // Checks Internet connection.
        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (viewModel.status.value == ImageViewModel.FAILURE) {
                recycler.visibility = View.GONE
                noInternetImage.visibility = View.VISIBLE
                recycler.adapter = ImageListAdapter(MutableLiveData<Array<String>>())
            } else {
                noInternetImage.visibility = View.GONE
                recycler.visibility = View.VISIBLE
                recycler.adapter = ImageListAdapter(viewModel.image)
            }
        })

        viewModel.image.observe(viewLifecycleOwner, Observer {
            recycler.adapter?.notifyDataSetChanged()
        })
    }
}