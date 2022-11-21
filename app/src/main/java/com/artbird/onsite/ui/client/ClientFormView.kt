package com.artbird.onsite.ui.client

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientFormOld(
    clientId: String,
    clientDetails: Profile,
//    role: Role?,
//    recommender: BaseAccount,
//    clientViewModel: ClientViewModel,
    onCancel: () -> Unit = {},
    onSubmit: () -> Unit = {},
){
//    val clientDetails by clientViewModel.clientDetails.observeAsState()

    var accountDetails by remember { mutableStateOf(Account2()) }
    var addressDetails by remember { mutableStateOf(Address()) }

//    LaunchedEffect(key1 = clientId) {
//        if (clientId != null && clientId != "new") {
//            clientViewModel.getClientDetails(clientId)
//        }
//    }

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

    LaunchedEffect(key1 = clientDetails){
        if(clientDetails != null && clientId != "new") {
            firstName = clientDetails!!.firstName
            username = clientDetails!!.account.username
            email = clientDetails!!.account.email
            phone = clientDetails!!.account.phone

//            unitNumber = clientDetails!!.address.unitNumber
//            streetNumber = clientDetails!!.address.streetNumber
//            streetName = clientDetails!!.address.streetName
//            city = clientDetails!!.address.city
//            province = clientDetails!!.address.province
//            postcode = clientDetails!!.address.postcode

        }
    }


    val verticalScrollState = rememberScrollState()

    val provinceOptions = listOf(
        OptionItem("ON", {addressDetails.province = it.label}),
        OptionItem("QC", {addressDetails.province = it.label}),
        OptionItem("BC", {addressDetails.province = it.label}),
    )

    fun handleSubmit(){
//        if(clientId == "new"){
//            val account = Account2(
//                "" ,
//                username = username,
//                email = email,
//                phone = phone,
//                role = role!!,
//            )
//            val address = Address(
//                "",
//                unitNumber = unitNumber,
//                streetNumber = streetNumber,
//                streetName = streetName,
//                city = city,
//                province = province,
//                postcode = postcode,
//            )
//            val data = Client2(
//                firstName = firstName,
////                    username = username,
////                    email = email,
////                    phone = phone,
////                    status = "I",
//                    account = account,
//                    address = address,
//                    recommender = recommender,
//            )
//            clientViewModel.createClient(data)
//        }else {
//            val data = Client2(
//                firstName = firstName,
////                    username = username,
////                    email = email,
////                    phone = phone,
////                    status = clientDetails!!.status,
////                    role = clientDetails!!.role,
//                    account = accountDetails,
//                    address = addressDetails,
//                    recommender = recommender,
//            )
//            clientViewModel.updateClient(
//                clientId,
//                data
//            )
//        }
    }

    Column(modifier = Modifier
        .padding(12.dp)
        .verticalScroll(verticalScrollState))
    {

            FormActionBar(
                onCancel = onCancel,
                onSave = ::handleSubmit,
            )

        Input(
            value = firstName,
            onValueChange = { firstName = it },
            label = "First Name",
        )

            Input(
                value = username,
                onValueChange = { username = it },
                label = "Account Name",
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


        OutlinedTextField(
            label = { Text("Address") },
            value = "", // address,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=20.dp)
                .clickable {
                    // onClickAddress()
                },
            placeholder = { Text(text = "Click to type an address") },
            enabled = false
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