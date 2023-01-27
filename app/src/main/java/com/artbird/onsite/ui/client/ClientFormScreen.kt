package com.artbird.onsite.ui.client

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.account.AccountViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientFormScreen(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    navController: NavController,
    accountViewModel: AccountViewModel,
    profileViewModel: ProfileViewModel,
    clientId: String,
    recommender: Account,
    role: Role,
    onSetAccountEmail: (email: String) -> Unit = {email -> }
){
    val clientProfile by profileViewModel.profile.observeAsState(Profile())
    val formError by profileViewModel.error.observeAsState()

    var client by remember { mutableStateOf(Profile()) }
    var error by remember { mutableStateOf(mapOf<String, String>())}

    LaunchedEffect(key1 = profileViewModel.status) {
        if (profileViewModel.status == ApiStatus.CREATE_DONE) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Create client successfully!",
                    duration = SnackbarDuration.Short
                )
                profileViewModel.clearStatus()
            }
        }
        if (profileViewModel.status == ApiStatus.UPDATE_DONE) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Update client successfully!",
                    duration = SnackbarDuration.Short
                )
                profileViewModel.clearStatus()
            }
        }
        if (profileViewModel.status == ApiStatus.DELETE_DONE) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Delete client successfully!",
                    duration = SnackbarDuration.Short
                )
                profileViewModel.clearStatus()
            }
        }
    }

    LaunchedEffect(key1 = formError) {
        if(formError != null) {
            if(formError!!.code != 200){
                error = mapOf(formError!!.field to formError!!.message)
            }
        }
    }
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

    LaunchedEffect(key1 = clientId) {
        if (clientId != null && clientId != "new") {
            profileViewModel.getProfileByAccountId(clientId)
        }
    }

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

    LaunchedEffect(key1 = formError) {
        if(formError != null) {
            if(formError!!.code != 200){
                error = mapOf(formError!!.field to formError!!.message)
            }else{
                profileViewModel.clearError()

                if(clientId == "new") {
                    navController.navigate("clients")
                }else{
                    navController.navigate("clients/${clientId}")
                }
            }
        }
    }

    fun handleSubmit(){
        val data = Profile(
            firstName = client.firstName,
            lastName = client.lastName,
            account = Account(
                username = client.account.username,
                email = client.account.email,
                phone = client.account.phone,
                role= role
            ),
            creator = recommender,
        )
        if(clientId == "new"){
            profileViewModel.createProfile(data)

            if (recommender.role.name == "partner") {
                accountViewModel.getClientsByRecommenderId(recommender.id)
            }else if(recommender.role.name == "sales" || recommender.role.name == "technician"){
                accountViewModel.getAccountsByEmployeeId(recommender.id, recommender.role.name)
            }


        }else {
            profileViewModel.updateProfileByAccountId(clientId, data)
//            profileViewModel.getClientsByRecommenderId(recommender.id)

        }
    }

    fun handleChange(fieldName: String, value: String){
        when(fieldName){
            "email" -> {
                client = client.copy(account = client.account.copy(email = value))
            }
            "username" -> {
                client = client.copy( account = client.account.copy(username=value))
            }
            "phone" -> {
                client = client.copy( account = client.account.copy(phone=value))
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
        firstName = client.firstName,
        lastName = client.lastName,
        account = client.account,
        error = error,
        onChange = ::handleChange,
        onCancel = {
            profileViewModel.clearError()
            navController.navigate("clients")
                   },
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