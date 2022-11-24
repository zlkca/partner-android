package com.artbir

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.client.ClientDetails
import com.artbird.onsite.ui.client.ProfileViewModel
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.project.ProjectViewModel


@Composable
fun ClientDetailsScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
    projectViewModel: ProjectViewModel,
    clientId: String,
) {
    val clientProfile by profileViewModel.profile.observeAsState(Profile())
    val projects by projectViewModel.projects.observeAsState()

    var selectedIndex by remember { mutableStateOf(0) }
    var client by remember { mutableStateOf(Profile()) }

//    LaunchedEffect(key1 = clientId) {
//        if (clientId != null && clientId != "new") {
//            profileViewModel.getProfileByAccountId(clientId)
//            projectViewModel.getProjectsByClientId(clientId)
//        }
//    }

//    LaunchedEffect(key1 = clientProfile){
//        if(clientProfile != null) {
//            client = clientProfile!!
//        }
//    }

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
            onEdit = {navController.navigate("clients/${clientId}/form")}
        )
        ClientDetails(
            navController,
            clientProfile!!,
            projects!!
        )
//        DetailsViewActionBar(
//            onBack = {navController.navigate("clients")},
//            onEdit = { navController.navigate("clients/${clientProfile!!.id}/form") }
//        )
//
//        if(clientProfile!=null) {
//            ClientDetails(
//                navController,
//                client = clientProfile!!,
//                projects = projects!!
//            )
//
////            if (projects != null && projects?.isNotEmpty()!!) {
////                com.artbird.onsite.ui.components.List<Project>(
////                    projects!!,
////                    selectedIndex,
////                    fields = listOf("created"),
////                    onGetLabel = ::getRecordLabel,
////                    onSelect = ::handleSelectRecord,
////                    onSelectMenu = { index -> selectedIndex = index },
////                )
////            }
//        }
    }

}
