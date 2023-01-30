package com.artbird.onsite.ui.client

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Profile

import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.profile.ProfileViewModel
import com.artbird.onsite.ui.project.ProjectList
import com.artbird.onsite.ui.project.ProjectViewModel
import com.artbird.onsite.ui.project.ApiStatus

@Composable
fun ClientProjectListScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
    projectViewModel: ProjectViewModel,
    clientAccountId: String,
) {
    val clientProfile by profileViewModel.profile.observeAsState()
    val projects by projectViewModel.projects.observeAsState()

    var client by remember { mutableStateOf(Profile()) }
    var selectedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = clientAccountId){
        if(clientAccountId != null) {
            projectViewModel.getProjectsByClientId(clientAccountId)
        }
    }

    LaunchedEffect(key1 = clientProfile){
        if(clientProfile != null) {
            client = clientProfile!!

        }
    }


    if (projectViewModel.status == ApiStatus.LOADING){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator()
        }
    }else {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        DetailsViewActionBar(
            onBack = {
                Log.d("zlk", "Back from project list to client detail: clients/${clientAccountId}")
                navController.navigate("clients/${clientAccountId}") },
            readOnly = true
        )


        if(client!=null && client.account!=null) {
            Label3("USERNAME")
            Body1(client.account.username)

            Label3("EMAIL")
            Body1(client.account.email)

            Label3("PHONE NUMBER")
            Body1(client.account.phone)
        }

                if(projects!!.isEmpty()){
            Body1("No address found, please create an appointment")
        }else {

                    ProjectList(
                        projects = projects!!,
                        selectedIndex,
                        onSelect = { index ->
                            val project = projects!![index]
                            navController.navigate("clients/${clientAccountId}/projects/${project._id}")
                        },
                    )
                }
        }
    }

}
