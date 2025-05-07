package ar.edu.ort.frases.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ar.edu.ort.frases.shared.GetServiceQuotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getQuotesUseCase: GetServiceQuotes
): ViewModel() {

     var Quote = mutableStateOf("Cargando....")
     var Author = mutableStateOf("")
    var Category = mutableStateOf("")

    fun loadQuotes() {
        viewModelScope.launch {

            val quote = getQuotesUseCase.invoke()
            if(!quote.isNullOrEmpty() && quote.size > 0) {
                Quote.value = quote!!.get(0)!!.quote
                Author.value = quote!!.get(0)!!.author
                Category.value = quote!!.get(0)!!.category
            }
        }
    }


    /*companion object {

        fun provideFactory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel() as T
            }
        }
    }*/
}