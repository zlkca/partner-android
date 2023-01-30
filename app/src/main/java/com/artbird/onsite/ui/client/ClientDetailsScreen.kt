package com.artbird.onsite.ui.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.*

import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.profile.ApiStatus
import com.artbird.onsite.ui.profile.ProfileViewModel


@Composable
fun ClientDetailsScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
    clientId: String,
) {
    val clientProfile by profileViewModel.profile.observeAsState(Profile())

    var client by remember { mutableStateOf(Profile()) }

    LaunchedEffect(key1 = clientId) {
        if (clientId != null && clientId != "new") {
            profileViewModel.getProfileByAccountId(clientId)
        }
    }

    LaunchedEffect(key1 = clientProfile){
        if(clientProfile != null && clientProfile!!.id.isNotEmpty()) {
            client = clientProfile!!
        }
    }

    if (profileViewModel.status == ApiStatus.LOADING){
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
                .verticalScroll(rememberScrollState())
        ) {
            DetailsViewActionBar(
                onBack = { navController.navigate("clients") },
                onEdit = { navController.navigate("clients/${clientId}/form") }
            )
            ClientDetails(
                navController,
                client!!,
            )
        }
    }
}
