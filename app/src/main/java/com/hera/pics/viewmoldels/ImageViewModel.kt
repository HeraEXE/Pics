package com.hera.pics.viewmoldels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hera.pics.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ImageViewModel : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val repository = Repository()
    val images = MutableLiveData<Array<String>>()
    var status = MutableLiveData<Int>()


    fun getImageUri() {
        scope.launch {
            try {
                images.value = repository.getImageUri()
                status.value = SUCCESS
            } catch (e: Exception) {
                status.value = FAILURE
            }
        }
    }

    companion object {
        const val SUCCESS = 0
        const val FAILURE = 1
    }
}