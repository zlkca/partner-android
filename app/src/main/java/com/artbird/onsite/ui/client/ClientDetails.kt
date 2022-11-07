package com.artbird.onsite.ui.client

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.utils.getAddressString

@Composable
fun Portrait(
    firstName: String,
    lastName: String,
){
    Row(){
        Text(text = "$firstName $lastName")
    }
}

@Composable
fun ContactInfo(
    account: Account,
    address: Address,
    notes: String = ""
){
    Column(){
        Text("USERNAME")
        Text(text = "${account.username}", modifier = Modifier.padding(8.dp))

        Text("EMAIL")
        Text(text = "${account.email}", modifier = Modifier.padding(8.dp))

        Text("PHONE")
        Text(text = "${account.phone}", modifier = Modifier.padding(8.dp))

        Text("ADDRESS")
        Text(text = getAddressString(address), modifier = Modifier.padding(8.dp))

    }
}



@Composable
fun ClientDetails(
    client: ClientDetails,

) {
//    fun getRecordLabel(item: Record, name: String): String {
//        return item.created
//    }
//
//    fun handleSelectRecord(index: Int) {
//        selectedIndex = index
//        val record = records!![index]
//        navController.navigate("clients/${client.id}/records/${record._id}")
//    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

//        DetailsViewActionBar(
//            onBack = {navController.navigate("clients")},
//            onEdit = { navController.navigate("clients/${client.id}/form") }
//        )

//        if(client!=null) {
//            ContactInfo(client!!.account)
//
//            Text("PROGRESS")
//            if (records != null && records?.isNotEmpty()!!) {
//                com.artbird.onsite.ui.components.List<Record>(
//                    records!!,
//                    selectedIndex,
//                    fields = listOf("created"),
//                    onGetLabel = ::getRecordLabel,
//                    onSelect = ::handleSelectRecord,
//                    onSelectMenu = { index -> selectedIndex = index },
//                )
//            }
//        }
    }

}
