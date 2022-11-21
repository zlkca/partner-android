package com.artbird.onsite.ui.role

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Role
import com.artbird.onsite.network.RoleApi
import kotlinx.coroutines.launch


enum class ApiStatus { LOADING, ERROR, DONE }

class RoleViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _roles = MutableLiveData<List<Role>>(arrayListOf())
    val roles: LiveData<List<Role>> = _roles

    private val _role = MutableLiveData<Role>()
    val role: LiveData<Role> = _role

//    init {
//        getRoles()
//    }

    fun getRoles() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {

                _roles.value = RoleApi.retrofitService.getRoles()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _roles.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getRole(id: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _role.value = RoleApi.retrofitService.getRole(id)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _role.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun createRole(body: Role) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                RoleApi.retrofitService.createRole(body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _role.value = null // listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getRolesByName(name: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.role.ApiStatus.LOADING
            try {
                _roles.value = RoleApi.retrofitService.getRolesByName(name)
                _status.value = com.artbird.onsite.ui.role.ApiStatus.DONE
            } catch (e: Exception) {
                _roles.value = listOf()
                _status.value = com.artbird.onsite.ui.role.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun updateRole(id: String, body: Role) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                RoleApi.retrofitService.updateRole(id, body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _role.value = null // listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }
}