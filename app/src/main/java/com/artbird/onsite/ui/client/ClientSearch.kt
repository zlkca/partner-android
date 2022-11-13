package com.artbird.onsite.ui.client




import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.content.res.Configuration

import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.domain.*
import com.artbird.onsite.ui.components.*

@Composable
fun ClientSearch(
    keyword: String,
    clients: List<Client> = listOf(),
    onSearch: (keyword: String) -> Unit = {},
    onSelect: (index: Int) -> Unit = {},
    onBack:() -> Unit = {},
    onClear:() -> Unit = {},
){
    Column() {
        SearchList<Client>(
            keyword,
            "Find Client",
            clients,
//        selectedIndex = selectedIndex,
            onSelect = onSelect,
            onSearch = onSearch,
            onBack = onBack,
            onClear = onClear,
            itemContent = { it, selected, index -> ClientListItem(it, selected, index) },
        )
    }
}


@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewClientSearch(){
    val clients = listOf<Client>(
        Client(id="1", username = "Jacky", email="jacky@gmail.com", phone="416-123-4567"),
        Client(id="2", username = "Sydney", email="sydney@gmail.com", phone="416-123-4567")
    )
    SLTheme {
        ClientSearch(
            "",
            clients,
        )
    }
}