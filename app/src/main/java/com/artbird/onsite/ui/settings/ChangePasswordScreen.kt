package com.artbird.onsite.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.artbird.onsite.domain.BaseAccount
import com.artbird.onsite.domain.Credential
import com.artbird.onsite.ui.auth.AuthViewModel
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.components.LongButton

@Composable
fun ChangePasswordScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    user: BaseAccount,
) {

    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        Text(text = "Change Password", fontSize = 20.sp, modifier = Modifier.padding(8.dp))

        FormActionBar(
            onCancel = {
                navController.navigate("settings")
            },
            onSave = {

            }
        )

        Input(
            value = oldPassword,
            onValueChange = { oldPassword = it },
            label = "Old Password",
        )

        Input(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = "New Password",
        )

        LongButton(
            "Submit",
            onClick = {
                authViewModel.changePassword(Credential(user.username, newPassword, oldPassword))
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .focusTarget()
        )
    }
}