package com.artbird.onsite.ui.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.FormError
import com.artbird.onsite.domain.Project
import com.artbird.onsite.network.RecordApi
import com.artbird.onsite.repository.ProjectRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


enum class ApiStatus { IDLE, LOADING, ERROR, DONE }

class ProjectViewModel : ViewModel() {
    var status by mutableStateOf(ApiStatus.IDLE)

    private val _projects = MutableLiveData<List<Project>>(arrayListOf())
    val projects: LiveData<List<Project>> = _projects

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project> = _project

    private val _error = MutableLiveData<FormError>()
    val error: LiveData<FormError> = _error

    private val repo = ProjectRepository()
    private val gson = Gson()

    fun clearError(){
        _error.value = null
    }
    fun clearStatus(){
        status = ApiStatus.IDLE
    }

    fun getProject(projectId: String) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getRecord(projectId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _project.value = rsp.body()!!
                    status = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _project.value = null
                    _error.value = it
                    status = ApiStatus.ERROR
                }
            } catch (e: Exception) {
                _project.value = null
                status = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getProjectsByRecommenderId(recommenderId: String) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getProjectsByRecommenderId(recommenderId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _projects.value = rsp.body()!!
                    status = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _projects.value = listOf()
                    _error.value = it
                    status = ApiStatus.ERROR
                }
            } catch (e: Exception) {
                _projects.value = listOf()
                status = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getProjectsByClientId(clientId: String) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getProjectsByClientId(clientId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _projects.value = rsp.body()!!
                    status = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _projects.value = listOf()
                    _error.value = it
                    status = ApiStatus.ERROR
                }
            } catch (e: Exception) {
                _projects.value = listOf()
                status = ApiStatus.ERROR
                throw e
            }
        }
    }

}