package com.artbird.onsite.repository

import com.artbird.onsite.domain.Window
import com.artbird.onsite.network.WindowApi
import retrofit2.Response

class WindowRepository(){
    suspend fun getWindowsByRoomId(roomId: String): Response<List<Window>> {
        return WindowApi.retrofitService.getWindowsByRoomId(roomId)
    }

    suspend fun getWindow(windowId: String): Response<Window> {
        return WindowApi.retrofitService.getWindow(windowId)
    }
//    suspend fun createSampleWindow(req: SampleWindowReqBody): Response<Window> {
//        return WindowApi.retrofitService.createSampleWindow(req)
//    }

}