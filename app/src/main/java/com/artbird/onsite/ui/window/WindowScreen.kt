package com.artbird.onsite.ui.window

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.utils.convertToFloat
import com.artbird.onsite.ui.utils.convertToInt
import com.artbird.onsite.ui.utils.floatToString
import com.artbird.onsite.ui.utils.intToString


data class UIWindowWidth(
    var top: String,
    var mid: String,
    var bottom: String,
)
data class UIWindowHeight(
    var left: String,
    var mid: String,
    var right: String,
)
data class UIBafflePosition(
    var top: String,
    var bottom: String,
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun WindowScreen(
    windowIndex: Int,
    windows: List<Window>,
//    navController: NavController,
//    windowId: String?,
    windowViewModel: WindowViewModel,
    onPrevious: (step: String, index: Int) -> Unit
) {

//    val layouts by layoutViewModel.layouts.observeAsState()
//    val appointment by appointmentViewModel.appointment.observeAsState()
//
//    LaunchedEffect(key1 = appointmentId){
//        if(appointmentId != null) {
//            appointmentViewModel.getAppointment(appointmentId)
//            layoutViewModel.getLayoutsByAppointmentId(appointmentId)
//            quoteViewModel.getQuotesByAppointmentId(appointmentId)
//        }
//    }
    val window by windowViewModel.window.observeAsState(windows[windowIndex])


    val scrollState = rememberScrollState()

    var name by remember { mutableStateOf(window?.name ?: "") }
    var notes by remember { mutableStateOf("") }
    var width by remember { mutableStateOf(UIWindowWidth("", "", "")) }
    var height by remember { mutableStateOf(UIWindowHeight("", "", "")) }
    var numOfWindows by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var direction by remember { mutableStateOf("") }
    var installPosition by remember { mutableStateOf("") }

    var bladeSize by remember { mutableStateOf("") }
    var rodStyle by remember { mutableStateOf("") }
    var leverType by remember { mutableStateOf("") }
    var bafflePosition by remember { mutableStateOf(UIBafflePosition("", "")) }


//    LaunchedEffect(key1 = windowId){
//        if(windowId != null) {
//            windowViewModel.getWindow(windowId)
//        }
//    }

    LaunchedEffect(key1 = window ){
        if(window != null) {
            name = window?.name.toString()
            notes = window?.notes.toString()
//            width = UIWindowWidth(
//                floatToString(window?.width!!.top),
//                floatToString(window?.width!!.mid),
//                floatToString(window?.width!!.bottom),
//            )
//            height = UIWindowHeight(
//                floatToString(window?.height!!.left),
//                floatToString(window?.height!!.mid),
//                floatToString(window?.height!!.right),
//            )
//            numOfWindows = intToString(window?.numOfWindows!!)
            type = window ?. type !!
//            position = window?.position!!
//                    direction = window ?. direction !!
//            installPosition = window?.installPosition!!
//                    bladeSize = window ?. bladeSize !!
//            rodStyle = window?.rodStyle!!
//                    leverType = window ?. leverType !!
//            bafflePosition = UIBafflePosition(
//                floatToString(window?.bafflePosition!!.top),
//                floatToString(window?.bafflePosition!!.bottom),
//            )
        }
    }

    fun clickBladeOption(it: OptionItem){
        bladeSize = it.label
    }

    val bladeOptions = listOf(
        OptionItem("2.5 Inch", "2.5in"),
        OptionItem("3.5 Inch","3.5in"),
    )

    fun clickLeverTypeOption(it: OptionItem){
        leverType = it.label
    }

    val leverOptions = listOf(
        OptionItem("Invisible", "invisible"),
        OptionItem("Others", "others"),
    )

    fun clickInstallOption(it: OptionItem){
        installPosition = it.label
    }

    fun handleBack(){
        onPrevious("windows", windowIndex)
    }

    fun handleSubmit(){
//        val data = Window(
//            name = name,
//            notes = notes,
//            height = WindowHeight(
//                convertToFloat(height.left),
//                convertToFloat(height.mid),
//                convertToFloat(height.right),
//            ),
//            width = WindowWidth(
//                convertToFloat(width.top),
//                convertToFloat(width.mid),
//                convertToFloat(width.bottom)
//            ),
//            type = type,
//            position = position,
//            direction = direction,
//            numOfWindows = convertToInt(numOfWindows),
//            installPosition = installPosition,
//            bafflePosition = BafflePosition(
//                convertToFloat(bafflePosition.top),
//                convertToFloat(bafflePosition.bottom)
//            ),
//            bladeSize = bladeSize,
//            rodStyle = rodStyle,
//            leverType = leverType,
//            room = BaseEntity(window?.room!!._id, window?.room!!.name), // empty
//            appointment = BaseAppointment(window?.appointment!!._id, window?.appointment!!.title) // empty
//        )
//        if (window != null && window?._id!!.isNotEmpty()) {
//            val id = window?._id!!
//            windowViewModel.updateWindow(id, data)
//        } else {
//            windowViewModel.createWindow(data)
//        }

        handleBack()
//        navController.popBackStack()
    }

    val installPositionOptions = listOf(
        OptionItem("Interal","internal"),
        OptionItem("External","external"),
    )

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        OldFormActionBar(mode="edit",
            onCancel=::handleBack,
            onBack=::handleBack,
            onSave=::handleSubmit
        )

        Column(Modifier.verticalScroll(scrollState)) {

            Input(
                value = name,
                onValueChange = { name = it },
                label = "Name",
            )

            Input(
                value = notes,
                onValueChange = { notes = it },
                label = "Notes",
            )


            Text(text = "Width",
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 0.dp))
            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                NumberInput(
                    value = width.top,
                    onValueChange = {
                        width = UIWindowWidth(it, width.mid, width.bottom)
                                    },
                    label = "Top",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                NumberInput(
                    value = width.mid,
                    onValueChange = {
                            width = UIWindowWidth(width.top, it, width.bottom)
                                    },
                    label = "Mid",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                NumberInput(
                    value = width.bottom,
                    onValueChange = {
                        width = UIWindowWidth(width.top, width.mid, it)
                                    },
                    label = "Bottom",
                    modifier = Modifier.weight(1f)
                )
            }

            Text(text = "Height",
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 0.dp))
            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {

                NumberInput(
                    value = height.left,
                    onValueChange = { height = UIWindowHeight(it, height.mid, height.right) },
                    label = "Left",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                NumberInput(
                    value = height.mid,
                    onValueChange = { height = UIWindowHeight(height.left, it, height.right) },
                    label = "Mid",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                NumberInput(
                    value = height.right,
                    onValueChange = { height = UIWindowHeight(height.left, height.mid, it) },
                    label = "Right",
                    modifier = Modifier.weight(1f)
                )
            }

            NumberInput(
                value = numOfWindows.toString(),
                onValueChange = {numOfWindows = it },
                label = "Number of Windows",
            )
            Input(
                value = position,
                onValueChange = { position = it },
                label = "Position",
            )
            Input(
                value = type,
                onValueChange = { type = it },
                label = "Type",
            )
            Select(
                value = bladeSize,
//                onValueChange = { bladeSize = it },
                label = "Blade Size",
                options = bladeOptions
            )

            Select(
                value = leverType,
//                onValueChange = { leverType = it },
                label = "Lever Type",
                options = leverOptions
            )

            Input(
                value = rodStyle,
                onValueChange = { rodStyle = it },
                label = "Rod Style",
            )
            Input(
                value = direction,
                onValueChange = { direction = it },
                label = "Opening Direction",
            )

            Text(text = "Baffle Position",
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 0.dp))
            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                NumberInput(
                    value = bafflePosition.top,
                    onValueChange = { bafflePosition = UIBafflePosition(it, bafflePosition.bottom) },
                    label = "Top",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                NumberInput(
                    value = bafflePosition.bottom,
                    onValueChange = { bafflePosition = UIBafflePosition(bafflePosition.top, it) },
                    label = "Bottom",
                    modifier = Modifier.weight(1f)
                )
            }

            Select(
                value = installPosition,
//                onValueChange = { installPosition = it },
                label = "Install Position",
                options = installPositionOptions
            )
        }

    }
}

