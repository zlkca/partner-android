package com.artbird.onsite.ui.project

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Address
import com.artbird.onsite.domain.BaseProject
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.utils.getAddressString


@Composable
fun ProjectListItem(item: BaseProject, selected: Boolean, index:Int){
    val colorScheme = MaterialTheme.colorScheme
    Column(){
        Text(
            text = getAddressString(item.address),
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )
        Text(
            text = item.created,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )
    }
}

@Composable
fun ProjectList(
    projects: List<BaseProject>,
    selectedIndex: Int,
    onSelect: (index: Int) -> Unit = { i: Int -> },
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        if (projects != null && projects.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<BaseProject>(
                projects,
                selectedIndex,
                onSelect = onSelect,
                itemContent = { it, selected, index ->
                    ProjectListItem(item=it, selected =selected, index =index)
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
    val projects = listOf<BaseProject>(
        BaseProject("1", address= Address("1", "A2", "3", "QianMen St", "Toronto", "ON", "L3R 0C7"), created = "2022-11-07"),
        BaseProject("1", address= Address("2", "", "235", "Front St", "Toronto", "ON", "L3R 0C7"), created = "2022-11-08"),
        )
    SLTheme {
        ProjectList(
            projects,
            0
        )
    }
}