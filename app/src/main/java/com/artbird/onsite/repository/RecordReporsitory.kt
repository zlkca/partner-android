package com.artbird.onsite.repository

import com.artbird.onsite.domain.Project
import com.artbird.onsite.network.RecordApi
import retrofit2.Response

class RecordRepository(){
    suspend fun getRecordsByClientId(clientId:String): Response<List<Project>> {
        return RecordApi.retrofitService.getRecordsByClientId(clientId)
    }

    suspend fun getRecord(recordId:String): Response<Project> {
        return RecordApi.retrofitService.getRecord(recordId)
    }
}