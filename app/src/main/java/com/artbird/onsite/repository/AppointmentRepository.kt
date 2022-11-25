package com.artbird.onsite.repository

import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.domain.Appointment2
import com.artbird.onsite.network.AppointmentApi

class AppointmentRepository(){
//    var appointment: LiveData<Appointment>;
    suspend fun getAppointmentsByEmployeeId(accountId: String): List<Appointment2>{
        return AppointmentApi.retrofitService.getAppointmentsByEmployeeId(accountId)
    }

    suspend fun getAppointment(appointmentId: String): Appointment2{
//        withContext(Dispatchers.IO){
            return AppointmentApi.retrofitService.getAppointment(appointmentId)
//        }
    }
}