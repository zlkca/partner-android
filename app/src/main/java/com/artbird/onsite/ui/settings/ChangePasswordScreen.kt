package com.artbird.onsite.ui.settings

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Credential
import com.artbird.onsite.ui.auth.AuthViewModel

@Composable
fun ChangePasswordScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    user: Account,
) {
    val authError by authViewModel.error.observeAsState()
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }


    var error by remember { mutableStateOf(mapOf<String, String>())}

    LaunchedEffect(key1 = authError) {
        if(authError != null) {
            if(authError!!.code != 200){
                error = mapOf(authError!!.field to authError!!.message)
            }else{
                authViewModel.clearError()
                navController.navigate("settings")
            }
        }
    }

    ChangePasswordForm(
        navController,
        oldPassword,
        newPassword,
        error = error,
        onChange = {name, value ->
                   when(name){
                       "oldPassword" -> oldPassword = value
                       "newPassword" -> newPassword = value
                   }
        },
        onCancel = {
            authViewModel.clearError()
            navController.navigate("settings")
        },
        onSave = {
            authViewModel.changePassword(
                Credential(
                    email = user.email,
                    password = newPassword,
                    oldPassword = oldPassword
                )
            )

        }
    )
}