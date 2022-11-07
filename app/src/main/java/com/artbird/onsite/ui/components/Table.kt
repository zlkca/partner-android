package com.artbird.onsite.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.People
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Stage

//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.AnnotatedString
//import androidx.compose.ui.tooling.preview.Preview
//import com.purple.cms.ui.theme.CmsTheme

data class Field(
    var label: String,
    var name: String,
    var width: Float,
)

data class Cell(
    var name: String,
    var value: String,
)

@Composable
fun TableHeader(
    columns: List<Field>,
) {
    var total = 0f;
    columns.forEach { total += it.width }

    Row(
        modifier = Modifier.border(1.dp, Color.DarkGray).background(Color.Gray)
    ) {
        columns.forEach {
            Row(modifier = Modifier.padding(4.dp).weight(it.width / total)) {
                Text(
                    it.label,

                )
            }
        }
    }
}

@Composable
fun <T> TableRow(
    columns: List<Field>,
    data: T,
    totalWidth: Float,
    onRowRender: @Composable ((cols: List<Field>, d: T, width: Float) -> Unit)
) {
//    Row(modifier = Modifier
//        .border(0.5.dp, Color.Black)
//    ) {
//        data.forEachIndexed { index, it ->
//            Row(modifier = Modifier
//                .weight(columns[index].width / totalWidth)
//                .padding(4.dp)
//            ) {
//                 Text(it.value)
//            }
//        }
        onRowRender(columns, data, totalWidth)
//    }
}

@Composable
fun <T> TableContent(
    columns: List<Field>,
    data: List<T>,
    onRowRender: @Composable ((cols: List<Field>, d: T, width: Float) -> Unit),
) {
    var total = 0f;
    columns.forEach { total += it.width }

    Column (
        modifier = Modifier
            .border(1.dp, Color.DarkGray)
            .padding(8.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ){
        data.forEach {
            TableRow<T>(columns, it, total, onRowRender)
        }
    }
}

@Composable
fun <T> SimpleTable(
    columns: List<Field>,
    data: List<T>,
    onRowRender: @Composable ((cols: List<Field>, d: T, width: Float) -> Unit)
) {
    Column {
        TableHeader(columns)
        TableContent<T>(columns, data, onRowRender)
    }
}

//@Preview(name = "Light Mode")
@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewSimpleTable(

) {
        SimpleTable<Stage>(
            listOf(
                Field("Col A","name", 40f),
                Field("Col B","status", 40f),
                Field("Col C","start", 40f)
            ),
            listOf(
                Stage("name1", "done", "2022-09-30", "2022-10-02"),
                Stage("name2", "in progress", "2022-10-01", "2022-10-02"),
            )
        ) { cols, it, w ->
            Row(modifier = Modifier
//                .border(0.5.dp, Color.Black)
            ) {
                cols.forEachIndexed { index, field ->
                    Row(modifier = Modifier
                        .weight(cols[index].width / w)
                        .padding(4.dp)
                    ) {
                        Text("Hello")
                    }
                }
            }
            Divider(color = Color.Gray)
        }
}
