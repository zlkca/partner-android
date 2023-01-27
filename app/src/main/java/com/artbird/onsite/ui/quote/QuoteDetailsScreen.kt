package com.artbird.onsite.ui.quote

//import android.content.Context
import android.os.Environment
//import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.artbird.onsite.domain.QuoteWindow
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.utils.generatePDF
//import com.artbird.onsite.ui.window.toInchString
import java.io.File

@Composable
fun QuoteHeader(company: String, address: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            Column() {
                Text(company, fontSize = 20.sp)
                Text(address, modifier = Modifier.padding(top = 20.dp))
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            Column(verticalArrangement = Arrangement.Top,) {
                Text("Quote", fontSize = 20.sp)
                Text(" ", modifier = Modifier.padding(top = 20.dp))
                Text(" ")
            }
        }
    }
}
@Composable
fun HorizontalList(
    items: List<Pair<String, String>>,
) {
    Row() {
        Column(
        ) {
            items.forEachIndexed { index, it ->
                Row() {
                    Text(it.first, modifier = Modifier.padding(end = 15.dp))
                }
            }
        }
        Column(
        ) {
            items.forEachIndexed { index, it ->
                Row() {
                    Text(it.second)
                }
            }
        }
    }
}

@Composable
fun QuoteInfo(client: String, address: String, quoteNumber: String, quoteDate: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            Column() {
                Text("Bill To")
                Text(client, modifier = Modifier.padding(top = 1.dp))
                Text(address)
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            HorizontalList(listOf(
                Pair("Quote #", quoteNumber),
                Pair("Quote Date", quoteDate),
                Pair("P.O #", "123456")
            ))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuoteHeader() {
    Column() {
        QuoteHeader("Shutterlux", "Unit9, 1380 Yonge St, Toronto, ON M2N 0C3")
        QuoteInfo("My Client",
            "Unit902, 5180 Yonge St, Toronto, ON M2N 0C3",
            "123456",
            "2022-09-24")
    }
}

@Composable
fun QuoteDetailsScreen(
    dir: File,
//    context: Context,
    navController: NavController,
    id: String?,
    viewModel: QuoteViewModel,
) {
    var dialogOpened by remember { mutableStateOf(false) }
    val quote by viewModel.quote.observeAsState()
    var rows: MutableList<List<Cell>> by remember { mutableStateOf(arrayListOf()) }

    val columns = listOf(
        Field("Description","description", 80f),
        Field("Dimension (W x H)","dimension", 80f),
        Field("Area","area", 40f),
        Field("Amount","amount", 40f)
    )

    LaunchedEffect(key1 = id) {
        if (id != null) {
            viewModel.getQuote(id)
        }
    }

//    LaunchedEffect(key1 = quote ){
//        if(quote!=null){
//            var rs: MutableList<List<Cell>> = arrayListOf()
//            quote?.windows!!.forEach { it ->
//                val item = listOf(
//                    Cell("description", "${it?.floor!!.name} ${it?.room!!.name} ${it?.window!!.name}"),
//                    Cell("dimension", "${toInchString(it?.width!!)} X ${toInchString(it?.height!!)}"),
//                    Cell("area", "${it.area}"),
//                    Cell("amount", "$${it.total}")
//                )
//                rs.add(item)
//            }
//            rows = rs
//        }
//    }


    fun isExternalStorageReadOnly(): Boolean {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED_READ_ONLY == extStorageState
    }

    fun isExternalStorageAvailable(): Boolean {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == extStorageState
    }

    Column() {
        PrintActionBar(
            {
                navController.navigate("quotes")
            },
            SubmitButton("Save as Pdf") {

                dialogOpened = (!isExternalStorageAvailable() || isExternalStorageReadOnly())

                if(!dialogOpened) {
                    val file = generatePDF("myQuote", dir, QuotePdfPage(quote=quote!!))
                    if(file?.exists() == true){
                        dialogOpened = true
                    }
                }


//                if(pdfFile != null){
//                    Toast.makeText(context, "PDF file generated successfully", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(context, "PDF file generated failed", Toast.LENGTH_SHORT).show()
//                }
            }
        )

        if(quote != null) {
            Column() {

                QuoteHeader("Shutterlux", "Unit9, 1380 Yonge St, Toronto, ON M2N 0C3")
                QuoteInfo(
                    quote!!.client.username,
                    quote!!.address,
                    "123456",
                    "2022-09-24")

//                SimpleTable(
//                    columns = columns,
//                    data = rows,
//                )
                SimpleTable<QuoteWindow>(
                    columns,
                    quote?.windows!!,
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
                                        "description" -> {
                                            Text("${it?.floor!!.name} ${it?.room!!.name} ${it?.window!!.name}")
                                        }
                                        "dimension" -> {
//                                            Text("${toInchString(it?.width!!)} X ${toInchString(it?.height!!)}")
                                        }
                                        "area" -> {
                                            Text("${it.area}")
                                        }
                                        "amount" -> {
                                            Text("$${it.total}")
                                        }
                                    }

                                }

                            }
                        }
                        Divider(color = Color.Gray)
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ){
                    Text("Total: $${quote?.total}")
                }


            }
        }

//        if(dialogOpened){
//        com.shutterlux.onsite.ui.components.Dialog(onDismissRequest = { dialogOpened = false },
//            confirmButton = { dialogOpened = false },
//            title = "Storage",
//            text = "Cannot write to Storage"
//        )
//        }
    }

}