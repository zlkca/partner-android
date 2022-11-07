package com.artbird.onsite.ui.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientFormScreen(
    navController: NavController,
    clientViewModel: ClientViewModel,
    clientId: String,
    role: Role?,
    recommender: BaseAccount,
){
    val clientDetails by clientViewModel.clientDetails.observeAsState()

    var accountId by remember { mutableStateOf("") }
    var addressId by remember { mutableStateOf("") }

    var unitNumber by remember { mutableStateOf("") }
    var streetNumber by remember { mutableStateOf("") }
    var streetName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("Toronto") }
    var province by remember { mutableStateOf("ON") }
    var postcode by remember { mutableStateOf("") }

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var firstName by remember { mutableStateOf("") }

    LaunchedEffect(key1 = clientId) {
        if (clientId != null && clientId != "new") {
            clientViewModel.getClientDetails(clientId)
        }
    }

    LaunchedEffect(key1 = clientDetails){
        if(clientDetails != null && clientId != "new") {
            firstName = clientDetails!!.firstName
            accountId = clientDetails!!.account.id
            username = clientDetails!!.account.username
            email = clientDetails!!.account.email
            phone = clientDetails!!.account.phone

            addressId = clientDetails!!.address.id
            unitNumber = clientDetails!!.address.unitNumber
            streetNumber = clientDetails!!.address.streetNumber
            streetName = clientDetails!!.address.streetName
            city = clientDetails!!.address.city
            province = clientDetails!!.address.province
            postcode = clientDetails!!.address.postcode
        }
    }

    val verticalScrollState = rememberScrollState()

    val provinceOptions = listOf(
        OptionItem("ON") { province = it.label },
        OptionItem("QC") { province = it.label },
        OptionItem("BC") { province = it.label },
    )

    fun handleSubmit(){
        val data = ClientDetails(
            firstName = firstName,
            account = AccountDetails(
                id = accountId,
                username = username,
                email = email,
                phone = phone,
                role = role!!,
            ),
            address = Address(
                id = addressId,
                unitNumber = unitNumber,
                streetNumber = streetNumber,
                streetName = streetName,
                city = city,
                province = province,
                postcode = postcode,
            ),
            recommender = recommender,
        )
        if(clientId == "new"){
            clientViewModel.createClient(data)
            clientViewModel.getClientsByRecommenderId(recommender.id)
            navController.navigate("clients")
        }else {
            clientViewModel.updateClient(clientId, data)
            clientViewModel.getClientsByRecommenderId(recommender.id)
            navController.navigate("clients/${clientDetails!!.id}")
        }
    }

    Column(modifier = Modifier
        .padding(12.dp)
        .verticalScroll(verticalScrollState))
    {

        FormActionBar(
            onCancel = {navController.navigate("clients")},
            onSave = ::handleSubmit,
        )
//        Input(
//            value = firstName,
//            onValueChange = { firstName = it },
//            label = "First Name",
//        )
//
//        Input(
//            value = middleName,
//            onValueChange = { middleName = it },
//            label = "Middle Name",
//        )
//
//        Input(
//            value = lastName,
//            onValueChange = { lastName = it },
//            label = "Last Name",
//        )

            Input(
                value = username,
                onValueChange = { username = it },
                label = "Username",
            )

            Input(
                value = email,
                onValueChange = { email = it },
                label = "Email",
            )

            Input(
                value = phone,
                onValueChange = { phone = it },
                label = "Phone",
            )

            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Input(
                    value = unitNumber,
                    onValueChange = { unitNumber = it },
                    label = "Unit Number",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Input(
                    value = streetNumber,
                    onValueChange = { streetNumber = it },
                    label = "Street Number",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
            }

            Input(
                value = streetName,
                onValueChange = { streetName = it },
                label = "Street Name",
            )

            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Input(
                    value = city,
                    onValueChange = { city = it },
                    label = "City",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                Select(
                    value = province,
                    onValueChange = { province = it },
                    label = "Province",
                    options = provinceOptions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 8.dp),
                )
            }

            Input(
                value = postcode,
                onValueChange = { postcode = it },
                label = "Postal Code",
            )
    }
}