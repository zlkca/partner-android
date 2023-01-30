package com.artbird.onsite.ui.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.artbird.onsite.ui.account.ApiStatus
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.DropdownMenuItem
import com.artbird.onsite.ui.components.ListActionBar

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import com.artbird.onsite.ui.profile.ProfileViewModel

@Composable
fun ClientListScreen(
    navController: NavController,
    accountViewModel: AccountViewModel,
    profileViewModel: ProfileViewModel,
    user: Account,
) {
    val accounts by accountViewModel.accounts.observeAsState()

    var selectedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = user) {
        if (user.role.name == "partner") {
            accountViewModel.getClientsByRecommenderId(user.id)
        }else if(user.role.name == "sales" || user.role.name == "technician"){
            accountViewModel.getAccountsByEmployeeId(user.id, user.role.name)
        }
    }

    fun handleEdit(index: Int){
        selectedIndex = index
        val client = accounts!![index]
        navController.navigate("accounts/${client.id}/form")
    }

    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("edit", "Edit", Icons.Outlined.Edit, "Edit", ::handleEdit),
    )

    if (accountViewModel.status == ApiStatus.LOADING){
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
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ListActionBar(items = listOf(
                ActionChip("Client", onClick = { navController.navigate("clients/new/form") }),
            ))

            if (accounts != null && accounts?.isNotEmpty()!!) {
                com.artbird.onsite.ui.components.List<Account>(
                    accounts!!,
                    selectedIndex,
                    onSelect = { index ->
                        selectedIndex = index
                        val account = accounts!![index]
                        profileViewModel.getProfileByAccountId(account.id)
                        navController.navigate("clients/${account.id}")
                    },
                    itemContent = { it, selected, index ->
                        AccountListItem(item = it, selected = selected, index = index)
                    }
                )
            }
        }
    }
}

