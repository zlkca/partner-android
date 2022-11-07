package com.artbird.onsite.ui.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Project
import com.artbird.onsite.network.RecordApi
import com.artbird.onsite.repository.RecordRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


enum class ApiStatus { LOADING, ERROR, DONE }

class RecordViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _records = MutableLiveData<List<Project>>(arrayListOf())
    val records: LiveData<List<Project>> = _records

    private val _record = MutableLiveData<Project>()
    val record: LiveData<Project> = _record

    private val repo = RecordRepository()
    private val gson = Gson()

    fun getRecord(recordId: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.project.ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getRecord(recordId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _record.value = rsp.body()!!
                }else {
                    val type = object : TypeToken<Project>() {}.type
                    val it: Project = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _record.value = it!!
                }
                _status.value = com.artbird.onsite.ui.project.ApiStatus.DONE
            } catch (e: Exception) {
                _record.value = null
                _status.value = com.artbird.onsite.ui.project.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun deleteCase(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.project.ApiStatus.LOADING
            try {
                RecordApi.retrofitService.deleteRecord(id)
                _status.value = com.artbird.onsite.ui.project.ApiStatus.DONE
            } catch (e: Exception) {
                _record.value = null
                _status.value = com.artbird.onsite.ui.project.ApiStatus.ERROR
                throw e
            }
        }
    }
    

    fun getRecordsByClientId(clientId: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.project.ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getRecordsByClientId(clientId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _records.value = rsp.body()!!
                }else {
                    val type = object : TypeToken<List<Project>>() {}.type
                    val it: List<Project> = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _records.value = it!!
                }
                _status.value = com.artbird.onsite.ui.project.ApiStatus.DONE
            } catch (e: Exception) {
                _records.value = listOf()
                _status.value = com.artbird.onsite.ui.project.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun createRecord(body: Project) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.project.ApiStatus.LOADING
            try {
                RecordApi.retrofitService.createRecord(body)
                _status.value = com.artbird.onsite.ui.project.ApiStatus.DONE
            } catch (e: Exception) {
                _record.value = null // listOf()
                _status.value = com.artbird.onsite.ui.project.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun updateRecord(id: String, body: Project) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.project.ApiStatus.LOADING
            try {
                RecordApi.retrofitService.updateRecord(id, body)
                _status.value = com.artbird.onsite.ui.project.ApiStatus.DONE
            } catch (e: Exception) {
                _record.value = null // listOf()
                _status.value = com.artbird.onsite.ui.project.ApiStatus.ERROR
                throw e
            }
        }
    }

}