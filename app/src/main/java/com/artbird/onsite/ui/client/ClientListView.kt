package com.artbird.onsite.ui.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artbird.onsite.domain.Client
import com.artbird.onsite.ui.components.ActionChip
import com.artbird.onsite.ui.components.ListActionBar

@Composable
fun ClientListView(
    clients: List<Client>?,
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
        val clientId = clients?.get(index)?.id!!
        onDelete(clientId)
    }

    fun handleView() {
    }


    fun getClientLabel(item: Client, name: String): String {
        return item.username
    }

    fun handleSubmit(){
        formOpened = false
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
//        Row() {
//            ActionButton(Icons.Outlined.Visibility, "View", ::handleView)
//            ActionButton(Icons.Outlined.Edit, "Edit", ::handleEdit)
//            ActionButton(Icons.Outlined.Add, "Add", ::handleAdd)
//            ActionButton(Icons.Outlined.Delete, "Delete", ::handleDelete)
//        }

        ListActionBar(items = listOf(
            ActionChip("Client", onClick = onAdd),
        ))

        if (clients != null && clients?.isNotEmpty()!!) {
            com.artbird.onsite.ui.components.List<Client>(
                clients!!,
                selectedIndex,
                fields = listOf("name"),
                onGetLabel = ::getClientLabel,
                onSelect = onSelect,
            )
        }
    }

}