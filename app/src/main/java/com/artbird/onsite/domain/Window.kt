package com.artbird.onsite.domain


//data class WindowListItem(
//    var _id: String,
//    var name: String,
//    var notes: String,
//)



data class WindowWidth(
    var unit: String = "inch",
    var top: String = "", // eg."12in13" = 12 and 13/16 inches, inch fragments
    var mid: String = "",
    var bottom: String = "",
)

data class WindowHeight(
    var unit: String = "inch",
    var left: String = "", // eg."12in13" = 12 and 13/16 inches, inch fragments
    var mid: String = "",
    var right: String = "",
)

data class BafflePosition(
    var unit: String = "inch",
    var top: String = "", // eg."12in13" = 12 and 13/16 inches, inch fragments
    var bottom: String = "",
)
data class LockerSize(
    var unit: String = "inch",
    var top: String = "", // eg."12in13" = 12 and 13/16 inches, inch fragments
    var bottom: String = "",
    var left: String = "",
    var right: String = "",
)
data class LockerPosition(
    var vertical: String,
    var horizontal: String,
)
data class Locker(
    var id: String = "",
    var position: LockerPosition = LockerPosition("LM", "RB"),
    var size: LockerSize = LockerSize(0,0,0,0)
)

data class Window (
    var _id: String = "",
    val name: String = "",
    val notes: String = "",
    val width: WindowWidth = WindowWidth(),
    val height: WindowHeight = WindowHeight(),
    val type: String = "Normal",
    val position: String = "L",
    var openingDirection: String = "L",
    var numOfWindows: Int = 0,
    var mountPosition: String = "L",
    val bafflePosition: BafflePosition = BafflePosition(),
    val bladeSize: String = "",
    val leverType: String = "",
    val frameStyle: String = "L Frame",
    val lockers: List<Locker> = listOf(),
    val originalFrameStyle: String = "Normal",
    val roomId: String = "",
    val appointmentId: String = "",
)

data class ImparialLength(
    var inches: String,
    var leftover: String,
)

data class UIWindowWidth(
    var top: ImparialLength = ImparialLength("0", "0"),
    var mid: ImparialLength = ImparialLength("0", "0"),
    var bottom: ImparialLength = ImparialLength("0", "0"),
)
data class UIWindowHeight(
    var left: ImparialLength = ImparialLength("0", "0"),
    var mid: ImparialLength = ImparialLength("0", "0"),
    var right: ImparialLength = ImparialLength("0", "0"),
)
data class UIBafflePosition(
    var top: ImparialLength = ImparialLength("0", "0"),
    var bottom: ImparialLength = ImparialLength("0", "0"),
)
//val scrollState = rememberScrollState()
//
//var name by remember { mutableStateOf("") }
//var notes by remember { mutableStateOf("") }
//var height by remember { mutableStateOf(WindowHeight("", "", "")) }
//var width by remember { mutableStateOf(WindowWidth("", "", "")) }
//var numOfWindows by remember { mutableStateOf("") }
//var position by remember { mutableStateOf("") }
//var type by remember { mutableStateOf("") }
//var bladeSize by remember { mutableStateOf("") }
//var rodStyle by remember { mutableStateOf("") }
//var leverType by remember { mutableStateOf("") }
//var direction by remember { mutableStateOf("") }
//var bafflePosition by remember { mutableStateOf(BafflePosition("","")) }
//var installPosition by remember { mutableStateOf("") }
//
//fun clickBladeOption(it: OptionItem){
//    bladeSize = it.label
//}
//
//val bladeOptions = listOf(
//    OptionItem("2.5 Inch",::clickBladeOption),
//    OptionItem("3.5 Inch",::clickBladeOption),
//)
//
//fun clickLeverTypeOption(it: OptionItem){
//    leverType = it.label
//}
//
//val leverOptions = listOf(
//    OptionItem("Invisible",::clickLeverTypeOption),
//    OptionItem("Others",::clickLeverTypeOption),
//)
//
//fun clickInstallOption(it: OptionItem){
//    installPosition = it.label
//}
//
//val installPostionOptions = listOf(
//    OptionItem("Interal",::clickInstallOption),
//    OptionItem("External",::clickInstallOption),
//)