package com.artbird.onsite.ui.settings

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        Text(
            text = "Settings",
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )

        Text(text = "Change My Password",
            modifier = Modifier
                .padding(8.dp)
                .clickable(enabled = true) {
                    Log.d("zlk", "navigate to /change-password")
                    navController.navigate("change-password")
                },
        )

//        Text(text = "Edit My Profile",
//            modifier = Modifier
//                .padding(8.dp)
//                .clickable(enabled = true) {
//                    // navController.navigate("change-password")
//                },
//        )
    }
}