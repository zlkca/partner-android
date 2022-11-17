package com.artbird.onsite.ui.project

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Address
import com.artbird.onsite.domain.Project
import com.artbird.onsite.domain.Stage
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.Body1
import com.artbird.onsite.ui.components.ListActionBar
import com.artbird.onsite.ui.components.Title2
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getAddressString
import com.artbird.onsite.ui.utils.getDateTimeString

fun getLastStage(stages: List<Stage>): Stage {
    return stages.last()
}

fun getStageTimeString(stage: Stage): String{
    val start = getDateTimeString(stage.start)
    if(stage.status === "done"){
        val end = getDateTimeString(stage.end)
        return "From ${start} to ${end}"
    }else{
        return "From ${start} till now"
    }
}

@Composable
fun ProjectListItem(
    item: Project,
    selected: Boolean
){
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val lastStage = getLastStage(item.stages);

    Column(){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Title2(
                text = if(item.address.streetName.isNotEmpty()) "Use Address" else item.created, // getAddressString(item.address),
                color = if (selected) colorScheme.onPrimary else colorScheme.onBackground,
            )
            Body1(
                text = "View Details >>",
                color = if (selected) colorScheme.onPrimary else colorScheme.onBackground,
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Body1(
                text = "${item.status}",
                color = if (selected) colorScheme.onPrimary else colorScheme.onBackground,
            )

            if(item.status != "done"){
                Body1(
                    text = lastStage.name,
                    color = if (selected) colorScheme.onPrimary else colorScheme.onBackground,
                )
            }
        }


    }
}

@Composable
fun ProjectList(
    projects: List<Project>,
    selectedIndex: Int,
    onSelect: (index: Int) -> Unit = { i: Int -> },
    onAdd: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

//        ListActionBar(items = listOf(
//            ActionChip("Project", onClick=onAdd),
//        ))

        if (projects != null && projects.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Project>(
                projects,
                selectedIndex,
                onSelect = onSelect,
                itemContent = { it, selected, index ->
                    ProjectListItem(item=it, selected =selected)
                }
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
fun PreviewProjectList(){
    val projects = listOf<Project>(
        Project("1",
            address= Address("1", "A2", "3", "QianMen St", "Toronto", "ON", "L3R 0C7"),
            stages= listOf(
                Stage("make sales appointment", "done", "2022-11-06T09:30:00.000", "2022-11-06T10:00:00.000"),
                Stage("measure windows", "done", "2022-11-07T09:30:00.000", "2022-11-07T11:30:00.000")
            ),
            status = "done",
            created = "2022-11-07"
        ),
        Project("1",
            address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7"),
            stages= listOf(
                Stage("make sales appointment", "done", "2022-11-06T09:30:00.000", "2022-11-06T10:00:00.000"),
                Stage("measure windows", "done", "2022-11-07T09:30:00.000", "2022-11-07T11:30:00.000"),
                Stage("measure windows", "in progress", "2022-11-08T09:30:00.000", "2022-11-09T11:30:00.000")
            ),
            status = "in progress",
            created = "2022-11-08"
        ),
        )
    SLTheme {
        ProjectList(
            projects,
            0
        )
    }
}