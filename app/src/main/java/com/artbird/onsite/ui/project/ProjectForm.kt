package com.artbird.onsite.ui.project

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.Body1
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Label3
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getAddressString

@Composable
fun ProjectForm(
//    project: Project,
    clientName: String,
    address: String,
    onClickClient: () -> Unit = {},
    onClickAddress: () -> Unit = {},
    onCancel: () -> Unit = {},
    onSave: ()-> Unit = {}
){
//    val client = project.client;
//    var clientName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.background(color= MaterialTheme.colorScheme.background)
    ){
        FormActionBar(
            onCancel,
            onSave,
        )


        OutlinedTextField(
            label = { Text("Client") },
            value = clientName,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth().clickable {
                onClickClient()
            },
            placeholder = { Text(text = "Click to Select a Client") },
            enabled = false
        )

        OutlinedTextField(
            label = { Text("Address") },
            value = address,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=20.dp)
                .clickable {
                    onClickAddress()
                },
            placeholder = { Text(text = "Click to type an address") },
            enabled = false
        )
//        Label3("ADDRESS")
//        Body1(getAddressString(project.address))
//
//        Label3("PROGRESS")
//        StageList(stages = project.stages, selectedIndex = -1) // do not select any row
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewProjectForm(){
    val project = Project("1",
        client = Client2("1", account = Account("1", "Jacky")),
        address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7"),
        created = "2022-11-08",
        stages = listOf<Stage>(
            Stage("make sales appointment", status="done", start = "2022-11-07", end = "2022-11-08"),
            Stage("send quote", status="done", start = "2022-11-08", end = "2022-11-10"),
            Stage("sign contract", status="in progress", start = "2022-11-12", end = "2022-11-12"),
        )
    )

    SLTheme {
        ProjectForm(
            "",
            "",
            onClickClient = {}
        )
    }
}