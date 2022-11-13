package com.artbird.onsite.ui.client

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getAddressString

@Composable
fun ClientDetails(client: Client2){
    val account = client.account

    Column(
        modifier = Modifier.background(color= MaterialTheme.colorScheme.background)
    ){
        Label3("ACCOUNT")

        Label3("USERNAME")
        Body1(account.username)

        Label3("EMAIL")
        Body1(account.email)

        Label3("PHONE NUMBER")
        Body1(account.phone)

        Label3("STATUS")
        Body1(account.status)


        Label3("INFO")

        Label3("FIRSTNAME")
        Body1(client.firstName)

        Label3("LASTNAME")
        Body1(client.lastName)

        Label3("ADDRESS")
//        Body1(getAddressString(client.address))
//        Label3("PROGRESS")
//        StageList(stages = client.stages, selectedIndex = -1) // do not select any row
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewClientDetails(){
    val client = Client2(
        id = "1",
        firstName = "Sydney",
        lastName = "Winston",
        account = Account("1", username="sydney", email="sydney@shutterlux.ca", phone="123-456-7890", status="active"),
//        address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7"),
        recommender = Account("2", "partner1"),
        created = "2022-11-08",
    )

    SLTheme {
        ClientDetails(
            client
        )
    }
}

//
//@Composable
//fun Portrait(
//    firstName: String,
//    lastName: String,
//){
//    Row(){
//        Text(text = "$firstName $lastName")
//    }
//}
//
//@Composable
//fun ContactInfo(
//    account: Account,
//    address: Address,
//    notes: String = ""
//){
//    Column(){
//        Text("USERNAME")
//        Text(text = "${account.username}", modifier = Modifier.padding(8.dp))
//
//        Text("EMAIL")
//        Text(text = "${account.email}", modifier = Modifier.padding(8.dp))
//
//        Text("PHONE")
//        Text(text = "${account.phone}", modifier = Modifier.padding(8.dp))
//
//        Text("ADDRESS")
//        Text(text = getAddressString(address), modifier = Modifier.padding(8.dp))
//
//    }
//}
//
//
//
//@Composable
//fun ClientDetails(
//    client: ClientDetails,
//
//) {
////    fun getRecordLabel(item: Record, name: String): String {
////        return item.created
////    }
////
////    fun handleSelectRecord(index: Int) {
////        selectedIndex = index
////        val record = records!![index]
////        navController.navigate("clients/${client.id}/records/${record._id}")
////    }
//
//    Column(
//        modifier = Modifier
//            .padding(8.dp)
//    ) {
//
////        DetailsViewActionBar(
////            onBack = {navController.navigate("clients")},
////            onEdit = { navController.navigate("clients/${client.id}/form") }
////        )
//
////        if(client!=null) {
////            ContactInfo(client!!.account)
////
////            Text("PROGRESS")
////            if (records != null && records?.isNotEmpty()!!) {
////                com.artbird.onsite.ui.components.List<Record>(
////                    records!!,
////                    selectedIndex,
////                    fields = listOf("created"),
////                    onGetLabel = ::getRecordLabel,
////                    onSelect = ::handleSelectRecord,
////                    onSelectMenu = { index -> selectedIndex = index },
////                )
////            }
////        }
//    }
//
//}
