package com.artbird.onsite.ui.project

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.Body1
import com.artbird.onsite.ui.components.Label3
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getAddressString


@Composable
fun ProjectDetails(project: Project){
    val client = project.client;
    Column(
        modifier = Modifier.background(color=MaterialTheme.colorScheme.background)
    ){
        Label3("CLIENT USERNAME")
        Body1("${client.account.username}")

        Label3("ADDRESS")
        Body1(getAddressString(project.address))

        Label3("PROGRESS")
        StageList(stages = project.stages, selectedIndex = -1) // do not select any row
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewProjectDetails(){
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
        ProjectDetails(
            project
        )
    }
}