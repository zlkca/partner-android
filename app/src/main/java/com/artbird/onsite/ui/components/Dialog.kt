package com.artbird.onsite.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(
    onDismissRequest: () -> Unit = {},
    confirmButton: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    dismissButton: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    title: String = "",
    text: String = "", // (@Composable () -> Unit)? = null,
    openDialogCustom: Boolean,
//    shape: Shape = AlertDialogDefaults.shape,
//    containerColor: Color = AlertDialogDefaults.containerColor,
//    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
//    iconContentColor: Color = AlertDialogDefaults.iconContentColor,
//    titleContentColor: Color = AlertDialogDefaults.titleContentColor,
//    textContentColor: Color = AlertDialogDefaults.textContentColor,
    properties: DialogProperties = DialogProperties()
): Unit {

//    @Composable
//    fun CustomDialog(openDialogCustom: MutableState<Boolean>) {
//        Dialog(onDismissRequest = { openDialogCustom.value = false}) {
//            CustomDialogUI(openDialogCustom = openDialogCustom)
//        }
//    }

//    //Layout
//    @Composable
//    fun CustomDialogUI(modifier: Modifier = Modifier, openDialogCustom: MutableState<Boolean>){
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(10.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier.padding(10.dp,5.dp,10.dp,10.dp),
//            elevation = 8.dp
        ) {
            Column(
                modifier
                    .background(Color.White)) {

                //.......................................................................
//                Image(
//                    painter = painterResource(id = R.drawable.notification),
//                    contentDescription = null, // decorative
//                    contentScale = ContentScale.Fit,
//                    colorFilter  = ColorFilter.tint(
//                        color = Purple40
//                    ),
//                    modifier = Modifier
//                        .padding(top = 35.dp)
//                        .height(70.dp)
//                        .fillMaxWidth(),
//
//                    )

                Column(modifier = Modifier.padding(16.dp)) {
                    androidx.compose.material3.Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    androidx.compose.material3.Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                //.......................................................................
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
//                        .background(Purple80)
                    horizontalArrangement = Arrangement.SpaceAround) {

                    androidx.compose.material3.TextButton(onClick = {
//                        openDialogCustom.value = false
//                        dismissButton()
                    }) {

                        androidx.compose.material3.Text(
                            "Not Now",
                            fontWeight = FontWeight.Bold,
//                            color = PurpleGrey40,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                    androidx.compose.material3.TextButton(onClick = {
//                        openDialogCustom.value = false
//                        confirmButton()
                    }) {
                        androidx.compose.material3.Text(
                            "Allow",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                }
            }
     //   }
    }





//    AlertDialog(
//        onDismissRequest,
//        confirmButton,
//        modifier,
//        dismissButton,
//        icon,
//        title = { Text(text = title) },
//        text  = { Text(text = text) },
////        shape,
////        containerColor,
////        tonalElevation,
////        iconContentColor,
////        titleContentColor,
////        textContentColor,
//        properties = properties,
//    )

}

@SuppressLint("UnrememberedMutableState")
@Preview(name="Custom Dialog")
@Composable
fun MyDialogUIPreview(){
    Dialog(
        title = "Get Update",
        text = "Allow Permission to send you notifications when new art styles added.",
        openDialogCustom = true)
}