package com.artbird.onsite.ui.account

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

enum class ApiStatus { LOADING, ERROR, DONE }

class AccountViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> = _accounts

    private val repo = AccountRepository()
    private val gson = Gson()

    fun search(query: Map<String, String>) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.search(query)
                }
                val code = rsp.code()

                if(code == 200) {
                    _accounts.value = rsp.body()!!

                }else {
                    val type = object : TypeToken<Account>() {}.type
                    val it: Account = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _accounts.value = it!!
                }
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _accounts.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }
}