package com.artbird.onsite.ui.building

//import retrofit2.http.Body

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artbird.onsite.domain.Building
import com.artbird.onsite.domain.Floor
import com.artbird.onsite.domain.Room
import com.artbird.onsite.network.BuildingApi
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE,
    DELETE_BUILDING_DONE, DELETE_BUILDING_ERROR,
    DELETE_FLOOR_DONE, DELETE_FLOOR_ERROR,
    DELETE_ROOM_DONE, DELETE_ROOM_ERROR
}


class BuildingViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _buildingStatus = MutableLiveData<ApiStatus>()
    val buildingStatus: LiveData<ApiStatus> = _buildingStatus

    private val _floorStatus = MutableLiveData<ApiStatus>()
    val floorStatus: LiveData<ApiStatus> = _floorStatus


    private val _buildings = MutableLiveData<List<Building>>(arrayListOf())
    val buildings: LiveData<List<Building>> = _buildings

    private val _building = MutableLiveData<Building>()
    val building: LiveData<Building> = _building


    private val _floor = MutableLiveData<Floor>()
    val floor: LiveData<Floor> = _floor

    private val _room = MutableLiveData<Room>()
    val room: LiveData<Room> = _room

    init {
//        getBuildings()
    }

    private fun getBuildings() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {

                _buildings.value = BuildingApi.retrofitService.getBuildings()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _buildings.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun createBuilding(body: Building) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _buildings.value = BuildingApi.retrofitService.createBuilding(body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _buildings.value = listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun updateBuilding(id: String, body: Building) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.building.ApiStatus.LOADING
            try {
                _building.value = BuildingApi.retrofitService.updateBuilding(id, body)
                _status.value = com.artbird.onsite.ui.building.ApiStatus.DONE
            } catch (e: Exception) {
                _building.value = null // listOf()
                _status.value = com.artbird.onsite.ui.building.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun createBuildingSample(body: Building) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                BuildingApi.retrofitService.createBuildingSample(body)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _building.value = null // listOf()
                _status.value = ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getBuildingsByAppointmentId(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.building.ApiStatus.LOADING
            try {

                _buildings.value = BuildingApi.retrofitService.getBuildingsByAppointmentId(id)
                _status.value = com.artbird.onsite.ui.building.ApiStatus.DONE
            } catch (e: Exception) {
                _buildings.value = listOf()
                _status.value = com.artbird.onsite.ui.building.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun getBuilding(id: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.building.ApiStatus.LOADING
            try {

                _building.value = BuildingApi.retrofitService.getBuilding(id)
                _status.value = com.artbird.onsite.ui.building.ApiStatus.DONE
            } catch (e: Exception) {
                _building.value = null
                _status.value = com.artbird.onsite.ui.building.ApiStatus.ERROR
                throw e
            }
        }
    }

    fun deleteBuilding(id: String, appointmentId: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.building.ApiStatus.LOADING
            try {
                _buildings.value = BuildingApi.retrofitService.deleteBuilding(id, appointmentId)
                _buildingStatus.value = ApiStatus.DELETE_BUILDING_DONE
            } catch (e: Exception) {
                _buildings.value = listOf()
                _buildingStatus.value = ApiStatus.DELETE_BUILDING_ERROR
                throw e
            }
        }
    }



    fun deleteFloor(floorId: String, buildingId: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.building.ApiStatus.LOADING
            try {
                _building.value = BuildingApi.retrofitService.deleteFloor(floorId, buildingId)
                _floorStatus.value = ApiStatus.DELETE_FLOOR_DONE
            } catch (e: Exception) {
                _building.value = null
                _floorStatus.value = ApiStatus.DELETE_FLOOR_ERROR
                throw e
            }
        }
    }


    fun deleteRoom(roomId: String, buildingId: String, floorId: String) {
        viewModelScope.launch {
            _status.value = com.artbird.onsite.ui.building.ApiStatus.LOADING
            try {
                _building.value = BuildingApi.retrofitService.deleteRoom(roomId, buildingId, floorId)
                _status.value = ApiStatus.DELETE_ROOM_DONE
            } catch (e: Exception) {
                _building.value = null
                _status.value = ApiStatus.DELETE_ROOM_ERROR
                throw e
            }
        }
    }
}