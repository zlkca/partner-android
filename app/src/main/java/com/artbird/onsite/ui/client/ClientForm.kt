package com.artbird.onsite.ui.client

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun ClientForm(
    navController: NavController,
    firstName: String,
    lastName: String,
    account: Account,
    error: Map<String, String> = mapOf<String, String>(),
    onChange: (fieldName: String, value: String) -> Unit = {f,v -> },
    onSave: ()-> Unit = {}
) {
    val verticalScrollState = rememberScrollState()

    Column(modifier = Modifier
//        .padding(12.dp)
        .verticalScroll(verticalScrollState))
    {
        FormActionBar(
            onCancel = {navController.navigate("clients")},
            onSave,
        )

        Input(
            value = firstName,
            onValueChange = { onChange("firstName", it) },
            label = "First Name",
        )
        Text("")
        Input(
            value = lastName,
            onValueChange = { onChange("lastName", it) },
            label = "Last Name",
        )
        Text("")
//        Input(
//            value = client.account.username,
//            onValueChange = { onChange("username", it) },
//            label = "Account Name",
//        )

        Input(
            value = account.email,
            onValueChange = { onChange("email", it) },
            label = "Email",
        )

        if (error["email"] != "" ) {
            Text(
                text = if(error["email"] == "Email not found") "Email not found" else "",
                color = Color.Red,
//                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Input(
            value = account.phone,
            onValueChange = { onChange("phone", it) },
            label = "Phone",
        )
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewClientForm(){
    val client = Profile("1", firstName = "Jet", lastName = "Lee", account = Account("1", "Jet", email="jet@gmail.com", phone="416-123-4567"))
    val address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7")

    SLTheme {
        ClientForm(
            rememberNavController(),
            firstName = client.firstName,
            lastName = client.lastName,
            account = client.account
        )
    }
}