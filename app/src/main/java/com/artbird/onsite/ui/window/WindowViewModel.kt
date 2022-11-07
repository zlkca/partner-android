package com.artbird.onsite.ui.window

//import retrofit2.http.Body

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Window
//import com.shutterlux.onsite.network.LayoutApi
import com.artbird.onsite.network.WindowApi
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE, DELETE_DONE, DELETE_ERROR }

class WindowViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _windows = MutableLiveData<List<Window>>(arrayListOf())
    val windows: LiveData<List<Window>> = _windows

    private val _window = MutableLiveData<Window>()
    val window: LiveData<Window> = _window

    init {
//        getWindows()
    }

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

    fun getWindow(id: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _window.value = WindowApi.retrofitService.getWindow(id)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _window.value = null
                _status.value = ApiStatus.ERROR
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
                _windows.value = WindowApi.retrofitService.getWindowsByRoomId(roomId)
                _status.value = com.artbird.onsite.ui.window.ApiStatus.DONE
            } catch (e: Exception) {
                _windows.value = listOf()
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