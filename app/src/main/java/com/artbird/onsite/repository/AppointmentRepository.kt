package com.artbird.onsite.repository

import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.network.AppointmentApi
import retrofit2.Response

class AppointmentRepository(){
    suspend fun getAppointmentsByEmployeeId(accountId: String): Response<List<Appointment>> {
        return AppointmentApi.retrofitService.getAppointmentsByEmployeeId(accountId)
    }

    suspend fun getAppointment(appointmentId: String): Response<Appointment>{
        return AppointmentApi.retrofitService.getAppointment(appointmentId)
    }

//    suspend fun getAppointment(appointmentId: String): Response<Appointment>{
//        return AppointmentApi.retrofitService.getAppointment(appointmentId)
//    }
//
//    suspend fun getAppointment(appointmentId: String): Response<Appointment>{
//        return AppointmentApi.retrofitService.getAppointment(appointmentId)
//    }
}