package com.artbird.onsite.ui.client

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.project.ProjectListItem
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getAddressString

@Composable
fun ClientDetails(
    navController: NavController,
    client: Client2,
    projects: List<Project> = listOf()
){
    val account = client.account
    var selectedIndex by remember { mutableStateOf(0) }

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

//        Label3("ADDRESS")
//        Body1(getAddressString(client.address))

        Label3("PROJECTS")
        if(projects.isEmpty()){
            Body1("No address set for the Project yet, please create an appointment")
        }else{
                com.artbird.onsite.ui.components.List<Project>(
                    projects,
                    selectedIndex,
                    onSelect = { index ->
                        val project = projects[index]
                        navController.navigate("clients/${client.id}/projects/${project._id}")
                    },
                    itemContent = { it, selected, index ->
                        ProjectListItem(item=it, selected=selected)
                    }
                )
        }
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
        account = Account("1", username="sydney", email="sydney@shutter.ca", phone="123-456-7890", status="active"),
//        address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7"),
        recommender = Account("2", "partner1"),
        created = "2022-11-08",
    )
    val projects = listOf<Project>(Project("1",
        client = Client2("1", account = Account("1", username="sydney", email="sydney@shutter.ca", phone="123-456-7890", status="active")),
//        address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7"),
        created = "2022-11-08",
        stages = listOf<Stage>(
            Stage("make sales appointment", status="done", start = "2022-11-07", end = "2022-11-08"),
            Stage("send quote", status="done", start = "2022-11-08", end = "2022-11-10"),
            Stage("sign contract", status="in progress", start = "2022-11-12", end = "2022-11-12"),
        )
    ))
    SLTheme {
        ClientDetails(
            rememberNavController(),
            client,
            projects
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
