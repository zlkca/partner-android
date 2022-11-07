package com.artbird.onsite.repository

import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.network.AppointmentApi

class AppointmentRepository(){
//    var appointment: LiveData<Appointment>;
    suspend fun getAppointmentsByEmployeeId(accountId: String): List<Appointment>{
        return AppointmentApi.retrofitService.getAppointmentsByEmployeeId(accountId)
    }

    suspend fun getAppointment(appointmentId: String): Appointment{
//        withContext(Dispatchers.IO){
            return AppointmentApi.retrofitService.getAppointment(appointmentId)
//        }
    }
}