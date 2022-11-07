package com.artbird.onsite.ui.case

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Case
import com.artbird.onsite.network.CaseApi
import kotlinx.coroutines.launch


enum class ApiStatus { LOADING, ERROR, DONE }

class CaseViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _cases = MutableLiveData<List<Case>>(arrayListOf())
    val cases: LiveData<List<Case>> = _cases

    private val _case = MutableLiveData<Case>()
    val case: LiveData<Case> = _case

//    init {
////        getCases()
//    }

    private fun getCases() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {

                _cases.value = CaseApi.retrofitService.getCases()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _cases.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getCase(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.case.ApiStatus.LOADING
            try {
                _case.value = CaseApi.retrofitService.getCase(id)
                _status.value = com.artbird.onsite.ui.case.ApiStatus.DONE
            } catch (e: Exception) {
                _case.value = null
                _status.value = com.artbird.onsite.ui.case.ApiStatus.ERROR
                throw e
            }
        }
    }


    fun deleteCase(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.case.ApiStatus.LOADING
            try {
                CaseApi.retrofitService.deleteCase(id)
                _status.value = com.artbird.onsite.ui.case.ApiStatus.DONE
            } catch (e: Exception) {
                _case.value = null
                _status.value = com.artbird.onsite.ui.case.ApiStatus.ERROR
                throw e
            }
        }
    }
    

    fun getCaseByAddressId(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.case.ApiStatus.LOADING
            try {

                _case.value = CaseApi.retrofitService.getCaseByAddressId(id)
                _status.value = com.artbird.onsite.ui.case.ApiStatus.DONE
            } catch (e: Exception) {
                _case.value = null
                _status.value = com.artbird.onsite.ui.case.ApiStatus.ERROR
                throw e
            }
        }
    }

}