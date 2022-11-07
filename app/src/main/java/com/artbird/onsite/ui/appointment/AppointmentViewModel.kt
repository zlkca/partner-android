package com.artbird.onsite.ui.appointment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.network.AppointmentApi
import com.artbird.onsite.repository.AppointmentRepository
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class AppointmentViewModel : ViewModel() {
    private val appointmentRepo = AppointmentRepository()

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status
    
    private val _appointments = MutableLiveData<List<Appointment>>(arrayListOf())
    val appointments: LiveData<List<Appointment>> = _appointments

    private val _appointment = MutableLiveData<Appointment>()
    val appointment: LiveData<Appointment> = _appointment

//    init {
//        getAppointments()
//    }

    fun getAppointmentsByEmployeeId(employeeId: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _appointments.value = appointmentRepo.getAppointmentsByEmployeeId(employeeId)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _appointments.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    // get appointment by id
    fun getAppointment(id: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _appointment.value = appointmentRepo.getAppointment(id)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _appointment.value = null
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun createAppointment(body: Appointment) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                AppointmentApi.retrofitService.createAppointment(body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _appointment.value = null // listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun updateAppointment(id: String, body: Appointment) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                AppointmentApi.retrofitService.updateAppointment(id, body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _appointment.value = null // listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }
}