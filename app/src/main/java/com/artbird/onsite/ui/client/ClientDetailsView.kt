package com.artbir

import com.artbird.onsite.ui.client.ClientViewModel


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.AccountDetails
import com.artbird.onsite.domain.Address
import com.artbird.onsite.domain.Client
import com.artbird.onsite.domain.ClientDetails
import com.artbird.onsite.ui.components.*


@Composable
fun ClientDetailsView(
    clientId: String,
    clientViewModel: ClientViewModel,
    onEdit: (client: ClientDetails) -> Unit,
    onBack: () -> Unit,
) {
    val client by clientViewModel.clientDetails.observeAsState()

//    var account by remember { mutableStateOf(AccountDetails()) }
//    var address by remember { mutableStateOf(Address()) }

    LaunchedEffect(key1 = clientId) {
        if (clientId != null) {
            clientViewModel.getClientDetails(clientId)
        }
    }

//    LaunchedEffect(key1 = client){
//        if(client != null) {
//            account = client!!.account
//            address = client!!.address
//        }
//    }

    fun getAddressString(address: Address): String {
        return "${address.unitNumber} ${address.streetNumber} ${address.streetName}, ${address.city} ${address.province} ${address.postcode}"
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        DetailsViewActionBar(
            onBack = onBack,
            onEdit = { onEdit(client!!) }
        )

        if(client!=null) {
            Text(text = "${client!!.account.username}", modifier = Modifier.padding(8.dp))

            Text(text = "${client!!.account.email}", modifier = Modifier.padding(8.dp))

            Text(text = "${client!!.account.phone}", modifier = Modifier.padding(8.dp))

            Text(text = getAddressString(client!!.address), modifier = Modifier.padding(8.dp))
        }
    }

}
