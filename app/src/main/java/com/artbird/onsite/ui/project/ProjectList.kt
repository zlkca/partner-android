package com.artbird.onsite.ui.project

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.BaseProject
import com.artbird.onsite.domain.Client
import com.artbird.onsite.domain.Project
import com.artbird.onsite.ui.auth.LoginForm
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.components.ListActionBar
import com.artbird.onsite.ui.components.LongButton
import com.artbird.onsite.ui.theme.SLTheme


@Composable
fun ProjectList(
    projects: List<BaseProject>,
    selectedIndex: Int,
    onSelect: (index: Int) -> Unit = { i: Int -> },
    onDelete: (clientId: String) -> Unit = {s: String ->},
    onAdd: () -> Unit = {},
) {

    var formOpened by remember { mutableStateOf(false) }

    // toolbar handler


    fun handleEdit() {
        formOpened = true
    }

    fun handleDelete(index: Int) {
//        val clientId = clients?.get(index)?.id!!
//        onDelete(clientId)
    }

    fun handleView() {
    }


    fun getProjectLabel(item: Client, name: String): String {
        return item.username
    }

    fun handleSubmit(){
        formOpened = false
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        ListActionBar(items = listOf(
            ActionChip("Client", onClick = onAdd),
        ))

        if (projects != null && projects.isNotEmpty()!!) {
//            com.artbird.onsite.ui.components.List<BaseProject>(
//                projects!!,
//                selectedIndex,
//                fields = listOf("name"),
//                onGetLabel = ::getProjectLabel,
//                onSelect = onSelect,
//            )
        }
    }

}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewLoginForm(){
    SLTheme {
        LoginForm(
            onChange = { name, value -> },
            onSubmit = { password, email -> }
        )
    }
}