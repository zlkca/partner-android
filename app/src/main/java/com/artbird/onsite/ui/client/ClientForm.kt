package com.artbird.onsite.ui.client

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.Body2
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun ClientForm(
    firstName: String,
    lastName: String,
    account: Account,
    error: Map<String, String> = mapOf<String, String>(),
    onChange: (fieldName: String, value: String) -> Unit = {f,v -> },
    onCancel: ()-> Unit = {},
    onSave: ()-> Unit = {}
) {
    val verticalScrollState = rememberScrollState()

    Column(modifier = Modifier
//        .padding(12.dp)
        .verticalScroll(verticalScrollState))
    {
        FormActionBar(
            onCancel = onCancel,
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
        Input(
            value = account.username,
            onValueChange = { onChange("username", it) },
            label = "username",
        )
        Input(
            value = account.email,
            onValueChange = { onChange("email", it) },
            label = "Email",
        )
        if (error.isNotEmpty() && error["email"] != null && error["email"] != "" ) {
            Body2(
                text = error["email"]!!,
                color = colorScheme.onError,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Input(
            value = account.phone,
            onValueChange = { onChange("phone", it) },
            label = "Phone",
        )
        if (error.isNotEmpty() && error["phone"] != null && error["phone"] != "" ) {
            Body2(
                text = error["phone"]!!,
                color = colorScheme.onError,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
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
            firstName = client.firstName,
            lastName = client.lastName,
            account = client.account,
            error = mapOf("email" to "Email cannot be empty")
        )
    }
}