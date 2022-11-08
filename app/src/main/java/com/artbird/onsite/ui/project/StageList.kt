package com.artbird.onsite.ui.project

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Stage
import com.artbird.onsite.ui.theme.SLTheme

@Composable
fun StageListItem(item: Stage, selected: Boolean, index:Int){
    val colorScheme = MaterialTheme.colorScheme
    Column(){
        Text(
            text = item.name,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )
        Text(
            text = item.status,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )
        Text(
            text = item.start,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )
        Text(
            text = item.end,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )
    }
}

@Composable
fun StageList(
    stages: List<Stage>,
    selectedIndex: Int,
    onSelect: (index: Int) -> Unit = { i: Int -> },
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        if (stages != null && stages.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Stage>(
                stages,
                selectedIndex,
                onSelect = onSelect,
                itemContent = { it, selected, index ->
                    StageListItem(item=it, selected =selected, index =index)
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
fun PreviewStageList(){
    val stages = listOf<Stage>(
        Stage("make sales appointment", status="done", start = "2022-11-07", end = "2022-11-08"),
        Stage("send quote", status="done", start = "2022-11-08", end = "2022-11-10"),
        Stage("sign contract", status="in progress", start = "2022-11-12", end = "2022-11-12"),
    )
    SLTheme {
        StageList(
            stages,
            0
        )
    }
}