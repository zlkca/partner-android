package com.artbird.onsite.ui.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.client.ClientViewModel
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.utils.getAddressString


@Composable
fun RecordDetailsScreen(
    navController: NavController,
    clientViewModel: ClientViewModel,
    recordViewModel: RecordViewModel,
    clientId: String,
    recordId: String,
) {
    val clientDetails by clientViewModel.clientDetails.observeAsState()
    val record by recordViewModel.record.observeAsState()

    var selectedIndex by remember { mutableStateOf(0) }
    var client by remember { mutableStateOf(ClientDetails()) }

    LaunchedEffect(key1 = clientId) {
        if (clientId != null && clientId != "new") {
            clientViewModel.getClientDetails(clientId)
        }
    }

    LaunchedEffect(key1 = recordId) {
        if (recordId != null && recordId != "new") {
            recordViewModel.getRecord(recordId)
        }
    }

    LaunchedEffect(key1 = clientDetails){
        if(clientDetails != null) {
            client = clientDetails!!
        }
    }

    fun getStageLabel(item: Stage, name: String): String {
        return when (name) {
            "name" -> {
                item.name
            }
            "status" -> {
                item.status
            }
            "start" -> {
                item.start
            }
            "end" -> {
                item.end
            }else -> {
                ""
            }
        }
    }


    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        DetailsViewActionBar(
            onBack = {navController.navigate("clients")},
            onEdit = { navController.navigate("clients/${client.id}/form") }
        )

        if(client!=null) {
            Text(text = "${client!!.account.username}", modifier = Modifier.padding(8.dp))

            Text(text = "${client!!.account.email}", modifier = Modifier.padding(8.dp))

            Text(text = "${client!!.account.phone}", modifier = Modifier.padding(8.dp))

            Text(text = getAddressString(client!!.address), modifier = Modifier.padding(8.dp))


            if (record != null && record?.stages?.isNotEmpty()!!) {
//                com.shutterlux.onsite.ui.components.List<Stage>(
//                    record?.stages!!,
//                    selectedIndex,
//                    fields = listOf("name", "status", "start", "end"),
//                    onGetLabel = ::getStageLabel,
//                    onSelect = { index -> selectedIndex = index },
//
//                )
                val columns = listOf(
                    Field("Stage","name", 60f),
                    Field("Status","status", 20f),
                    Field("Start","start", 40f),
                    Field("end","end", 40f),
                )
                SimpleTable<Stage>(
                    columns,
                    record?.stages!!,
                    onRowRender = { cols, it, w ->
                        Row(
//                            modifier = Modifier.border(0.5.dp, Color.Black)
                        ) {
                            cols.forEachIndexed { index, field ->
                                Row(modifier = Modifier
                                    .weight(cols[index].width / w)
                                    .padding(4.dp)
                                ) {
                                    when (cols[index].name) {
                                        "name" -> {
                                            Text(it.name)
                                        }
                                        "status" -> {
                                            Text(it.status)
                                        }
                                        "start" -> {
                                            Text(it.start)
                                        }
                                        "end" -> {
                                            Text(it.end)
                                        }
                                    }
                                }
                            }
                        }
                        Divider(color = Color.Gray)
                    }
                )
            }
        }
    }

}
