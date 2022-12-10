package com.artbird.onsite.repository

import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Profile
import com.artbird.onsite.network.ProfileApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

class ProfileRepository(){
    suspend fun getProfileByAccountId(accountId: String): Response<Profile> {
        return ProfileApi.retrofitService.getProfileByAccountId(accountId)
    }

    suspend fun createProfile(profile: Profile): Response<Profile> {
        return ProfileApi.retrofitService.createProfile(profile)
    }

    suspend fun updateProfileByAccountId(accountId: String, profile: Profile): Response<Profile> {
        return ProfileApi.retrofitService.updateProfileByAccountId(accountId, profile)
    }
}