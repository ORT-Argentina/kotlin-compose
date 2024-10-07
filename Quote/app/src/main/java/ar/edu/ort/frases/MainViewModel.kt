package ar.edu.ort.frases

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ar.edu.ort.frases.shared.GetServiceQuotes
import ar.edu.ort.frases.shared.IServiceQuotes
import kotlinx.coroutines.launch

class MainViewModel(
    private val getQuotesUseCase: GetServiceQuotes
): ViewModel() {

     var Quote = mutableStateOf<String>("Cargando...")
     var Author = mutableStateOf<String>("")

    fun loadQuotes() {
        viewModelScope.launch {
            val quote = getQuotesUseCase.execute()
            if(!quote.isNullOrEmpty() && quote.size > 0) {
                Quote.value = quote!!.get(0)!!.quote
                Author.value = quote!!.get(0)!!.author
            }
        }
    }

    companion object {

        fun provideFactory(getQuotesUseCase: GetServiceQuotes): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(getQuotesUseCase) as T
            }
        }
    }
}