package com.artbird.onsite.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.artbird.onsite.domain.GeoPlace
import com.artbird.onsite.domain.GooglePrediction
import com.artbird.onsite.domain.GooglePredictionTerm
import com.artbird.onsite.ui.theme.SLTheme



@Composable
fun PlaceListItem(item: GeoPlace, selected: Boolean, index:Int){
    val colorScheme = MaterialTheme.colorScheme
    Column(){
        Body2(
            text = item.displayAddress,
            color = if (selected) colorScheme.onPrimary else colorScheme.onBackground
        )
    }
}

@Composable
fun AddressAutoComplete(
    keyword: String,
    places: List<GeoPlace> = listOf(),
    onSearch: (keyword: String) -> Unit = {},
    onSelect: (index: Int) -> Unit = {},
    onBack:() -> Unit = {},
    onClear:() -> Unit = {},
){
    Column() {
        SearchList<GeoPlace>(
            keyword,
            "Enter Address",
            places,
//        selectedIndex = selectedIndex,
            onSelect = onSelect,
            onSearch = onSearch,
            onBack = onBack,
            onClear = onClear,
            itemContent = { it, selected, index -> PlaceListItem(it, selected, index) },
        )
    }
}


@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewAddressAutoComplete(){
    val places = listOf<GeoPlace>(
            GeoPlace(displayAddress = "1 King St"),
            GeoPlace(displayAddress="32 King West St"),
        )
    SLTheme {
        AddressAutoComplete(
            "",
            places,
        )
    }
}