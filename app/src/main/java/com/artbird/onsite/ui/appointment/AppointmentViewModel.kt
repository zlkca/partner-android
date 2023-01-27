package com.artbird.onsite.ui.appointment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.domain.FormError
import com.artbird.onsite.network.AppointmentApi
import com.artbird.onsite.repository.AppointmentRepository
import com.artbird.onsite.repository.ProfileRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ApiStatus { IDLE, LOADING, ERROR, DONE }

class AppointmentViewModel : ViewModel() {
    var status by mutableStateOf(ApiStatus.IDLE)

    private val _appointments = MutableLiveData<List<Appointment>>(arrayListOf())
    val appointments: LiveData<List<Appointment>> = _appointments

    private val _appointment = MutableLiveData<Appointment>()
    val appointment: LiveData<Appointment> = _appointment

    private val _error = MutableLiveData<FormError>()
    val error: LiveData<FormError> = _error

    private val repo = AppointmentRepository()
    private val gson = Gson()

    fun clearError(){
        _error.value = null
    }
    fun clearStatus(){
        status = ApiStatus.IDLE
    }

    fun getAppointmentsByEmployeeId(employeeId: String) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getAppointmentsByEmployeeId(employeeId)
                }
                val code = rsp.code()

                if(code == 200) {
                    _appointments.value = rsp.body()!!
                    status = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _appointments.value = listOf()
                    _error.value = it!!
                    status = ApiStatus.ERROR
                }

            } catch (e: Exception) {
                _appointments.value = listOf()
                status = ApiStatus.ERROR
                throw e
            }
        }
    }

    // get appointment by id
    fun getAppointment(id: String) {
        viewModelScope.launch {
            status = ApiStatus.LOADING
            try {
                val rsp = withContext(Dispatchers.IO) {
                    repo.getAppointment(id)
                }
                val code = rsp.code()

                if(code == 200) {
                    _appointment.value = rsp.body()!!
                    status = ApiStatus.DONE
                }else {
                    val type = object : TypeToken<FormError>() {}.type
                    val it: FormError = gson.fromJson(rsp.errorBody()!!.charStream(), type)
                    _appointment.value = null
                    _error.value = it!!
                    status = ApiStatus.ERROR
                }

            } catch (e: Exception) {
                _appointment.value = null
                status = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun createAppointment(body: Appointment) {
        viewModelScope.launch {
            try {
                AppointmentApi.retrofitService.createAppointment(body)
            } catch (e: Exception) {
                _appointment.value = null // listOf()
                throw e
            }
        }
    }

    fun updateAppointment(id: String, body: Appointment) {
        viewModelScope.launch {
            try {
                AppointmentApi.retrofitService.updateAppointment(id, body)
            } catch (e: Exception) {
                _appointment.value = null // listOf()
                throw e
            }
        }
    }
}