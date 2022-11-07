package com.artbird.onsite.ui.utils

//import androidx.compose.ui.text.style.TextAlign
//import com.shutterlux.onsite.R
//import com.jetpack.generatefilepdf.ui.theme.Purple500

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfScreen(context: Context, fileName: String, directory: File, pdfDocument: PdfDocument){

    var pdfFile by remember { mutableStateOf<File?>(null) }
    var pickedImageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//        println("selected file URI ${it.data?.data}")
        pickedImageUri = it.data?.data
    }

    pickedImageUri?.let {
        Text(it.toString())
    }

    Surface(
//        color = MaterialTheme.colors.background
    ) {
        Scaffold(
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        pdfFile = generatePDF(fileName, directory, pdfDocument)
//                        if(pdfFile != null){
//                            Toast.makeText(context, "PDF file generated successfully", Toast.LENGTH_SHORT).show()
//                        }else{
//                            Toast.makeText(context, "PDF file generated failed", Toast.LENGTH_SHORT).show()
//                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(60.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(Purple500)
                ) {
                    Text(
                        text = "Generate Pdf",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }



                Button(
                    onClick = {
//                        val path = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Android/media/com.shutterlux.onsite/onsite/myQuote.pdf";
//                        Log.i("zlk", path)
//                        val file = File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/myQuote.pdf");
//                        val target = Intent(Intent.ACTION_VIEW)
//                        target.setDataAndType(Uri.fromFile(file), "application/pdf")
//                        target.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
//
//                        val intent = Intent.createChooser(target, "Open File")
//                        try {
////                            startActivity(intent)
//                            intent.apply {
//                                addCategory(Intent.CATEGORY_OPENABLE)
//                            }
//                        } catch (e: ActivityNotFoundException) {
//                            // Instruct the user to install a PDF reader here, or something
//                        }
                        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                            .apply {
                                addCategory(Intent.CATEGORY_OPENABLE)
                            }
                        launcher.launch(intent)
                    }
                ) {
                    Text("Select")
                }
            }
        }
    }
}