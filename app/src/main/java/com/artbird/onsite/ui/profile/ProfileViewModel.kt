package com.artbird.onsite.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.FormError
import com.artbird.onsite.domain.Client
import com.artbird.onsite.domain.Profile
import com.artbird.onsite.network.ProfileApi
import com.artbird.onsite.repository.ProfileRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ApiStatus {
    IDLE,
    LOADING,
    ERROR,
    DONE,
    CREATE_DONE,
    UPDATE_DONE,
    DELETE_DONE,
}

class ProfileViewModel : ViewModel() {
    var status by mutableStateOf(ApiStatus.IDLE)

    private val _profiles = MutableLiveData<List<Account>>(arrayListOf())
    val profiles: LiveData<List<Account>> = _profiles

    private val _clients = MutableLiveData<List<Client>>(arrayListOf())
    val clients: LiveData<List<Client>> = _clients

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile

    private val _error = MutableLiveData<FormError>()
    val error: LiveData<FormError> = _error

    private val repo = ProfileRepository()
    private val gson = Gson()

    fun clearError(){
        _error.value = null
    }
    fun clearStatus(){
        status = ApiStatus.IDLE
    }
    fun getProfileByAccountId(accountId: String) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getProfileByAccountId(accountId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _profile.value = rsp.body()!!
                    status = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _profile.value = null
                    _error.value = it!!
                    status = ApiStatus.ERROR
                }

            } catch (e: Exception) {
                _profile.value = null
                status = ApiStatus.ERROR
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
//            _status.value = ApiStatus.LOADING
            try {
                _clients.value = ProfileApi.retrofitService.getClientsByRecommenderId(recommenderId)
//                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _clients.value = listOf()
//                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun searchByRecommender(recommenderId: String, keyword: String) {
        viewModelScope.launch {
//            _status.value = ApiStatus.LOADING
            try {
                _clients.value = ProfileApi.retrofitService.searchByRecommender(recommenderId, keyword)
//                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _clients.value = listOf()
//                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun searchByAssignedEmployee(employeeId: String, keyword: String) {
        viewModelScope.launch {
//            _status.value = ApiStatus.LOADING
            try {
                _clients.value = ProfileApi.retrofitService.searchByAssignedEmployee(employeeId, keyword)
//                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _clients.value = listOf()
//                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }


    fun createProfile(body: Profile) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.createProfile(body)
                }
                val code = rsp.code()

                if(code == 200) {
                    _profile.value = rsp.body()!!
                    _error.value = FormError()
                    status = ApiStatus.CREATE_DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _error.value = it!!
                    status = ApiStatus.ERROR
                }
            } catch (e: Exception) {
                _profile.value = null
                status = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun updateProfileByAccountId(accountId: String, body: Profile) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.updateProfileByAccountId(accountId, body)
                }
                val code = rsp.code()

                if(code == 200) {
                    _profile.value = rsp.body()!!
                    _error.value = FormError()
                    status = ApiStatus.UPDATE_DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _error.value = it!!
                    status = ApiStatus.ERROR
                }

            } catch (e: Exception) {
                _profile.value = null
                status = ApiStatus.ERROR
                throw e
            }
        }
    }
}