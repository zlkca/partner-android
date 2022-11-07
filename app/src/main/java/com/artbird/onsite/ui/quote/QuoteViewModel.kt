package com.artbird.onsite.ui.quote


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Quote
import com.artbird.onsite.domain.QuoteRequest
import com.artbird.onsite.network.QuoteApi
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class QuoteViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _quotes = MutableLiveData<List<Quote>>(arrayListOf())
    val quotes: LiveData<List<Quote>> = _quotes

    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> = _quote

//    init {
////        getQuotes()
//    }

    private fun getQuotes() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {

                _quotes.value = QuoteApi.retrofitService.getQuotes()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _quotes.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getQuote(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.quote.ApiStatus.LOADING
            try {
                _quote.value = QuoteApi.retrofitService.getQuote(id)
                _status.value = com.artbird.onsite.ui.quote.ApiStatus.DONE
            } catch (e: Exception) {
                _quote.value = null
                _status.value = com.artbird.onsite.ui.quote.ApiStatus.ERROR
                throw e
            }
        }
    }


    fun deleteQuote(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.quote.ApiStatus.LOADING
            try {
                QuoteApi.retrofitService.deleteQuote(id)
                _status.value = com.artbird.onsite.ui.quote.ApiStatus.DONE
            } catch (e: Exception) {
                _quote.value = null
                _status.value = com.artbird.onsite.ui.quote.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun createQuote(body: QuoteRequest) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _quotes.value = QuoteApi.retrofitService.createQuote(body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _quotes.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getQuotesByAppointmentId(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.quote.ApiStatus.LOADING
            try {

                _quotes.value = QuoteApi.retrofitService.getQuotesByAppointmentId(id)
                _status.value = com.artbird.onsite.ui.quote.ApiStatus.DONE
            } catch (e: Exception) {
                _quotes.value = listOf()
                _status.value = com.artbird.onsite.ui.quote.ApiStatus.ERROR
                throw e
            }
        }
    }

}