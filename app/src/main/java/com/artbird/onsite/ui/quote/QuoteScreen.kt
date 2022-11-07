package com.artbird.onsite.ui.quote

import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.artbird.onsite.ui.components.Dialog
import com.artbird.onsite.ui.components.PrintActionBar
import com.artbird.onsite.ui.components.SubmitButton
import com.artbird.onsite.ui.utils.generatePDF
import java.io.File

@Composable
fun QuoteScreen(
    dir: File,
//    context: Context,
    navController: NavController,
    id: String?,
    viewModel: QuoteViewModel,
) {
    var dialogOpened by remember { mutableStateOf(false) }
    val quote by viewModel.quote.observeAsState()

    LaunchedEffect(key1 = id) {
        if (id != null) {
            viewModel.getQuote(id)
        }
    }

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
                navController.popBackStack()
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
                Row() {
                    Text("Address:")
                    Text(text = quote?.address!!)
                }

                Row() {
                    Text("Client:")
                    Text(text = quote?.client!!.username!!)
                }

                quote?.windows!!.forEach { it ->
                    Text(text = "Window: ${it?.floor!!.name} ${it?.room!!.name} ${it?.window!!.name}")
                    Text(text = "Width X Height: ${it?.width!!}inch X ${it?.height!!}inch")
                    Text("Area: ${it.area}")
                    Text("Subtotal: $${it.total}")
                }
                Text("Total: $${quote?.total}")

            }
        }

        if(dialogOpened){
//        com.artbird.onsite.ui.components.Dialog(onDismissRequest = { dialogOpened = false },
//            confirmButton = { dialogOpened = false },
//            title = "Storage",
//            text = "Cannot write to Storage"
//        )
        }
    }

}