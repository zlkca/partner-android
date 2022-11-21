package com.artbird.onsite.ui.client

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
//import com.artbird
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.role.RoleViewModel

@Composable
fun ClientScreen(
    recommender: BaseAccount,
    clientViewModel: ProfileViewModel,
    roleViewModel: RoleViewModel,
) {
    val clients by clientViewModel.clients.observeAsState()
    var clientId by remember { mutableStateOf("") }
    val roles: List<Role> by roleViewModel.roles.observeAsState(arrayListOf())

    LaunchedEffect(key1 = recommender) {
        if (recommender.id.isNotEmpty()) {
            clientViewModel.getClientsByRecommenderId(recommender.id)
        }
    }

    var clientDetails by remember { mutableStateOf(Profile()) }
    var page by remember { mutableStateOf("client-list") } // list -> details
    var clientIndex by remember { mutableStateOf(0) }

    fun toNextView(){
        if(page == "client-list"){
            page = "client-details"
        }
    }

    fun toPreviousView(index: Int){
        if(page == "client-details" || page == "new-client" || page == "edit-client") {
            page = "client-list"
        }
    }

    fun deleteClient(id: String){
//        clientViewModel.deleteClient(id)
    }

    fun handleAdd() {
//        if(recommenderId!=null) {
//
//            if(type == "sample") {
//                // create default client
//                clientViewModel.createClientSample(
//                    Client("",
//                        "New Address",
//                        "New client",
//                        appointment = BaseAppointment(recommenderId, appointment?.title!!),
//                        floors = listOf(
//                            Floor("", "First Floor", "", rooms = listOf(
//                                Room("", "Living Room", ""),
//                                Room("", "Family Room", ""),
//                                Room("", "Dinning Room", ""),
//                                Room("", "Kitchen", ""),
//                            )),
//                            Floor("", "Second Floor", "", rooms = listOf(
//                                Room("", "Master Bedroom", ""),
//                                Room("", "Room 1", ""),
//                                Room("", "Room 2", ""),
//                                Room("", "Room 3", ""),
//                                Room("", "Washroom1", ""),
//                                Room("", "Washroom2", ""),
//                            )),
//                        )
//                    ),
//                )
//            }else{
//
//            }
//            clientViewModel.getClientsByRecommanderId(recommenderId)
//        }

    }

    when (page) {
        "client-list" -> {
//            ClientListView(
//                clients,
//                clientIndex,
//                onSelect = { index ->
//                    clientIndex = index
//                    clientId = clients!![clientIndex].id
//                    toNextView()
//                },
//                onDelete = ::deleteClient,
//                onAdd={
////                    client = ClientDetails(
////                        id= "",
////                        username = "",
////                        email="",
////                        phone="",
////                        recommenderId= recommenderId!!,
////                    )
//                    clientDetails = ClientDetails()
//                    page = "new-client"
//                }
//            )
        }
//        "new-client" -> {
//            val role = roles.firstOrNull { it.name == "client" }
//            ClientForm("new", clientDetails, role, recommender, clientViewModel) { page = "client-list" }
//        }
//        "edit-client" -> {
//            ClientForm(clientId, clientDetails, role =null, recommender, clientViewModel) {
//                page = "client-list"
//            }
//        }
        else -> { // client-details
//            ClientDetailsView(
//                clientId,
//                clientViewModel,
//                onEdit = { c ->
//                    clientDetails = c
//                    page = "edit-client"
//                },
//                onBack = { page = "client-list"},
//            )
        }
    }

}