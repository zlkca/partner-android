package com.artbird.onsite.ui.project

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController

@Composable
fun ProjectListScreen(
    navController: NavController,
    projectViewModel: ProjectViewModel,
    recommenderId: String,

){
    val projects by projectViewModel.projects.observeAsState()
    var selectedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = recommenderId) {
        if (recommenderId != null && recommenderId != "new") {
            projectViewModel.getProjectsByRecommenderId(recommenderId)
        }
    }

    ProjectList(
        projects!!,
        selectedIndex,
        onSelect = { index ->
            val projectId = projects!![index]._id
            navController.navigate("projects/${projectId}")
        },
        onAdd = {
            navController.navigate("projects/new/form")
        }
    )
}