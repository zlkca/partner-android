@file:OptIn(ExperimentalMaterial3Api::class)

package com.artbird.onsite.ui.appointment

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.artbird.onsite.domain.*


@Composable
fun AppointmentTabContent(
    navController: NavController,
//    appointmentId: String?,
    appointment: Appointment?,
//    layoutViewModel: LayoutViewModel,
){

//    val layouts by layoutViewModel.layouts.observeAsState()




//            Button(onClick = {
//                if(layouts?.isEmpty() == true && appointmentId!=null) {
//                    // create default layout
//                    layoutViewModel.createLayout(
//                        Building("",
//                            "New Address",
//                            "New layout",
//                            appointment = BaseAppointment(appointmentId, appointment?.title!!),
//                            floors =  listOf(
//                                Floor("", "First Floor", "", rooms = listOf(
//                                    Room("", "Living Room", ""),
//                                    Room("", "Family Room", ""),
//                                    Room("", "Dinning Room", ""),
//                                    Room("", "Kitchen", ""),
//                                )),
//                                Floor("", "Second Floor", "", rooms = listOf(
//                                    Room("", "Master Bedroom", ""),
//                                    Room("", "Room 1", ""),
//                                    Room("", "Room 2", ""),
//                                    Room("", "Room 3", ""),
//                                    Room("", "Washroom1", ""),
//                                    Room("", "Washroom2", ""),
//                                )),
//                            )
//                        ),
//                    )
//                }
//                // navController.navigate("appointments/${appointmentId}/layouts")
//            }) {
//                Text(text = "Create Layout")
//            }



}

//
//@Composable
//fun AccountRow(
//    modifier: Modifier = Modifier,
//    name: String,
////    number: Int,
////    amount: Double,
//    color: Color
//) {
//    BaseRow(
//        modifier = modifier,
//        color = color,
//        title = name,
////        subtitle = stringResource(R.string.account_redacted) + AccountDecimalFormat.format(number),
////        amount = amount,
//        negative = false
//    )
//}
//
//@Composable
//fun BillRow(name: String, due: String, amount: Float, color: Color) {
//    BaseRow(
//        color = color,
//        title = name,
//        subtitle = "Due $due",
//        amount = amount,
//        negative = true
//    )
//}
//
//@Composable
//private fun BaseRow(
//    modifier: Modifier = Modifier,
//    color: Color,
//    title: String,
//    subtitle: String,
//    amount: Float,
//    negative: Boolean
//) {
//    val dollarSign = if (negative) "–$ " else "$ "
//    val formattedAmount = formatAmount(amount)
//    Row(
//        modifier = modifier
//            .height(68.dp)
//            .clearAndSetSemantics {
//                contentDescription =
//                    "$title account ending in ${subtitle.takeLast(4)}, current balance $dollarSign$formattedAmount"
//            },
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        val typography = MaterialTheme.typography
//        AccountIndicator(
//            color = color,
//            modifier = Modifier
//        )
//        Spacer(Modifier.width(12.dp))
//        Column(Modifier) {
//            Text(text = title, style = typography.body1)
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text(text = subtitle, style = typography.subtitle1)
//            }
//        }
//        Spacer(Modifier.weight(1f))
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = dollarSign,
//                style = typography.h6,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//            Text(
//                text = formattedAmount,
//                style = typography.h6,
//                modifier = Modifier.align(Alignment.CenterVertically)
//            )
//        }
//        Spacer(Modifier.width(16.dp))
//
//        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//            Icon(
//                imageVector = Icons.Filled.ChevronRight,
//                contentDescription = null,
//                modifier = Modifier
//                    .padding(end = 12.dp)
//                    .size(24.dp)
//            )
//        }
//    }
//    RallyDivider()
//}
//
//@Composable
//private fun AccountIndicator(color: Color, modifier: Modifier = Modifier) {
//    Spacer(
//        modifier
//            .size(4.dp, 36.dp)
//            .background(color = color)
//    )
//}
//
//@Composable
//fun RallyDivider(modifier: Modifier = Modifier) {
//    Divider(color = MaterialTheme.colors.background, thickness = 1.dp, modifier = modifier)
//}
//
//fun formatAmount(amount: Float): String {
//    return AmountDecimalFormat.format(amount)
//}
//
//private val AccountDecimalFormat = DecimalFormat("####")
//private val AmountDecimalFormat = DecimalFormat("#,###.##")
//
//fun <E> List<E>.extractProportions(selector: (E) -> Float): List<Float> {
//    val total = this.sumOf { selector(it).toDouble() }
//    return this.map { (selector(it) / total).toFloat() }
//}
