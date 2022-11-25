package com.artbird.onsite.repository

import com.artbird.onsite.domain.Account
import com.artbird.onsite.network.AccountApi
import retrofit2.Response

class AccountRepository(){
    suspend fun search(query: Map<String, String> = mapOf<String, String>()): Response<List<Account>> {
        return AccountApi.retrofitService.search(query)
    }

    suspend fun getAccountsByRecommenderId(recommenderId: String): Response<List<Account>> {
        return AccountApi.retrofitService.getAccountsByRecommenderId(recommenderId)
    }

    suspend fun getAccountsByEmployeeId(employeeId: String, roleName: String): Response<List<Account>> {
        return AccountApi.retrofitService.getAccountsByEmployeeId(employeeId, roleName)
    }
}