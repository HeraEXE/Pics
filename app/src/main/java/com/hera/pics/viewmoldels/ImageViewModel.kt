package com.hera.pics.viewmoldels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hera.pics.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val repository = Repository()
    val image = MutableLiveData<Array<String>>()


    fun getImageUri() {
        scope.launch {
            image.value = repository.getImageUri()
        }
    }
}