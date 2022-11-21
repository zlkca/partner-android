package com.artbird.onsite.repository

import com.artbird.onsite.domain.Profile
import com.artbird.onsite.network.ProfileApi
import retrofit2.Response

class ProfileRepository(){
    suspend fun getProfileByAccountId(accountId: String): Response<Profile> {
        return ProfileApi.retrofitService.getProfileByAccountId(accountId)
    }
}