package com.artbird.onsite.ui.account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Account
import com.artbird.onsite.repository.AccountRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ApiStatus { IDLE, LOADING, ERROR, DONE }

class AccountViewModel : ViewModel() {
    var status by mutableStateOf(ApiStatus.IDLE)

    private val _accounts = MutableLiveData<List<Account>>(listOf())
    val accounts: LiveData<List<Account>> = _accounts

    private val repo = AccountRepository()
    private val gson = Gson()
    
    fun clearStatus(){
        status = ApiStatus.IDLE
    }
    
    fun search(query: Map<String, String>) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.search(query)
                }
                val code = rsp.code()

                if(code == 200) {
                    _accounts.value = rsp.body()!!
                    status = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<List<Account>>() {}.type
                    val it: List<Account> = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _accounts.value = it!!
                    status = ApiStatus.ERROR
                }

            } catch (e: Exception) {
                _accounts.value = null
                status = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getClientsByRecommenderId(recommenderId: String) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getClientsByRecommenderId(recommenderId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _accounts.value = rsp.body()!!
                    status = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<List<Account>>() {}.type
                    val it: List<Account> = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _accounts.value = it!!
                    status = ApiStatus.ERROR
                }
            } catch (e: Exception) {
                _accounts.value = null
                status = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getAccountsByEmployeeId(employeeId: String, roleName: String) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getAccountsByEmployeeId(employeeId, roleName)
                }
                val code = rsp.code()

                if(code == 200) {
                    _accounts.value = rsp.body()!!
                    status = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<List<Account>>() {}.type
                    val it: List<Account> = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _accounts.value = it!!
                    status = ApiStatus.ERROR
                }
            } catch (e: Exception) {
                _accounts.value = null
                status = ApiStatus.ERROR
                throw e
            }
        }
    }
}