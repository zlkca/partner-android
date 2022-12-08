package com.artbird.onsite.ui.client

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.Project
import com.artbird.onsite.ui.components.DetailsViewActionBar
import com.artbird.onsite.ui.project.ProjectDetails
import com.artbird.onsite.ui.project.ProjectViewModel

@Composable
fun ClientProjectDetailsScreen(
    navController: NavController,
    projectViewModel: ProjectViewModel,
    clientId: String,
    projectId: String,
) {
    val project by projectViewModel.project.observeAsState(Project())

    LaunchedEffect(key1 = projectId) {
        if (projectId != null && projectId != "new") {
            projectViewModel.getProject(projectId)
        }
    }

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        DetailsViewActionBar(
            onBack = {
                Log.d("zlk", "From project detail back to client detail: clients/${clientId}")
                navController.navigate("clients/${clientId}")
                     },
            readOnly = true
        )

        if(project!=null) {
            ProjectDetails(project!!)
        }
    }
}
