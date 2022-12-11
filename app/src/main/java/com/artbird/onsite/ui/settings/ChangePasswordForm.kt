package com.artbird.onsite.ui.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.ui.components.Body2
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.components.LongButton
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun ChangePasswordForm(
    navController: NavController,
    oldPassword: String,
    newPassword: String,
    error: Map<String, String> = mapOf<String, String>(),
    onChange: (name:String, value: String)-> Unit = {name, value->},
    onCancel: () -> Unit = {},
    onSave: () -> Unit = {}
){
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        Text(text = "Change Password", fontSize = 20.sp, modifier = Modifier.padding(8.dp))

        FormActionBar(
            onCancel = onCancel,
            showSaveButton = false,
        )

        Input(
            value = oldPassword,
            onValueChange = { onChange("oldPassword", it) },
            label = "Old Password",
        )
        if (error["oldPassword"] != null && error["oldPassword"] != "") {
            Body2(
                text = error["oldPassword"]!!,
                color = colorScheme.onError,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Input(
            value = newPassword,
            onValueChange = { onChange("newPassword", it) },
            label = "New Password",
        )
        if (error["newPassword"] != null && error["newPassword"] != "") {
            Body2(
                text = error["newPassword"]!!,
                color = colorScheme.onError,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        LongButton(
            "Submit",
            onClick = onSave,

            modifier = Modifier
                .fillMaxWidth()
                .padding(top=20.dp, start = 8.dp, end = 8.dp)
                .focusTarget()
        )
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewFormActionBar(){
    SLTheme {
        ChangePasswordForm(
            navController = rememberNavController(),
            "123456",
            "123456",
            onChange = {name, value -> },
        )
    }
}