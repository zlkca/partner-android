package com.artbir

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.client.ClientDetails
import com.artbird.onsite.ui.client.ClientViewModel
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.project.ProjectViewModel
import com.artbird.onsite.ui.utils.getAddressString


@Composable
fun ClientDetailsScreen(
    navController: NavController,
    clientViewModel: ClientViewModel,
    projectViewModel: ProjectViewModel,
    clientId: String,
) {
    val clientDetails by clientViewModel.clientDetails.observeAsState()
//    val projects by projectViewModel.projects.observeAsState()

    var selectedIndex by remember { mutableStateOf(0) }
    var client by remember { mutableStateOf(Client2()) }

    LaunchedEffect(key1 = clientId) {
        if (clientId != null && clientId != "new") {
            clientViewModel.getClientDetails(clientId)
//            projectViewModel.getRecordsByClientId(clientId)
        }
    }

    LaunchedEffect(key1 = clientDetails){
        if(clientDetails != null) {
            client = clientDetails!!
        }
    }

    fun getRecordLabel(item: Project, name: String): String {
        return item.created
    }

    fun handleSelectRecord(index: Int) {
//        selectedIndex = index
//        val record = projects!![index]
//        navController.navigate("clients/${client.id}/projects/${record._id}")
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        DetailsViewActionBar(
            onBack = {navController.navigate("clients")},
            onEdit = { navController.navigate("clients/${client.id}/form") }
        )

        if(client!=null) {
            ClientDetails(client = clientDetails!!)

//            if (projects != null && projects?.isNotEmpty()!!) {
//                com.artbird.onsite.ui.components.List<Project>(
//                    projects!!,
//                    selectedIndex,
//                    fields = listOf("created"),
//                    onGetLabel = ::getRecordLabel,
//                    onSelect = ::handleSelectRecord,
//                    onSelectMenu = { index -> selectedIndex = index },
//                )
//            }
        }
    }

}
