package com.artbird.onsite.ui.project

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getAddressString
import com.artbird.onsite.ui.project.StageList

@Composable
fun ProjectDetails(project: Project){
    val client = project.client;

    Column(){
        Text("CLIENT INFO")
        Text("USERNAME")
        Text(text = "${client.account.username}", modifier = Modifier.padding(8.dp))

        Text("ADDRESS")
        Text(text = getAddressString(project.address), modifier = Modifier.padding(8.dp))

        StageList(stages = project.stages, selectedIndex = 0)
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
        client = BaseClient("1", BaseAccount("1", "Jacky")),
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