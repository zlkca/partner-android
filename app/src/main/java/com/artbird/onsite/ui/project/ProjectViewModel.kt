package com.artbird.onsite.ui.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Project
import com.artbird.onsite.network.RecordApi
import com.artbird.onsite.repository.ProjectRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


enum class ApiStatus { LOADING, ERROR, DONE }

class ProjectViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _projects = MutableLiveData<List<Project>>(arrayListOf())
    val projects: LiveData<List<Project>> = _projects

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project> = _project

    private val repo = ProjectRepository()
    private val gson = Gson()

    fun getProject(projectId: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.project.ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getRecord(projectId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _project.value = rsp.body()!!
                }else {
                    val type = object : TypeToken<Project>() {}.type
                    val it: Project = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _project.value = it!!
                }
                _status.value = com.artbird.onsite.ui.project.ApiStatus.DONE
            } catch (e: Exception) {
                _project.value = null
                _status.value = com.artbird.onsite.ui.project.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getProjectsByRecommenderId(recommenderId: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.project.ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getProjectsByRecommenderId(recommenderId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _projects.value = rsp.body()!!
                }else {
//                    val type = object : TypeToken<List<Project>>() {}.type
//                    val it: List<Project> = gson.fromJson(rsp.errorBody()!!.charStream(), type)
//                    _projects.value = it!!
                    _projects.value = listOf()
                }
                _status.value = com.artbird.onsite.ui.project.ApiStatus.DONE
            } catch (e: Exception) {
                _projects.value = listOf()
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
                    _projects.value = rsp.body()!!
                }else {
                    val type = object : TypeToken<List<Project>>() {}.type
                    val it: List<Project> = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _projects.value = it!!
                }
                _status.value = com.artbird.onsite.ui.project.ApiStatus.DONE
            } catch (e: Exception) {
                _projects.value = listOf()
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
                _project.value = null
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
                _project.value = null // listOf()
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
                _project.value = null // listOf()
                _status.value = com.artbird.onsite.ui.project.ApiStatus.ERROR
                throw e
            }
        }
    }

}