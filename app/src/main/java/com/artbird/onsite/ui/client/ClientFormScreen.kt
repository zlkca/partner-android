package com.artbird.onsite.ui.client

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.artbird.onsite.domain.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientFormScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
    clientId: String,
    recommender: Account,
){
    val clientProfile by profileViewModel.profile.observeAsState(Profile())
    var client by remember { mutableStateOf(Profile()) }


//    var accountId by remember { mutableStateOf("") }
//    var addressId by remember { mutableStateOf("") }
//
//    var unitNumber by remember { mutableStateOf("") }
//    var streetNumber by remember { mutableStateOf("") }
//    var streetName by remember { mutableStateOf("") }
//    var city by remember { mutableStateOf("Toronto") }
//    var province by remember { mutableStateOf("ON") }
//    var postcode by remember { mutableStateOf("") }
//
//    var username by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var phone by remember { mutableStateOf("") }
//
//    var firstName by remember { mutableStateOf("") }

//    LaunchedEffect(key1 = clientId) {
//        if (clientId != null && clientId != "new") {
//            profileViewModel.getProfileByAccountId(clientId)
//        }
//    }

    LaunchedEffect(key1 = clientProfile){
        if(clientProfile != null && clientId != "new") {
            client = clientProfile!!
//            firstName = clientProfile!!.firstName
//            accountId = clientProfile!!.account.id
//            username = clientProfile!!.account.username
//            email = clientProfile!!.account.email
//            phone = clientProfile!!.account.phone

//            addressId = clientProfile!!.address.id
//            unitNumber = clientProfile!!.address.unitNumber
//            streetNumber = clientProfile!!.address.streetNumber
//            streetName = clientProfile!!.address.streetName
//            city = clientProfile!!.address.city
//            province = clientProfile!!.address.province
//            postcode = clientProfile!!.address.postcode
        }
    }



    fun handleSubmit(){
        val data = Profile(
            firstName = client.firstName,
            lastName = client.lastName,
            account = Account(
                email = client.account.email,
                phone = client.account.phone,
            ),
            creator = recommender,
        )
        if(clientId == "new"){
            profileViewModel.createClient(data)
//            profileViewModel.getClientsByRecommenderId(recommender.id)
            navController.navigate("clients")
        }else {
            profileViewModel.updateClient(clientId, data)
//            profileViewModel.getClientsByRecommenderId(recommender.id)
            navController.navigate("clients/${clientProfile!!.id}")
        }
    }

    fun handleChange(fieldName: String, value: String){
        when(fieldName){
            "email" -> {
                client = client.copy(account = Account(email = value, phone=client.account.phone))
            }
            "phone" -> {
                client = client.copy( account = Account(email = client.account.email, phone=value))
            }
            "firstName" -> {
                client = client.copy(firstName = value)
            }
            "lastName" -> {
                client = client.copy(lastName = value);
            }
        }
    }

    ClientForm(
        navController = navController,
        firstName = client.firstName,
        lastName = client.lastName,
        account = client.account,
        onChange = ::handleChange,
        onSave = ::handleSubmit
    )


//            Row(
//                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
//            ) {
//                Input(
//                    value = unitNumber,
//                    onValueChange = { unitNumber = it },
//                    label = "Unit Number",
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(end = 8.dp)
//                )
//                Input(
//                    value = streetNumber,
//                    onValueChange = { streetNumber = it },
//                    label = "Street Number",
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(end = 8.dp)
//                )
//            }
//
//            Input(
//                value = streetName,
//                onValueChange = { streetName = it },
//                label = "Street Name",
//            )
//
//            Row(
//                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
//            ) {
//                Input(
//                    value = city,
//                    onValueChange = { city = it },
//                    label = "City",
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(end = 8.dp)
//                )
//
//                Select(
//                    value = province,
//                    onValueChange = { province = it },
//                    label = "Province",
//                    options = provinceOptions,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                        .padding(end = 8.dp),
//                )
//            }
//
//            Input(
//                value = postcode,
//                onValueChange = { postcode = it },
//                label = "Postal Code",
//            )
}