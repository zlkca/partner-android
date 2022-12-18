package com.artbird.onsite.ui.window

//import retrofit2.http.Body

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.FormError
import com.artbird.onsite.domain.Profile
import com.artbird.onsite.domain.Window
import com.artbird.onsite.network.WindowApi
import com.artbird.onsite.repository.WindowRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ApiStatus { LOADING, ERROR, DONE, DELETE_DONE, DELETE_ERROR }

class WindowViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _windows = MutableLiveData<List<Window>>(arrayListOf())
    val windows: LiveData<List<Window>> = _windows

    private val _window = MutableLiveData<Window>()
    val window: LiveData<Window> = _window

    private val _error = MutableLiveData<FormError>()
    val error: LiveData<FormError> = _error

    private val repo = WindowRepository()
    private val gson = Gson()

    private fun getWindows() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {

                _windows.value = WindowApi.retrofitService.getWindows()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _windows.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getWindow(windowId: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.window.ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getWindow(windowId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _window.value = rsp.body()!!
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _window.value = null
                    _error.value = it!!
                }
                _status.value = com.artbird.onsite.ui.window.ApiStatus.DONE
            } catch (e: Exception) {
                _window.value = null
                _error.value = FormError(500, "any", e.message.toString())
                _status.value = com.artbird.onsite.ui.window.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun createWindow(body: Window) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                WindowApi.retrofitService.createWindow(body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _window.value = null // listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getWindowsByRoomId(roomId: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.window.ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getWindowsByRoomId(roomId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _windows.value = rsp.body()!!
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _windows.value = listOf()
                    _error.value = it!!
                }
                _status.value = com.artbird.onsite.ui.window.ApiStatus.DONE
            } catch (e: Exception) {
                _windows.value = listOf()
                _error.value = FormError(500, "any", e.message.toString())
                _status.value = com.artbird.onsite.ui.window.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun updateWindow(id: String, body: Window) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                WindowApi.retrofitService.updateWindow(id, body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _window.value = null // listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun deleteWindow(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.window.ApiStatus.LOADING
            try {
                WindowApi.retrofitService.deleteWindow(id)
                _status.value = com.artbird.onsite.ui.window.ApiStatus.DELETE_DONE
            } catch (e: Exception) {
                _window.value = null
                _status.value = com.artbird.onsite.ui.window.ApiStatus.DELETE_ERROR
                throw e
            }
        }
    }
}