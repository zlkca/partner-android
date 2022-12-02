package com.artbird.onsite.ui.window

//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.outlined.Add
//import androidx.compose.material.icons.outlined.Delete
//import androidx.compose.material.icons.outlined.Edit
//import androidx.compose.material.icons.outlined.Visibility
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CalendarToday
//import androidx.compose.material.icons.filled.Dashboard
//import androidx.compose.material.icons.outlined.Add
//import androidx.compose.material.icons.outlined.Delete
//import androidx.compose.material.icons.outlined.Edit
//import androidx.compose.material.icons.outlined.Visibility

import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.*

//import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.building.RoomList

@Composable
fun Area1Page(navController: NavController, floors: List<Floor>) {


        var selectedIndex by remember { mutableStateOf(0) }
        val nItems = 2
        var expandedList by remember {
            mutableStateOf(MutableList(nItems){false})
        }

        fun handleSelect(idx: Int){

            val myList = mutableListOf<Boolean>()

            expandedList.forEachIndexed { index, b ->
                if(index == idx) {
                    myList.add(index, !b)
                }else{
                    myList.add(index, b)
                }
            }
            expandedList = myList
            selectedIndex = idx
        }

//        fun selectRoom(idx: Int){
//
//        }
        fun getFloorLabel(item: Floor): String {
            return item.name
        }
//        fun getRoomLabel(item: Room, name: String): String {
//            return item.name + name;
//        }


//        Column() {
//            AccordionList<Floor>(
//                floors,
//                selectedIndex = selectedIndex,
//                expandedList = expandedList,
//                itemContent = { it, index, selectedIndex, expanded ->
//                    fun handleView(id: String){
//                        navController.navigate("rooms/${id}/windows")
//                    }
//
//                    fun handleAdd(id: String){
//                    }
//
//                    fun handleEdit(id: String){
//                    }
//
//                    fun handleDelete(id: String){
//                    }
//                     RoomList(it.rooms, ::handleAdd, ::handleEdit, ::handleDelete, ::handleView)
//
//                              },
//                onClick = ::handleSelect,
//                onGetTitle = ::getFloorLabel,
//            )
//
//        }
}



//
//@Composable
//fun Area2Page(){
//    Text("Page 2")
//}
//
//
//@Composable
//fun LayoutTabsScreen(appointmentId: String?,
//                     navController: NavController,
//                     layoutViewModel: BuildingViewModel,
//                     quoteViewModel: QuoteViewModel,
//                     appointmentViewModel: AppointmentViewModel,
//) {
//
//    val layouts by layoutViewModel.layouts.observeAsState()
//    val quotes by quoteViewModel.quotes.observeAsState()
//    val appointment by appointmentViewModel.appointment.observeAsState()
//
//    LaunchedEffect(key1 = appointmentId){
//        if(appointmentId != null) {
//            appointmentViewModel.getAppointment(appointmentId)
//            layoutViewModel.getBuildingsByAppointmentId(appointmentId)
//            quoteViewModel.getQuotesByAppointmentId(appointmentId)
//        }
//    }
//
//    var state by remember { mutableStateOf(0) }
//    fun handleSelect( index: Int ){
//        state = index
//    }
//
//    val tabs = layouts?.map {
//        TabItem(it.name, Icons.Filled.CalendarToday) { Area1Page(navController, it.floors) }
//    }
//
//    if (tabs != null) {
//        if(tabs.isNotEmpty()) {
//            Column() {
//                Row(){
//                    Button(onClick = {
//                        if(quotes.isNullOrEmpty()){
//                            val data = QuoteRequest(
//                                BaseAppointment(appointmentId, appointment?.title!!),
//                                "My Address",
//                                BaseUser(appointment?.client!!.id, appointment?.client!!.username),
//                                BaseUser(appointment?.employee!!.id, appointment?.employee!!.username),
//                            )
//                            quoteViewModel.createQuote(data);
//                        }
//                    }) {
//                        Text(text = "create Quote")
//                    }
//
//                    Button(onClick = {
//                        if(!quotes.isNullOrEmpty()) {
//                            navController.navigate("appointments/${appointmentId}/quotes")
//                        }
//                    }) {
//                        Text(text = "Show Quotes")
//                    }
//                }
//
//
//                Tabs(tabs, state, ::handleSelect);
//                TabContents(tabs, state)
//            }
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewArea1Page(){
//    val floors =  listOf(
//        Floor("", "First Floor", "", rooms = listOf(
//            Room("", "Living Room", ""),
//            Room("", "Family Room", ""),
//            Room("", "Dinning Room", ""),
//            Room("", "Breakfast", ""),
//            Room("", "Kitchen", ""),
//        )),
//        Floor("", "Second Floor", "", rooms = listOf(
//            Room("", "Master Bedroom", ""),
//            Room("", "Room 1", ""),
//            Room("", "Room 2", ""),
//            Room("", "Room 3", ""),
//            Room("", "Washroom1", ""),
//            Room("", "Washroom2", ""),
//        )),
//    )
//    Area1Page(navController = rememberNavController(), floors)
////    LayoutTabsScreen("123", rememberNavController())
//}