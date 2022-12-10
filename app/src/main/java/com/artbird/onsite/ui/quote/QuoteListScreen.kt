package com.artbird.onsite.ui.quote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.DropdownMenuItem

@Composable
fun QuoteListScreen(
    navController: NavController,
    quoteViewModel: QuoteViewModel,
    appointment: Appointment,
    client: Account
) {
    val quotes: List<Quote> by quoteViewModel.quotes.observeAsState(listOf())

    LaunchedEffect(key1 = appointment._id) {
        if (appointment._id != "" && appointment._id != "new") {
            quoteViewModel.getQuotesByAppointmentId(appointment._id)
        }
    }

    var selectedQuoteIndex by remember { mutableStateOf(0) }

    fun handleDelete(index: Int){
        val quoteId = quotes[index]._id
        if (quoteId != null) {
            quoteViewModel.deleteQuote(quoteId)
            quoteViewModel.getQuotesByAppointmentId(appointment!!._id)
        }
    }

    val menus: List<DropdownMenuItem> = listOf(
        DropdownMenuItem("delete", "Delete", Icons.Outlined.Delete, "Delete", ::handleDelete),
    )

    fun selectQuote(index: Int){
        selectedQuoteIndex = index
        val quoteId = quotes[selectedQuoteIndex]._id
        navController.navigate("quotes/${quoteId}")
    }

    fun getQuoteLabel(item: Quote, name: String): String {
        return when (name) {
            "address" -> {
                item.address
            }
            "total" -> {
                "CAD $${item.total}"
            }
            "created" -> {
                item.created
            }
            else -> {
                ""
            }
        }
    }

    if (appointment._id == "" || appointment._id == "new") {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Please select an appointment")
        }
    }else {
        Column() {
//            ListActionBar(items = listOf(
//                ActionChip("Quote", onClick = {
//                    if (appointment != null) {
//                        val data = QuoteRequest(
//                            BaseAppointment(appointment!!._id, appointment?.title!!),
//                            "test address", // getAddressString(client.address),
//                            BaseAccount(appointment?.client!!.id,
//                                appointment?.client!!.account.username),
//                            BaseAccount(appointment?.employee!!.id,
//                                appointment?.employee!!.username),
//                        )
//                        quoteViewModel.createQuote(data);
//                    }
//                }),
//            ))

            if (quotes != null && quotes.isNotEmpty()) {
                com.artbird.onsite.ui.components.List<Quote>(
                    quotes,
                    selectedQuoteIndex,
                    fields = listOf("total", "created"),
                    onGetLabel = ::getQuoteLabel,
                    onSelect = ::selectQuote,
                    menus = menus
                )
            }
        }
    }
}