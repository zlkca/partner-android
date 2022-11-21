package com.artbird.onsite.ui.dashboard


import androidx.navigation.NavController
import com.artbird.onsite.ui.client.ProfileViewModel


import androidx.compose.material3.*
import androidx.compose.runtime.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController, viewModel: ProfileViewModel) {
//    Button(onClick = { navController.navigate("home") }) {
//        Text(text = "From Dashboard")
//    }

        var state by remember { mutableStateOf(0) }
        val titles = listOf("Tab 1", "Tab 2", "Tab 3 with lots of text")

//        Scaffold(content = {
//                Column {
//                        Box(
//                                modifier
//                                        .padding(5.dp)
//                                        .fillMaxSize()
//                                        .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(5.dp))
//                        )
//                        TabRow(selectedTabIndex = state) {
//                                titles.forEachIndexed { index, title ->
//                                        Tab(
//                                                text = { Text(title) },
//                                                selected = state == index,
//                                                onClick = { state = index }
//                                        )
//                                }
//                        }
//                        Text(
//                                modifier = Modifier.align(Alignment.CenterHorizontally),
//                                text = "Text tab ${state + 1} selected",
//                                style = MaterialTheme.typography.bodyLarge
//                        )
//                }
//        }
}