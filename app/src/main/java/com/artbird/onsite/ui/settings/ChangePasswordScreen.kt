package com.artbird.onsite.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Credential
import com.artbird.onsite.ui.account.AccountViewModel
import com.artbird.onsite.ui.account.ApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    navController: NavController,
    accountViewModel: AccountViewModel,
    user: Account,
) {
    val authError by accountViewModel.error.observeAsState()
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }


    var error by remember { mutableStateOf(mapOf<String, String>())}

    LaunchedEffect(key1 = authError) {
        if(authError != null) {
            if(authError!!.code != 200){
                error = mapOf(authError!!.field to authError!!.message)
            }else{
                accountViewModel.clearError()
                navController.navigate("settings")
            }
        }
    }

    if (accountViewModel.status == ApiStatus.LOADING){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator()
        }
    }

    if (accountViewModel.status == ApiStatus.UPDATE_DONE) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "Change password successfully!",
                duration = SnackbarDuration.Short
            )
            accountViewModel.clearStatus()
        }
    }else {

        ChangePasswordForm(
            navController,
            oldPassword,
            newPassword,
            error = error,
            onChange = { name, value ->
                when (name) {
                    "oldPassword" -> oldPassword = value
                    "newPassword" -> newPassword = value
                }
            },
            onCancel = {
                accountViewModel.clearError()
                navController.navigate("settings")
            },
            onSave = {
                accountViewModel.changePassword(
                    Credential(
                        email = user.email,
                        password = newPassword,
                        oldPassword = oldPassword
                    )
                )

            }
        )
    }
}