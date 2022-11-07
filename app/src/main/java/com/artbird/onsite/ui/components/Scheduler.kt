package com.artbird.onsite.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NavigateBefore
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt
import java.time.format.DateTimeFormatter

data class Event(
    val id: String?=null,
    val name: String,
    val color: Color,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val description: String? = null,
)

@Composable
fun BasicEvent(
    event: Event,
    onClick: (event: Event) -> Unit,
    onSelect: (event: Event) -> Unit = {},
    modifier: Modifier = Modifier,
) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(end = 2.dp, bottom = 2.dp)
                .background(event.color, shape = RoundedCornerShape(4.dp))
                .padding(4.dp)
                .clickable(onClick={
                    onClick(event)
                })
        ) {

            Row() {
                Column(
                    modifier = Modifier.weight(5f)
                ) {
                    Text(
                        text = event.start.toString()
//            text = "${event.start.format(EventTimeFormatter)} - ${event.end.format(EventTimeFormatter)}",
//            style = MaterialTheme.typography.caption,
                    )

                    Text(
                        text = event.name,
//            style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                    )
                }

//                Column(
//                    modifier = Modifier.weight(1f)
//                ) {
//                    ActionButton(Icons.Outlined.Straighten, "Measure", { onSelect(event) })
//                }

            }


            if (event.description != null) {
                Text(
                    text = event.description,
//                style = MaterialTheme.typography.body2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }
}

private val sampleEvents = listOf<Event>(
    Event(
        id = "1",
        name = "Google I/O Keynote",
        color = Color(0xFFAFBBF2),
        start = LocalDateTime.parse("2022-08-07T09:00:00"),
        end = LocalDateTime.parse("2022-08-07T11:00:00"),
        description = "Tune in to find out about how we're furthering our mission to organize the world’s information and make it universally accessible and useful.",
    ),
)

class EventsProvider : PreviewParameterProvider<Event> {
    override val values = sampleEvents.asSequence()
}


private class EventDataModifier(
    val event: Event,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = event
}
private fun Modifier.eventData(event: Event) = this.then(EventDataModifier(event))

@Composable
fun BasicSchedule(
    events: List<Event>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (
        event: Event,
        onClick: (event: Event) -> Unit,
        onSelect: (event: Event) -> Unit,
    ) -> Unit = { it, onClick, onSelect ->
        BasicEvent(event = it, onClick, onSelect) },
    minDate: LocalDate = events.minByOrNull(Event::start)!!.start.toLocalDate(),
//    maxDate: LocalDate = events.maxByOrNull(Event::end)!!.end.toLocalDate(),
    dayWidth: Dp,
    hourHeight: Dp,
    startHour: Int,
    onClick: (event: Event) -> Unit,
    onSelect: (event: Event) -> Unit,
) {
    val numDays = 1 // ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 1
    val dividerColor = Color.LightGray // if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray

    Layout(
        content = {
            if(events!=null && events.isNotEmpty()) {
                events.sortedBy(Event::start).forEach { event ->
                    Box(modifier = Modifier.eventData(event)) {
                        eventContent(event, onClick, onSelect)
                    }
                }
            }
        },
        modifier = Modifier
            .drawBehind {
                repeat(23 - startHour) {
                    drawLine(
                        dividerColor,
                        start = Offset(0f, (it + 1) * hourHeight.toPx()),
                        end = Offset(size.width, (it + 1) * hourHeight.toPx()),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                repeat(numDays - 1) {
                    drawLine(
                        dividerColor,
                        start = Offset((it + 1) * dayWidth.toPx(), 0f),
                        end = Offset((it + 1) * dayWidth.toPx(), size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
    ) { measureables, constraints ->
        val height = hourHeight.roundToPx() * (24 - startHour)
        val width = dayWidth.roundToPx() * numDays
        val placeablesWithEvents = measureables.map { measurable ->
            val event = measurable.parentData as Event
            val eventDurationMinutes = ChronoUnit.MINUTES.between(event.start, event.end)
            val eventHeight = ((eventDurationMinutes / 60f) * hourHeight.toPx()).roundToInt()
            val placeable = measurable.measure(
                constraints.copy(
                    minWidth = dayWidth.roundToPx(),
                    maxWidth = dayWidth.roundToPx(),
                    minHeight = eventHeight,
                    maxHeight = eventHeight
                )
            )
            Pair(placeable, event)
        }

        layout(width, height) {
            placeablesWithEvents.forEach { (placeable, event) ->
                val eventOffsetMinutes = ChronoUnit.MINUTES.between(LocalTime.MIN, event.start.toLocalTime())
                val eventY = ((eventOffsetMinutes / 60f - startHour) * hourHeight.toPx()).roundToInt()
                val eventOffsetDays = ChronoUnit.DAYS.between(minDate, event.start.toLocalDate()).toInt()
                val eventX = eventOffsetDays * dayWidth.roundToPx()
                placeable.place(eventX, eventY)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun EventPreview(
//    @PreviewParameter(EventsProvider::class) event: Event,
//) {
////    WeekScheduleTheme {
//        BasicEvent(event, modifier = Modifier.sizeIn(maxHeight = 64.dp))
////    }
//}
@Composable
fun BasicDayHeader(
    day: LocalDate,
    modifier: Modifier = Modifier,
) {
//    val DayFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd")
    Text(
        text = day.toString(),
                // day.format(DayFormatter),
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    )
}

@Composable
fun ScheduleHeader(
    minDate: LocalDate,
//    maxDate: LocalDate,
    dayWidth: Dp=100.dp,
    modifier: Modifier = Modifier,
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
    onPrevClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
) {
    Row() {
            ActionButton(Icons.Outlined.NavigateBefore, "View", onPrevClick)
            Box(modifier = Modifier.width(dayWidth).padding(top=10.dp)) {
                dayHeader(minDate.plusDays(0))
            }
            ActionButton(Icons.Outlined.NavigateNext, "View", onNextClick)
    }
//        val numDays = 1 // ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 1
//                repeat(numDays) { i ->
//                dayHeader(minDate.plusDays(i.toLong()))
}

private val HourFormatter = DateTimeFormatter.ofPattern("h a")

@Composable
fun BasicSidebarLabel(
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    Text(
        text = time.format(HourFormatter),
        modifier = modifier
            .fillMaxHeight()
            .padding(4.dp)
    )
}

@Composable
fun ScheduleSidebar(
    startHour: Int,
    hourHeight: Dp,
    modifier: Modifier = Modifier,
    label: @Composable (time: LocalTime) -> Unit = { BasicSidebarLabel(time = it) },
) {
    Column(modifier = modifier) {
        val startTime = LocalTime.MIN

        // start from 7:00 AM
        repeat(24 - startHour) { i ->
            Box(modifier = Modifier.height(hourHeight)) {
                label(startTime.plusHours((i + startHour).toLong()))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ScheduleSidebarPreview() {
////    WeekScheduleTheme {
//        ScheduleSidebar(hourHeight = 64.dp)
////    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun BasicSidebarLabelPreview() {
////    WeekScheduleTheme {
//        BasicSidebarLabel(time = LocalTime.NOON, Modifier.sizeIn(maxHeight = 64.dp))
////    }
//}

@Composable
fun Scheduler(
    events: List<Event>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (event: Event,
                               onClick: (event: Event) -> Unit,
                               onSelect: (event: Event) -> Unit
    ) -> Unit = { it, onClick, onSelect ->
        BasicEvent(event = it, onClick, onSelect)
                },
    minDate: LocalDate = events.minByOrNull(Event::start)!!.start.toLocalDate(),
//    maxDate: LocalDate = events.maxByOrNull(Event::end)!!.end.toLocalDate(),
    onClickEvent: (event: Event) -> Unit,
    onSelect: (event: Event) -> Unit,
    onPrevDay: () -> Unit = {},
    onNextDay: () -> Unit = {},
) {
    val dayWidth = 320.dp
    val hourHeight = 56.dp

    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()
    var sidebarWidth by remember { mutableStateOf(0) }
//    var headerHeight by remember { mutableStateOf(0) }

    Column(modifier = modifier) {
        ScheduleHeader(
            minDate = minDate,
//            maxDate = maxDate,
//            dayHeader = dayHeader,
            dayWidth = 100.dp,
            modifier = Modifier
                .padding(start = with(LocalDensity.current) { sidebarWidth.toDp() })
                .horizontalScroll(horizontalScrollState),
//                .onGloballyPositioned { headerHeight = it.size.height }
            onPrevClick = onPrevDay,
            onNextClick = onNextDay
        )
        Row(modifier = Modifier
//            .weight(1f)
//            .align(Alignment.Start)
            .verticalScroll(verticalScrollState)
        )
        {
            ScheduleSidebar(
                startHour = 7,
                hourHeight = hourHeight,
                modifier = Modifier
                    .weight(1f)
//                    .verticalScroll(verticalScrollState)
//                    .onGloballyPositioned { sidebarWidth = it.size.width }
            )
            BasicSchedule(
                events = events,
                eventContent = eventContent,
                minDate = minDate,
//                maxDate = maxDate,
                dayWidth = dayWidth,
                hourHeight = hourHeight,
                startHour = 7,
                onClick=onClickEvent,
                onSelect=onSelect,
                modifier = Modifier
                    .weight(3f)
//                    .verticalScroll(verticalScrollState)
//                    .horizontalScroll(horizontalScrollState)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ScheduleHeaderPreview() {
////    WeekScheduleTheme {
//        ScheduleHeader(
//            minDate = LocalDate.now(),
//            maxDate = LocalDate.now().plusDays(5),
//            dayWidth = 256.dp,
//        )
////    }
//}

//@Preview(showBackground = true)
//@Composable
//fun BasicDayHeaderPreview() {
////    WeekScheduleTheme {
//        BasicDayHeader(day = LocalDate.now())
////    }
//}
//
@Preview(showBackground = true)
@Composable
fun SchedulerPreview() {
    var today by remember { mutableStateOf(LocalDate.now()) }

    fun handleView(e: Event){
        val my = e.id
    }
    Scheduler(
         sampleEvents,
        onClickEvent=::handleView,
        onSelect = ::handleView,
        minDate=today,
        onNextDay = {today = today.plusDays(1)},
        onPrevDay = {today = today.plusDays(-1)}
    )
}