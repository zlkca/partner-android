package com.artbird.onsite.ui.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Profile
import com.artbird.onsite.ui.client.ProfileViewModel

@Composable
fun ProjectFormScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
//    addressViewModel: AddressViewModel,
    projectId: String,
    address: String,
) {
//    val project by addressViewModel.project.observeAsState()
//
//    LaunchedEffect(key1 = projectId) {
//        if (projectId != null && projectId != "new") {
//            addressViewModel.getProject(projectId)
//        }
//    }
//    var keyword by remember { mutableStateOf("") }
    val clientDetails: Profile by profileViewModel.profile.observeAsState(Profile())
//    val placeRsp by addressViewModel.placeRsp.observeAsState()
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        ProjectForm(
            clientDetails.account.username,
            address,
            onClickClient = { navController.navigate("clients/search") },
            onClickAddress = { navController.navigate("address/autocomplete") },
            onCancel = {navController.navigate("projects")},
            onSave = {

            }
        )
//        DetailsViewActionBar(
//            onBack = { navController.navigate("projects") },
//            readOnly = true
//        )
//
//        if(project!=null) {
//            ProjectDetails(project!!)
//        }
    }
}