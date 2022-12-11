package com.artbird.onsite.repository

import com.artbird.onsite.domain.*
import com.artbird.onsite.network.AuthApi
import retrofit2.Response

class AuthRepository(){
    suspend fun login(credential: Credential): Response<Auth> {
        return AuthApi.retrofitService.login(credential)
    }

    suspend fun signup(account: Account): Response<Auth> {
        return AuthApi.retrofitService.signup(account)
    }

    suspend fun changePassword(credential: Credential): Response<PutResponse> {
        return AuthApi.retrofitService.changePassword(credential)
    }
}