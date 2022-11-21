package com.artbird.onsite.ui.appointment

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController

import com.artbird.onsite.ui.building.BuildingViewModel

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Appointment
import com.artbird.onsite.domain.BaseAccount
import com.artbird.onsite.domain.BaseClient
import com.artbird.onsite.domain.Client
import com.artbird.onsite.ui.client.ClientViewModel
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.window.WindowViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//@Composable
//fun Area1Page(navController: NavController, floors: List<Floor>) {
//
//
//    var selectedIndex by remember { mutableStateOf(0) }
//    val nItems = 2
//    var expandedList by remember {
//        mutableStateOf(MutableList(nItems){false})
//    }
//
//    fun handleSelect(idx: Int){
//
//        val myList = mutableListOf<Boolean>()
//
//        expandedList.forEachIndexed { index, b ->
//            if(index == idx) {
//                myList.add(index, !b)
//            }else{
//                myList.add(index, b)
//            }
//        }
//        expandedList = myList
//        selectedIndex = idx
//    }
//
//    //        fun selectRoom(idx: Int){
////
////        }
//    fun getFloorLabel(item: Floor): String {
//        return item.name
//    }
////        fun getRoomLabel(item: Room, name: String): String {
////            return item.name + name;
////        }
//
//
//    Column() {
//        AccordionList<Floor>(
//            floors,
//            selectedIndex = selectedIndex,
//            expandedList = expandedList,
//            itemContent = { it, index, selectedIndex, expanded ->
//                fun handleView(id: String){
//                    navController.navigate("rooms/${id}/windows")
//                }
//
//                fun handleAdd(id: String){
//                }
//
//                fun handleEdit(id: String){
//                }
//
//                fun handleDelete(id: String){
//                }
//                RoomList(it.rooms, ::handleAdd, ::handleEdit, ::handleDelete, ::handleView)
//
//            },
//            onClick = ::handleSelect,
//            onGetTitle = ::getFloorLabel,
//        )
//
//    }
//}
//
//
//
//
//@Composable
//fun Area2Page(){
//    Text("Page 2")
//}


@Composable
fun AppointmentScreen(
    navController: NavController,
    appointmentId: String?, // 'new' or appointmentId
    appointmentViewModel: AppointmentViewModel,
    buildingViewModel: BuildingViewModel,
    windowViewModel: WindowViewModel,
    clientViewModel: ClientViewModel,
    user: BaseAccount, // logged in user
) {
    var mode by remember { mutableStateOf(if(appointmentId=="new") "edit" else "view") }

    val appointment by appointmentViewModel.appointment.observeAsState()

    LaunchedEffect(key1 = appointmentId) {
        if (appointmentId != null && appointmentId != "new") {
            appointmentViewModel.getAppointment(appointmentId)
            buildingViewModel.getBuildingsByAppointmentId(appointmentId)
        }
    }

    var client by remember { mutableStateOf(BaseClient("", BaseAccount("", ""))) }
    var title by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }


    LaunchedEffect(key1 = appointment){
        if(appointment != null && appointmentId != "new") {
//            client = appointment?.client!!
//            title = appointment?.title!!
//            notes = appointment?.notes!!
//            val startList = appointment?.start?.split(".")
//            val start = startList?.get(0)?.split("T")
//            date = start?.get(0)!!
//            startTime = start?.get(1)!!
//
//            val endList = appointment?.end?.split(".")
//            val end = endList?.get(0)?.split("T")
//            endTime = end?.get(1)!!
        }
    }

    fun handleSubmit(){
        mode = "view"

//        if (appointmentId != null && appointmentId != "new") {
//            val data = Appointment(
//                _id = appointment?._id!!,
//                title,
//                notes,
//                start="${date}T${startTime}",
//                end="${date}T${endTime}",
//                type= "sales",
//                client = client,
//                employee = BaseAccount(appointment?.employee!!.id, appointment?.employee!!.username),
//                createBy = BaseAccount(appointment?.createBy!!.id, appointment?.createBy!!.username),
//            )
//            appointmentViewModel.updateAppointment(appointment?._id!!, data)
//        } else {
//            val data = Appointment(
//                _id = "",
//                title,
//                notes,
//                start="${date}T${startTime}",
//                end="${date}T${endTime}",
//                type= "sales",
//                client = client,
//                employee = BaseAccount(user.id, user.username),
//                createBy = BaseAccount(user.id, user.username),
//            )
//            appointmentViewModel.createAppointment(data);
//        }
//
//        // refresh list
//        appointmentViewModel.getAppointmentsByEmployeeId(user.id!!)
//        navController.navigate("appointments")
    }

    fun handleDelete(){

    }

    fun handleChangeDate(y: Int, m: Int, d: Int) {
        val dateTime =
            LocalDateTime.now().withYear(y).withMonth(m).withDayOfMonth(d)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        date = dateTime.format(formatter)
    }

    fun handleChangeTime(name: String, h: Int, m: Int) {
        val dateTime = LocalDateTime.now().withHour(h).withMinute(m)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        if (name == "startTime") {
            startTime = dateTime.format(formatter)
        } else {
            endTime = dateTime.format(formatter)
        }
    }

    fun handleChangeClient(it: Client){

    }
//    if(appointmentId == "new"){}
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        if(mode=="edit"){
            OldFormActionBar(mode,
                onCancel = {
                    navController.navigate("appointments")
                },
                onBack = {
                    navController.navigate("appointments")
                },
                onSave = ::handleSubmit,
                onEdit = { mode = "edit" }
            )

//            AppointmentForm(
//                client = client,
//                title = title,
//                notes = notes,
//                date = date,
//                startTime = startTime,
//                endTime = endTime,
//                onValChange = { name, value ->
//                    if (name == "title") {
//                        title = value
//                    }
//                    if (name == "notes") {
//                        notes = value
//                    }
//                },
//                onDateChange = ::handleChangeDate,
//                onTimeChange = ::handleChangeTime,
//                onUserChange = { name ->
//                    if(name == "client"){
//                        mode = "add client"
//                    }
//                }
//            )
        }else if(mode == "add client") {
//            SearchClientScreen(
//                clientViewModel,
//                onSelect= { it ->
//                    client = BaseAccount(it.id, it.username)
//                    mode = "edit"
//                }
//            )
        }else{ // view
            OldFormActionBar(mode,
                onCancel = {
                    navController.navigate("appointments")
                },
                onBack = {
                    navController.navigate("appointments")
                },
                onSave = ::handleSubmit,
                onEdit = { mode = "edit" }
            )
            Text(text=title)
            Text(text="${date}  ${startTime} - ${endTime}")
//            Text(text="Meet client ${client.username}")
            Text(text=notes)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewAppointmentScreen(){
//    AppointmentScreen(navController = rememberNavController(), "new")
}

//    Column() {
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

//    }

//
//@Composable
//fun AppointmentScreen(
//    navController: NavController,
//    appointmentId: String?,
//    viewModel: AppointmentViewModel,
//    buildingViewModel: LayoutViewModel,
//){
//    val appointments: List<Appointment> by viewModel.appointments.observeAsState(arrayListOf())
//    val appointment: Appointment? = appointments.find { it._id == appointmentId }
//
//    val buildings by buildingViewModel.buildings.observeAsState()
//
//    var client by remember { mutableStateOf("") }
//    var title by remember { mutableStateOf("") }
//    var notes by remember { mutableStateOf("") }
//    var date by remember { mutableStateOf("") }
//    var startTime by remember { mutableStateOf("") }
//    var endTime by remember { mutableStateOf("") }
//
//
//    val verticalScrollState = rememberScrollState()
//
//    LaunchedEffect(key1 = appointmentId){
//        if(appointmentId != null && appointmentId != "new") {
//            buildingViewModel.getLayoutsByAppointmentId(appointmentId)
//        }
//    }
//
//    LaunchedEffect(key1 = appointment){
//        if(appointment != null) {
//            client = appointment.client.username
//            title = appointment.title
//            notes = appointment.notes
//            val startList = appointment.startTime.split(".")
//            val start = startList[0].split("T")
//            date = start[0]
//            startTime = start[1]
//
//            val endList = appointment.endTime.split(".")
//            endTime = endList[0].split("T")[1]
//        }
//    }
//
//    fun handleEdit(){
//
//    }
//
//    fun handleDelete(){
//
//    }
//
//    fun handleViewLayout(){
//        navController.navigate("appointments/${appointmentId}/buildings")
//    }
//
//    Card {
//        Column(modifier = Modifier
//            .padding(12.dp)
//            .verticalScroll(verticalScrollState)) {
//            if(appointment !== null) {
//                Row() {
//                    ActionButton(Icons.Outlined.Home, "Layout", ::handleViewLayout)
//                    ActionButton(Icons.Outlined.Edit, "Edit", ::handleEdit)
//                    ActionButton(Icons.Outlined.Delete, "Delete", ::handleDelete)
//                }
//                Input(
//                    value = client,
//                    onValueChange = { client = it },
//                    label = "Client",
//                    readOnly = true,
//                )
//
//                Input(
//                    value = title,
//                    onValueChange = { title = it },
//                    label = "Appointment",
//                )
//
//                Input(
//                    value = notes,
//                    onValueChange = { notes = it },
//                    label = "Notes",
//                )
//
//                Input(
//                    value = date,
//                    onValueChange = { },
//                    label = "Notes",
//                )
//
//                fun handleChangeDate(y:Int, m: Int, d: Int){
//                    val dateTime = LocalDateTime.now().withYear(y).withMonth(m+1).withDayOfMonth(d)
//                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//                    date = dateTime.format(formatter)
//                }
//
//                DatePicker(LocalContext.current, "Date", date, ::handleChangeDate)
//
//
//                fun handleChangeStartTime(h:Int, m: Int){
//                    val dateTime = LocalDateTime.now().withHour(h).withMinute(m)
//                    val formatter = DateTimeFormatter.ofPattern("HH:mm")
//                    startTime = dateTime.format(formatter)
//                }
//
//                TimePicker(LocalContext.current, "Start Time", startTime, ::handleChangeStartTime)
//
//                fun handleChangeEndTime(h:Int, m: Int){
//                    val dateTime = LocalDateTime.now().withHour(h).withMinute(m)
//                    val formatter = DateTimeFormatter.ofPattern("HH:mm")
//                    endTime = dateTime.format(formatter)
//                }
//
//                TimePicker(LocalContext.current, "End Time", endTime, ::handleChangeEndTime)
//
//            }
//
//
//            Button(onClick = {
//                if(buildings?.isEmpty() == true && appointmentId!=null) {
//                    // create default building
//                    buildingViewModel.createLayout(
//                        Building("",
//                            "New Address",
//                            "New building",
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
//                // navController.navigate("appointments/${appointmentId}/buildings")
//            }) {
//                Text(text = "Create Layout")
//            }
//
//            Button(onClick = {
//                // navController.navigate("appointments/${appointmentId}/buildings")
//            }) {
//                Text(text = "Submit")
//            }
//
//        }
//    }
//}

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
