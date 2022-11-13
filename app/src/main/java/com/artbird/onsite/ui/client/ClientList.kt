package com.artbird.onsite.ui.client

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.artbird.onsite.domain.Client
import com.artbird.onsite.ui.components.Body3
import com.artbird.onsite.ui.components.Title2
import com.artbird.onsite.ui.theme.SLTheme


@Composable
fun ClientListItem(item: Client, selected: Boolean, index:Int){
    val colorScheme = MaterialTheme.colorScheme
    Column(){
        Title2(
            text = item.username,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )

        Body3(
            text = item.email,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground,
        )

        Body3(
            text = item.phone,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground,
        )
    }
}

@Composable
fun ClientList(
    clients: List<Client>,
    selectedIndex: Int,
    onSelect: (index: Int) -> Unit = { i: Int -> },
) {
    Column() {
        com.artbird.onsite.ui.components.List<Client>(
            clients,
            selectedIndex,
            onSelect = onSelect,
            itemContent = { it, selected, index ->
                ClientListItem(item=it, selected=selected, index =index)
            }
        )
    }

}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewClientList(){
    val clients = listOf<Client>(
        Client(username="Jet Li", email="jet@shutterlux.ca", phone="123-456-7890", created="2022-11-07"),
        Client(username="Nelson", email="nelson@shutterlux.ca", phone="123-456-7890", created="2022-11-07"),
        Client(username="claudia", email="claudia@shutterlux.ca", phone="123-456-7890", created="2022-11-07"),
    )
    SLTheme {
        ClientList(
            clients,
            0
        )
    }
}
