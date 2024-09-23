package ar.edu.ort.bootsshop.screens.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ar.edu.ort.bootsshop.data.Boot

class DetailViewModel : ViewModel() {

    private var _item = MutableLiveData<Boot>()

    fun setItem(item: Boot) {
        _item.value = item
    }

    fun getItem(): Boot {
        return _item.value!!
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetailViewModel() as T
            }
        }
    }
}