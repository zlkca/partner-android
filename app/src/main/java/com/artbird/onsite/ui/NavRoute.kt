package com.artbird.onsite.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.artbir.ClientDetailsScreen
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.domain.Role
import com.artbird.onsite.ui.account.AccountViewModel
import com.artbird.onsite.ui.address.AddressAutocompleteScreen
import com.artbird.onsite.ui.address.AddressViewModel
import com.artbird.onsite.ui.appointment.AppointmentDetailsScreen
import com.artbird.onsite.ui.appointment.AppointmentFormScreen
import com.artbird.onsite.ui.appointment.AppointmentListScreen
import com.artbird.onsite.ui.appointment.AppointmentViewModel
import com.artbird.onsite.ui.auth.AuthViewModel
import com.artbird.onsite.ui.building.*
import com.artbird.onsite.ui.client.*
import com.artbird.onsite.ui.project.ProjectDetailsScreen
import com.artbird.onsite.ui.project.ProjectFormScreen
import com.artbird.onsite.ui.project.ProjectListScreen
import com.artbird.onsite.ui.project.ProjectViewModel
import com.artbird.onsite.ui.quote.QuoteDetailsScreen
import com.artbird.onsite.ui.quote.QuoteViewModel
import com.artbird.onsite.ui.role.RoleViewModel
import com.artbird.onsite.ui.settings.ChangePasswordScreen
import com.artbird.onsite.ui.settings.SettingsScreen
import com.artbird.onsite.ui.window.WindowViewModel
import kotlinx.coroutines.CoroutineScope
import java.io.File


@Composable
fun NaviRoute(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    user: Account,
    address: String,
    authViewModel: AuthViewModel,
    accountViewModel: AccountViewModel,
    navController: NavController,
    roleViewModel: RoleViewModel,
    appointmentViewModel: AppointmentViewModel,
    windowViewModel: WindowViewModel,
    buildingViewModel: BuildingViewModel,
    quoteViewModel: QuoteViewModel,
    addressViewModel: AddressViewModel,
    projectViewModel: ProjectViewModel,
    profileViewModel: ProfileViewModel,
    appointmentId: String,
    appointment: Appointment,
    onMeasure: (appointmentId: String) -> Unit,
//    onChangeClient: (client: Profile) -> Unit,
    onChangeAppointment:(appointment: Appointment) -> Unit,
    onChangeAddress: (address: String) -> Unit,
    dir: File? = LocalContext.current.filesDir,
    startDestination: String,
//    clientProfile: Profile,
){
    val roles: List<Role> by roleViewModel.roles.observeAsState(arrayListOf())
    var highlightedClient by remember { mutableStateOf(null) }

    NavHost(
        navController = navController as NavHostController,
        startDestination = startDestination,
    ) {
        composable(route = "clients") {
            if (user != null) {
                ClientListScreen(
                    navController,
                    accountViewModel,
                    profileViewModel,
                    user,
                )
            }
        }

        composable(route = "clients/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ){
            if (user != null) {
                ClientDetailsScreen(
                    navController,
                    profileViewModel,
                    projectViewModel,
                    clientId = it.arguments?.getString("id")!!,
                )
            }
        }

        composable(route = "clients/{clientId}/projects/{projectId}",
            arguments = listOf(
                navArgument("clientId") {
                    type = NavType.StringType
                },
                navArgument("projectId") {
                    type = NavType.StringType
                },
            ),
        )
        {
            ClientProjectDetailsScreen(
                navController = navController,
                projectViewModel = projectViewModel,
                clientId = it.arguments?.getString("clientId")!!,
                projectId = it.arguments?.getString("projectId")!!,
            )
        }

        composable(route = "clients/{id}/form",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ){
            if (user != null) {
                val role = roles!!.find { it.name == "client"}
                ClientFormScreen(
                    scope = scope,
                    snackbarHostState = snackbarHostState,
                    navController,
                    accountViewModel,
                    profileViewModel,
                    clientId = it.arguments?.getString("id")!!,
                    recommender = user,
                    role = role!!
                )
            }
        }

        composable(route = "search/client"){
            ClientSearchScreen(
                navController = navController,
                accountViewModel = accountViewModel,
                profileViewModel = profileViewModel,
                roles=roles,
                user=user,
            )
        }

        composable(route = "appointments") {
            if (user != null) {
                AppointmentListScreen(
                    navController=navController,
                    appointmentViewModel=appointmentViewModel,
                    employee = user,
                    onSelectAppointment = onChangeAppointment
                )
            }
        }

        composable(route = "appointments/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        )
        {
            if (user != null) {
                AppointmentDetailsScreen(
                    navController = navController,
                    appointmentId = it.arguments?.getString("id")!!,
                    appointmentViewModel = appointmentViewModel,
                    onSelectAppointment = onChangeAppointment,
                )
            }
        }

        composable(route = "appointments/{id}/form",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        )
        {
            if (user != null) {
                AppointmentFormScreen(
                    navController = navController,
                    appointmentId = it.arguments?.getString("id"),
                    appointmentViewModel = appointmentViewModel,
                    profileViewModel=profileViewModel,
                    user,
                )
            }
        }


        composable(route = "quotes"){
//            QuoteListScreen(
//                navController,
//                quoteViewModel,
//                appointment,
//                client
//            )
        }

        composable(route = "quotes/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ){
            if (dir != null) {
                QuoteDetailsScreen(
                    dir,
                    navController,
                    it.arguments?.getString("id"),
                    quoteViewModel,
                )
            }
        }

        composable(route = "appointments/{id}/quote",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ){
            if (dir != null) {
                QuoteDetailsScreen(
                    dir,
                    //                context,
                    navController,
                    it.arguments?.getString("id"),
                    quoteViewModel
                )
            }
        }

        composable(route = "settings") {
            SettingsScreen(navController)
        }

        composable(route = "change-password") {
            ChangePasswordScreen(
                navController,
                authViewModel,
                user
            )
        }

        composable(route = "projects") {
            ProjectListScreen(navController, projectViewModel, user.id)
        }



        composable(route = "projects/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ){
            ProjectDetailsScreen(
                navController,
                projectViewModel,
                it.arguments?.getString("id")!!,
            )
        }

        composable(route = "projects/{id}/form",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        )
        {
            ProjectFormScreen(
                navController = navController,
                profileViewModel = profileViewModel,
                it.arguments?.getString("id")!!,
                address,
            )
        }

        composable(route = "address/autocomplete") {
            AddressAutocompleteScreen(
                navController,
                addressViewModel,
                onSelect = onChangeAddress
            )
        }
//        composable(
//            route = "windows/{id}",
//            arguments = listOf(
//                navArgument("id") {
//                    type = NavType.StringType
//                }
//            )
//        ) {
//            WindowScreen(
//                navController,
//                it.arguments?.getString("id"),
//                windowViewModel,
//            )
//        }
//        composable(
//            route = "dashboard",
//        ) {
//            DashboardScreen(
//                navController,
//                clientViewModel
//            )
//        }
    }
}

