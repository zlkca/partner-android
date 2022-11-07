package com.artbird.onsite.ui.building

//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*

//import androidx.navigation.compose.rememberNavController
//import com.shutterlux.onsite.domain.RoomListItem
import com.artbird.onsite.ui.components.*
//import com.shutterlux.onsite.ui.components.List

@Composable
fun BuildingDetailsView(
    buildingIndex: Int,
    building: Building,
    onNext: (step: String, index: Int) -> Unit,
    onPrevious: (step: String, index: Int) -> Unit,
) {

    var selectedIndex by remember { mutableStateOf(0) }
    var mode by remember { mutableStateOf("view") }

    var floors = building.floors


    fun selectFloor(index: Int){
        selectedIndex = index
        onNext("rooms", selectedIndex)
    }

    fun getFloorLabel(item: Floor, name: String): String {
        return item.name
    }

    // toolbar handler
    fun handleAdd(){

    }

    fun handleDelete(){
    }

    fun handleBack(){
        onPrevious("buildings", buildingIndex)
    }

    fun handleSubmit(){
        mode = "view"
    }

    fun handleCancel(){
        mode = "view"
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        if(floors != null) {


            OldFormActionBar(mode, onCancel = ::handleCancel, onBack = ::handleBack, onSave = ::handleSubmit, onEdit = {mode = "edit"})

            if(mode == "edit") {
//                BuildingFormScreen(building, ::handleSubmit)
            }else {
                Text(text="${building.name}", modifier = Modifier.padding(8.dp))
                Text(text="${building.notes}", modifier = Modifier.padding(8.dp))

                ListActionBar(items = listOf(
                    ActionChip("Floor", onClick = {}),
                    ActionChip("Sample Floor", onClick = {})
                ))
                if (floors != null && floors?.isNotEmpty()!!) {
                    com.artbird.onsite.ui.components.List<Floor>(
                        floors!!,
                        selectedIndex,
                        fields = listOf("name"),
                        onGetLabel = ::getFloorLabel,
                        onSelect = ::selectFloor,
                    )
                }
            }
        }

    }

}

//@Preview(showBackground = true)
//@Composable
//fun PreviewBuildingScreen(){
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

//
//
//@Composable
//fun Area2Page(){
//    Text("Page 2")
//}
//
//
//@Composable
//fun Building1Screen(appointmentId: String?,
//                     navController: NavController,
//                     buildingViewModel: BuildingViewModel,
//                     quoteViewModel: QuoteViewModel,
//                     appointmentViewModel: AppointmentViewModel,
//) {
//
//    val layouts by buildingViewModel.layouts.observeAsState()
//    val quotes by quoteViewModel.quotes.observeAsState()
//    val appointment by appointmentViewModel.appointment.observeAsState()
//
//    LaunchedEffect(key1 = appointmentId){
//        if(appointmentId != null) {
//            appointmentViewModel.getAppointment(appointmentId)
//            buildingViewModel.getBuildingsByAppointmentId(appointmentId)
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


