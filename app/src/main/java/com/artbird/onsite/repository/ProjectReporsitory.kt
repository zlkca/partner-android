package com.artbird.onsite.repository

import com.artbird.onsite.domain.Project
import com.artbird.onsite.network.RecordApi
import retrofit2.Response

class ProjectRepository(){
    suspend fun getProjectsByClientId(clientId:String): Response<List<Project>> {
        return RecordApi.retrofitService.getProjectsByClientId(clientId)
    }

    suspend fun getProjectsByRecommenderId(clientId:String): Response<List<Project>> {
        return RecordApi.retrofitService.getProjectsByRecommenderId(clientId)
    }

    suspend fun getRecord(recordId:String): Response<Project> {
        return RecordApi.retrofitService.getRecord(recordId)
    }
}