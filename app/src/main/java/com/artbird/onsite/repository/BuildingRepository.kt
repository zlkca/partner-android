package com.artbird.onsite.repository

import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.SampleBuildingReqBody
import com.artbird.onsite.network.BuildingApi
import retrofit2.Response

class BuildingRepository(){
//    suspend fun getBuildingByAccountId(accountId: String): Response<Building> {
//        return BuildingApi.retrofitService.getBuildingByAccountId(accountId)
//    }

    suspend fun createSampleBuilding(req: SampleBuildingReqBody): Response<Building> {
        return BuildingApi.retrofitService.createSampleBuilding(req)
    }

}