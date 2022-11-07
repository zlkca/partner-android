package com.artbird.onsite.ui.components

//import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.Icon
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.LeadingIconTab

//import androidx.compose.material3.TopAppBar

import androidx.compose.material3.Text
import androidx.compose.material3.TabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//import androidx.compose.material3.PagerState

//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//
//import androidx.compose.ui.input.key.Key.Companion.Music
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//
//import androidx.compose.ui.unit.sp
//import com.shutterlux.onsite.MenuItem
//import com.shutterlux.onsite.R

//import kotlinx.coroutines.launch


data class TabItem(val label: String, val icon: ImageVector?, val showPage: @Composable () -> Unit)


@Composable
fun MyTab(selected: Boolean, title: String, icon: ImageVector?, onClick: () -> Unit){
    Tab(selected, onClick) {
        Column(
            Modifier
                .padding(10.dp)
                .height(32.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .size(10.dp)
                    .align(Alignment.CenterHorizontally)
//                .background(color = if (selected) Color.Red else Color.White)
            )

            Row(){
                if(icon != null) {
                    Icon(icon, contentDescription = title, modifier=Modifier.padding(bottom=2.dp, end = 8.dp))
                }
                Text(text = title)
            }

        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tabs(tabs: List<TabItem>, selectedIndex: Int, onClick: (index: Int) -> Unit) { // , pagerState: PagerState
    Column {
        TabRow(selectedTabIndex = selectedIndex) {
            tabs.forEachIndexed { index, tabItem ->
                MyTab(
                    selected = selectedIndex == index,
                    title = tabItem.label,
                    icon = tabItem.icon,
                    onClick = { onClick(index) },
                )
            }
        }
    }
//    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
//    TabRow(
////        selectedTabIndex = pagerState.currentPage,
////        backgroundColor = colorResource(id = R.color.colorPrimaryDark),
//        contentColor = Color.White,
//        indicator = { tabPositions ->
//            TabRowDefaults.Indicator(
////                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
//            )
//        }) {
//        tabs.forEachIndexed { index, tab ->
//            // OR Tab()
//            LeadingIconTab(
////                icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "") },
//                text = { Text(tab.title) },
////                selected = pagerState.currentPage == index,
//                onClick = {
//                    scope.launch {
////                        pagerState.animateScrollToPage(index)
//                    }
//                },
//            )
//        }
//    }
}

//@Composable
//fun TopBar() {
////    TopAppBar(
////        title = { androidx.compose.material3.Text(text = "Hello") },
////        backgroundColor = Color.Blue,
////        contentColor = Color.White
////    )
//    Text(text = "Hello")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun TopBarPreview() {
//    TopBar()
//}
@Composable
fun Tab1Page(){
    Text("Page 1")
}

@Composable
fun Tab2Page(){
    Text("Page 2")
}

@Composable
fun TabContents(tabs: List<TabItem>, selectedIndex: Int){
    tabs[selectedIndex].showPage()
}

//@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    var state by remember { mutableStateOf(0) }
    fun handleSelect( index: Int ){
        state = index
    }
    val tabs = listOf(
        TabItem("Area1", Icons.Filled.CalendarToday) { Tab1Page() },
        TabItem("Area2", Icons.Filled.Dashboard) { Tab2Page() },
    )

    Column() {
        Tabs(tabs, state, ::handleSelect );
        TabContents(tabs, state)

    }
}