package com.artbird.onsite.repository

import android.util.Log
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Credential
import com.artbird.onsite.domain.PutResponse
import com.artbird.onsite.network.*
import retrofit2.Response

class AccountRepository(){


    suspend fun search(query: Map<String, String> = mapOf<String, String>()): Response<List<Account>> {
        Log.d("zlk", "api /search/accounts")
        return AccountApi.retrofitService.search(query);
    }

    suspend fun getClientsByRecommenderId(recommenderId: String): Response<List<Account>> {
        Log.d("zlk", "api /accounts?recommenderId=${recommenderId}")
        return AccountApi.retrofitService.getClientsByRecommenderId(recommenderId)
    }

    suspend fun getAccountsByEmployeeId(employeeId: String, roleName: String): Response<List<Account>> {
        Log.d("zlk", "api /accounts?employeeId=${employeeId}&roleName=${roleName}")
        return AccountApi.retrofitService.getAccountsByEmployeeId(employeeId, roleName)
    }

    suspend fun changePassword(credential: Credential): Response<Account> {
        return AccountApi.retrofitService.changePassword(credential)
    }
}