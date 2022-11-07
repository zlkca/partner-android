package com.artbird.onsite.ui.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Client
import com.artbird.onsite.domain.BaseAccount
import com.artbird.onsite.domain.ClientDetails
import com.artbird.onsite.network.ClientApi
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class ClientViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _accounts = MutableLiveData<List<BaseAccount>>(arrayListOf())
    val accounts: LiveData<List<BaseAccount>> = _accounts

    private val _clients = MutableLiveData<List<Client>>(arrayListOf())
    val clients: LiveData<List<Client>> = _clients

    private val _clientDetails = MutableLiveData<ClientDetails>()
    val clientDetails: LiveData<ClientDetails> = _clientDetails

//    init {
//        getClients()
//    }
    
//    fun getClients() {
//        viewModelScope.launch {
//            _status.value = ApiStatus.LOADING
//            try {
//
//                _clients.value = ClientApi.retrofitService.getClients()
//                _status.value = ApiStatus.DONE
//            } catch (e: Exception) {
//                _clients.value = listOf()
//                _status.value = ApiStatus.ERROR
//                throw e
//            }
//        }
//    }

    fun getClientsByRecommenderId(recommenderId: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _clients.value = ClientApi.retrofitService.getClientsByRecommenderId(recommenderId)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _clients.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun searchByRecommender(recommenderId: String, keyword: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _clients.value = ClientApi.retrofitService.searchByRecommender(recommenderId, keyword)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _clients.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getClientDetails(id: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _clientDetails.value = ClientApi.retrofitService.getClientDetails(id)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _clientDetails.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }
    fun createClient(body: ClientDetails) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.client.ApiStatus.LOADING
            try {
                ClientApi.retrofitService.createClient(body)
                _status.value = com.artbird.onsite.ui.client.ApiStatus.DONE
            } catch (e: Exception) {
                _clientDetails.value = null // listOf()
                _status.value = com.artbird.onsite.ui.client.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun updateClient(id: String, body: ClientDetails) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.client.ApiStatus.LOADING
            try {
                ClientApi.retrofitService.updateClient(id, body)
                _status.value = com.artbird.onsite.ui.client.ApiStatus.DONE
            } catch (e: Exception) {
                _clientDetails.value = null // listOf()
                _status.value = com.artbird.onsite.ui.client.ApiStatus.ERROR
                throw e
            }
        }
    }
}