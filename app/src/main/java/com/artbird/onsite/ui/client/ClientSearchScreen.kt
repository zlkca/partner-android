@file:OptIn(ExperimentalMaterial3Api::class)

package com.artbird.onsite.ui.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.components.SearchActionBar

@Composable
fun ClientSearchScreen(
    navController: NavController,
    clientViewModel: ClientViewModel,
//    appointmentId: String,
    user: Account,
//    onSelect: (d: Client2) -> Unit = {},
){
    val clients: List<Client> by clientViewModel.clients.observeAsState(arrayListOf())
    val clientDetails: Client2 by clientViewModel.clientDetails.observeAsState(Client2())
    var keyword by remember { mutableStateOf("") }
//    var needRedirect by remember { mutableStateOf(false) }
    var client by remember { mutableStateOf(Client()) }
    var selectedIndex by remember { mutableStateOf(0) }

//    LaunchedEffect(key1 = keyword) {
//        if (keyword != null && keyword.isNotEmpty()) {
//            clientViewModel.searchByRecommender(user.id, keyword)
//        }
//    }

//    LaunchedEffect(key1 = client) {
//        if (client != null && client.id != "") {
//            clientViewModel.getClientDetails(client.id)
//        }
//    }

    LaunchedEffect(key1 = clientDetails) {
        if (clientDetails != null && clientDetails!!.id != "") {
            keyword = clientDetails.account.username
//            onSelect(clientDetails!!)
//            if(needRedirect){
//                navController.navigate("appointments/$appointmentId/form")
//            }
        }
    }

    Column(modifier = Modifier.padding(8.dp)) {

        ClientSearch(
            keyword,
            clients,
            onSearch = { it ->
                keyword = it
                if(keyword.length >= 3) {
                    clientViewModel.searchByRecommender(user.id, keyword)
                }
            },
            onSelect = { index ->
                val client = clients[index] // update client by useEffect
                clientViewModel.getClientDetails(client.id)
                navController.navigate("projects/new/form")
            },
            onBack = {
                navController.navigate("projects/new/form")
            },
            onClear = {
                keyword = ""
            }
        )
    }
}
