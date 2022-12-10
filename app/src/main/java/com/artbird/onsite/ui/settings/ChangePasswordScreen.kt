package com.artbird.onsite.ui.settings

import androidx.compose.runtime.*
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
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    ChangePasswordForm(
        navController,
        oldPassword,
        newPassword,
        onCancel = {
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