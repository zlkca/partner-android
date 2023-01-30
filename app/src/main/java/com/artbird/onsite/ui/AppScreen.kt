package com.artbird.onsite.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.address.AddressViewModel
import com.artbird.onsite.ui.appointment.AppointmentViewModel
import com.artbird.onsite.ui.auth.AuthViewModel
import com.artbird.onsite.ui.auth.LoginScreen
import com.artbird.onsite.ui.building.*
import com.artbird.onsite.ui.quote.QuoteViewModel
import com.artbird.onsite.ui.role.RoleViewModel
import com.artbird.onsite.ui.window.WindowViewModel
import java.io.File

import androidx.compose.runtime.remember
import com.artbird.onsite.MyBottomAppBar
import com.artbird.onsite.network.ApiService
import com.artbird.onsite.ui.auth.SignupScreen
import com.artbird.onsite.ui.project.ProjectViewModel
import com.artbird.onsite.ui.account.AccountViewModel
import com.artbird.onsite.ui.profile.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(
//    cache: SharedPreferencesHelper,
    accountViewModel: AccountViewModel,
    authViewModel: AuthViewModel,
    addressViewModel: AddressViewModel,
    roleViewModel: RoleViewModel,
    profileViewModel: ProfileViewModel,
    appointmentViewModel: AppointmentViewModel,
    windowViewModel: WindowViewModel,
    buildingViewModel: BuildingViewModel,
    quoteViewModel: QuoteViewModel,
    projectViewModel: ProjectViewModel,
    dir: File,
){
    val navController = rememberNavController()
    var isLoggedIn by remember { mutableStateOf(false) }
    var page by remember { mutableStateOf("login")}
    var appointmentId by remember { mutableStateOf("") }
    var user: Account? by remember { mutableStateOf(null) } // logged in user
    var address by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var appointment by remember { mutableStateOf(
        Appointment()
    )}


    fun handleSelectAppointment(id: String){
        appointmentId = id
    }



    if(isLoggedIn) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                MyBottomAppBar(
                    roleName = user!!.role.name,
                    onClick = {it -> navController.navigate(it.path)}
                ) },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = it.calculateBottomPadding()
                        )
                ) {
                    NaviRoute(
                        scope = scope,
                        snackbarHostState = snackbarHostState,
                        user = user!!,
                        address,
                        navController = navController,
                        authViewModel = authViewModel,
                        accountViewModel = accountViewModel,
                        roleViewModel = roleViewModel,
                        appointmentViewModel = appointmentViewModel,
                        windowViewModel = windowViewModel,
                        buildingViewModel = buildingViewModel,
                        quoteViewModel = quoteViewModel,
                        addressViewModel = addressViewModel,
                        projectViewModel = projectViewModel,
                        profileViewModel = profileViewModel,
                        appointmentId = appointmentId,
                        appointment = appointment,
                        onMeasure = ::handleSelectAppointment, //fix me
//                            onChangeClient = { it ->
//                                clientProfile = it
//                            },
                            onChangeAppointment = { it ->
                                appointment = it
                            },
                            onChangeAddress = {
                              address = it
                            },
                            dir = dir, // LocalContext.current.filesDir,
                            startDestination = "clients",
                        )

                    }
                },

// ------- Use in future ---------
//        floatingActionButton = {
//            if(mode !="edit") {
//                FloatingActionButton(
//                    onClick = {},
//                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
//                ) {
//                    Icon(Icons.Filled.Add, "Add Building")
//                }
//            }
//        }

            )
    }else{
        if(page == "login"){
            LoginScreen(
                authViewModel = authViewModel,
                onAfterSubmit = {
                    isLoggedIn = it.status == "ok"

                    if(it.status == "ok"){
                        ApiService.put("JWT_TOKEN", it.token);
                        user = it.account
                        roleViewModel.getRoles()
                    }
                },
                onPageChange = { page = it }
            )
        }else{
            SignupScreen(
                authViewModel,
                roleViewModel,
                onSubmit = {
                    isLoggedIn = it.status == "ok"
                    ApiService.put("JWT_TOKEN", it.token);
                    user = it.account;
                    roleViewModel.getRoles()
                },
                onPageChange = {page = it}
            )
        }
    }
}