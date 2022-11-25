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
import com.artbird.onsite.ui.account.AccountViewModel
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.DropdownMenuItem
import com.artbird.onsite.ui.components.ListActionBar

@Composable
fun ClientListScreen(
    navController: NavController,
    accountViewModel: AccountViewModel,
    profileViewModel: ProfileViewModel,
    user: Account,
) {
    val accounts by accountViewModel.accounts.observeAsState()
//    val roles: List<Role> by roleViewModel.roles.observeAsState(arrayListOf())

    var selectedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = user) {
        if (user.role.name == "partner") {
            accountViewModel.getAccountsByRecommenderId(user.id)
        }else if(user.role.name == "sales" || user.role.name == "technician"){
            accountViewModel.getAccountsByEmployeeId(user.id, user.role.name)
        }
    }

    fun handleSelectClient(index: Int) {

    }

    fun handleEdit(index: Int){
        selectedIndex = index
        val client = accounts!![index]
        navController.navigate("accounts/${client.id}/form")
    }

    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", ::handleEdit),
    )

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        ListActionBar(items = listOf(
            ActionChip("Client", onClick = {navController.navigate("profiles/new/form")}),
        ))

        if (accounts != null && accounts?.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Account>(
                accounts!!,
                selectedIndex,
                onSelect =  { index ->
                    selectedIndex = index
                    val account = accounts!![index]
                    profileViewModel.getProfileByAccountId(account.id)
                    navController.navigate("clients/${account.id}")
                },
                itemContent = { it, selected, index ->
                    AccountListItem(item=it, selected=selected, index =index)
                }
            )
        }
    }

}



//    fun handleAdd() {
//        if(userId!=null) {
//
//            if(type == "sample") {
//                // create default client
//                accountViewModel.createClientSample(
//                    Client("",
//                        "New Address",
//                        "New client",
//                        appointment = BaseAppointment(userId, appointment?.title!!),
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
//            accountViewModel.getClientsByRecommanderId(userId)
//        }

//    }

//    when (page) {
//        "client-list" -> {
//            ClientListView(
//                accounts,
//                clientIndex,
//                onSelect = { index ->
//                    clientIndex = index
//                    clientId = accounts!![clientIndex].id
//                    toNextView()
//                },
//                onDelete = ::deleteClient,
//                onAdd={
////                    client = ClientDetails(
////                        id= "",
////                        username = "",
////                        email="",
////                        phone="",
////                        userId= userId!!,
////                    )
//                    clientDetails = ClientDetails()
//                    page = "new-client"
//                }
//            )
//        }
//        "new-client" -> {
//            val role = roles.firstOrNull { it.name == "client" }
//            ClientForm("new", clientDetails, role, user, accountViewModel,
//                onCancel = { page = "client-list" },
//                onSubmitSuccess = { page = "client-list" },
//            )
//        }
//        "edit-client" -> {
//            ClientForm(clientId, clientDetails, role =null, user, accountViewModel,
//                onCancel = { page = "client-list" },
//                onSubmitSuccess = { page = "client-list" },
//            )
//        }
//        else -> { // client-details
//            ClientDetailsView(
//                clientId,
//                accountViewModel,
//                onEdit = { c ->
//                    clientDetails = c
//                    page = "edit-client"
//                },
//                onBack = { page = "client-list"},
//            )
//        }
//    }
