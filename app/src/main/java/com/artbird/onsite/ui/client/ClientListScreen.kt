package com.artbird.onsite.ui.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.DropdownMenuItem
import com.artbird.onsite.ui.components.ListActionBar

@Composable
fun ClientListScreen(
    navController: NavController,
    clientViewModel: ClientViewModel,
//    roleViewModel: RoleViewModel,
    recommender: BaseAccount,
) {
    val clients by clientViewModel.clients.observeAsState()
//    val roles: List<Role> by roleViewModel.roles.observeAsState(arrayListOf())

    var selectedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = recommender) {
        if (recommender.id.isNotEmpty()) {
            clientViewModel.getClientsByRecommenderId(recommender.id)
        }
    }

    fun getClientLabel(item: Client, name: String): String {
        return item.username
    }

    fun handleSelectClient(index: Int) {
        selectedIndex = index
        val client = clients!![index]
        navController.navigate("clients/${client.id}")
    }

    fun handleEdit(index: Int){
        selectedIndex = index
        val client = clients!![index]
        navController.navigate("clients/${client.id}/form")
    }

    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", ::handleEdit),
    )

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        ListActionBar(items = listOf(
            ActionChip("Client", onClick = {navController.navigate("clients/new/form")}),
        ))

        if (clients != null && clients?.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Client>(
                clients!!,
                selectedIndex,
                fields = listOf("name"),
                onGetLabel = ::getClientLabel,
                onSelect = ::handleSelectClient,
                onSelectMenu = { index -> selectedIndex = index },
                menus = menus
            )
        }
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

//    when (page) {
//        "client-list" -> {
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
//        }
//        "new-client" -> {
//            val role = roles.firstOrNull { it.name == "client" }
//            ClientForm("new", clientDetails, role, recommender, clientViewModel,
//                onCancel = { page = "client-list" },
//                onSubmitSuccess = { page = "client-list" },
//            )
//        }
//        "edit-client" -> {
//            ClientForm(clientId, clientDetails, role =null, recommender, clientViewModel,
//                onCancel = { page = "client-list" },
//                onSubmitSuccess = { page = "client-list" },
//            )
//        }
//        else -> { // client-details
//            ClientDetailsView(
//                clientId,
//                clientViewModel,
//                onEdit = { c ->
//                    clientDetails = c
//                    page = "edit-client"
//                },
//                onBack = { page = "client-list"},
//            )
//        }
//    }

}