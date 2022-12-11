package com.artbird.onsite.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Auth
import com.artbird.onsite.domain.FormError
import com.artbird.onsite.domain.Credential
import com.artbird.onsite.repository.AuthRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


enum class ApiStatus { LOADING, ERROR, DONE }

class AuthViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status
    
    private val _auth = MutableLiveData<Auth>()
    val auth: LiveData<Auth> = _auth

    private val _error = MutableLiveData<FormError>()
    val error: LiveData<FormError> = _error

    private val repo = AuthRepository()
    private val gson = Gson()

    fun clearError(){
        _error.value = null
    }

    fun login(credential: Credential) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.login(credential)
                }
                val code = rsp.code()

                if(code == 200) {
                    _auth.value = rsp.body()!!
                    _error.value = FormError() // No error
                    _status.value = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _error.value = it!!
                    _status.value = ApiStatus.ERROR
                }

            } catch (e: Exception) {
                _auth.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun signup(account: Account) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.signup(account)
                }
                val code = rsp.code()
                if(code == 200) {
                    _auth.value = rsp.body()!!
                    _error.value = FormError() // No error
                    _status.value = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _error.value = it!!
                    _status.value = ApiStatus.ERROR
                }
            } catch (e: Exception) {
                _auth.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun changePassword(credential: Credential) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.changePassword(credential)
                }
                val code = rsp.code()
                if(code == 200) {
//                    _auth.value = rsp.body()!!
                    _error.value = FormError() // No error
                    _status.value = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _error.value = it!!
                    _status.value = ApiStatus.ERROR
                }
            } catch (e: Exception) {
                _auth.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }
}