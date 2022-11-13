package com.artbird.onsite.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Address
import com.artbird.onsite.domain.GeoPlace
import com.artbird.onsite.domain.GooglePredictionsResponse
import com.artbird.onsite.network.AddressApi
import com.artbird.onsite.repository.AddressRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


enum class ApiStatus { LOADING, ERROR, DONE }

class AddressViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _addresses = MutableLiveData<List<Address>>(arrayListOf())
    val addresses: LiveData<List<Address>> = _addresses

    private val _address = MutableLiveData<Address>()
    val address: LiveData<Address> = _address

//    init {
//        getAddresses()
//    }

    fun getAddressByClientId(clientId: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _addresses.value = AddressApi.retrofitService.getAddressByClientId(clientId)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _addresses.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    // get address by id
    fun getAddress(id: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _address.value = AddressApi.retrofitService.getAddress(id)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _address.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun createAddress(body: Address) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                AddressApi.retrofitService.createAddress(body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _address.value = null // listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun updateAddress(id: String, body: Address) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                AddressApi.retrofitService.updateAddress(id, body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _address.value = null // listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    private val _placeRsp = MutableLiveData<List<GeoPlace>>()
    val placeRsp: LiveData<List<GeoPlace>> = _placeRsp
    private val repo = AddressRepository()
    private val gson = Gson()

    fun getAddressPredictions(input: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getAddressPredictions(input)
                }
                val code = rsp.code()

                if(code == 200) {
                    _placeRsp.value = rsp.body()!!

                }else {
                    val type = object : TypeToken<List<GeoPlace>>() {}.type
                    val it: List<GeoPlace> = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _placeRsp.value = it!!
                }
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _placeRsp.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    // for google
//    private val _placeRsp = MutableLiveData<GooglePredictionsResponse>()
//    val placeRsp: LiveData<GooglePredictionsResponse> = _placeRsp
//    private val repo = AddressRepository()
//    private val gson = Gson()
//
//    fun getAddressPredictions(input: String) {
//        viewModelScope.launch {
//            _status.value = ApiStatus.LOADING
//            try {
//                val rsp = withContext(Dispatchers.IO) {
//                    repo.getAddressPredictions(input)
//                }
//                val code = rsp.code()
//
//                if(code == 200) {
//                    _placeRsp.value = rsp.body()!!
//
//                }else {
//                    val type = object : TypeToken<GooglePredictionsResponse>() {}.type
//                    val it: GooglePredictionsResponse = gson.fromJson(rsp.errorBody()!!.charStream(), type)
//                    _placeRsp.value = it!!
//                }
//                _status.value = ApiStatus.DONE
//            } catch (e: Exception) {
//                _placeRsp.value = null
//                _status.value = ApiStatus.ERROR
//                throw e
//            }
//        }
//    }
}