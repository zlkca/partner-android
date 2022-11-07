package com.artbird.onsite.ui.client




import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.content.res.Configuration

import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.components.SearchActionBar
@Composable
fun SearchClient(
    clients: List<Client>,
    onSelect: (d: ClientDetails) -> Unit = {},
){
    var client by remember { mutableStateOf(
        Client(
            id= "",
            username = "",
            email = "",
            phone = "",
            status = "",
            firstName = "",
            middleName = "",
            lastName = "",
            accountId = "",
            addressId = "",
            recommenderId = "",
            created = "",
            updated = ""
        )
    ) }

//    val clients: List<Client> by clientViewModel.clients.observeAsState(arrayListOf())
//    val clientDetails by clientViewModel.clientDetails.observeAsState()

    var needRedirect by remember { mutableStateOf(false) }
    var keyword by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableStateOf(0) }

//    LaunchedEffect(key1 = keyword) {
//        if (keyword != null && keyword.isNotEmpty()) {
//            clientViewModel.searchByRecommender(user.id, keyword)
//        }
//    }
//
//    LaunchedEffect(key1 = client) {
//        if (client != null && client.id != "") {
//            clientViewModel.getClientDetails(client.id)
//        }
//    }
//
//    LaunchedEffect(key1 = clientDetails) {
//        if (clientDetails != null && clientDetails!!.id != "") {
//            onSelect(clientDetails!!)
//            if(needRedirect){
//                navController.navigate("appointments/$appointmentId/form")
//            }
//        }
//    }

    fun getClientLabel(c:Client, name: String): String {
        return when (name) {
            "username" -> {
                c.username
            }
            "phone" -> {
                c.phone
            }
            else -> {
                "Unknown Field"
            }
        }
    }

    Column(modifier = Modifier.padding(8.dp)) {

        SearchActionBar(
            onCancel = {
//                if(appointmentId == "new"){
//                    onSelect(
//                        ClientDetails(
//                            account = AccountDetails(),
//                            address = Address(),
//                            recommender = BaseAccount(),
//                        )
//                    )
//                }
//                navController.navigate("appointments/$appointmentId/form")
            }
        )

        Input(
            value = keyword,
            onValueChange = {
                keyword = it
            },
            label = "Client",
        )

        if (clients.isNotEmpty()) {

        Column(modifier = Modifier.padding(8.dp)) {
            com.artbird.onsite.ui.components.List<Client>(
                clients,
                selectedIndex,
                fields = listOf("username", "phone"),
                onGetLabel = ::getClientLabel,
                onSelect = { index ->
                    client = clients[index] // update client by useEffect
                    needRedirect = true
                },
            )
        }
    }

    }
}


@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewClientSearch(){
    val clients = listOf<Client>(
        Client(id="1", username = "Jacky", email="jacky@gmail.com"),
        Client(id="2", username = "Sydney", email="sydney@gmail.com")
    )
    SLTheme {
        SearchClient(
            clients
        )
    }
}