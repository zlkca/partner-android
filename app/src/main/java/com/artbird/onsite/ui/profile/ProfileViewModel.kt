package com.artbird.onsite.ui.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Client
import com.artbird.onsite.domain.Profile
import com.artbird.onsite.network.ProfileApi
import com.artbird.onsite.repository.AccountRepository
import com.artbird.onsite.repository.ProfileRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ApiStatus { LOADING, ERROR, DONE }

class ProfileViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _profiles = MutableLiveData<List<Profile>>(arrayListOf())
    val profiles: LiveData<List<Profile>> = _profiles

    private val _clients = MutableLiveData<List<Client>>(arrayListOf())
    val clients: LiveData<List<Client>> = _clients

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile

    private val repo = ProfileRepository()
    private val gson = Gson()
    
    fun getProfileByAccountId(accountId: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getProfileByAccountId(accountId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _profile.value = rsp.body()!!
                }else {
                    val type = object : TypeToken<Profile>() {}.type
                    val it: Profile = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _profile.value = it!!
                }
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _profile.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }
    
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
                _clients.value = ProfileApi.retrofitService.getClientsByRecommenderId(recommenderId)
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
                _clients.value = ProfileApi.retrofitService.searchByRecommender(recommenderId, keyword)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _clients.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun searchByAssignedEmployee(employeeId: String, keyword: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _clients.value = ProfileApi.retrofitService.searchByAssignedEmployee(employeeId, keyword)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _clients.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }


    fun createClient(body: Profile) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.client.ApiStatus.LOADING
            try {
                ProfileApi.retrofitService.createClient(body)
                _status.value = com.artbird.onsite.ui.client.ApiStatus.DONE
            } catch (e: Exception) {
                _profile.value = null // listOf()
                _status.value = com.artbird.onsite.ui.client.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun updateClient(id: String, body: Profile) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.client.ApiStatus.LOADING
            try {
                ProfileApi.retrofitService.updateClient(id, body)
                _status.value = com.artbird.onsite.ui.client.ApiStatus.DONE
            } catch (e: Exception) {
                _profile.value = null // listOf()
                _status.value = com.artbird.onsite.ui.client.ApiStatus.ERROR
                throw e
            }
        }
    }
}