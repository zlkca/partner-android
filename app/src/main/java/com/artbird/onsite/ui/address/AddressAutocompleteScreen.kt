package com.artbird.onsite.ui.address

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artbird.onsite.domain.GeoPlace
import com.artbird.onsite.domain.GooglePrediction
import com.artbird.onsite.domain.GooglePredictionsResponse
import com.artbird.onsite.ui.components.AddressAutoComplete


@Composable
fun AddressAutocompleteScreen(
    navController: NavController,
    addressViewModel: AddressViewModel,
    onSelect: (address: String) -> Unit = {}
){
    var keyword by remember { mutableStateOf("") }
    val placeRsp: List<GeoPlace> by addressViewModel.placeRsp.observeAsState(listOf())

    LaunchedEffect(key1 = keyword) {
        if (keyword != null && keyword.isNotEmpty()) {
            if(keyword.length > 4) {
                addressViewModel.getAddressPredictions(keyword)
            }
        }
    }

    Column(modifier = Modifier.padding(8.dp)) {
        AddressAutoComplete(
            keyword,
            placeRsp!!,
            onSearch = { it ->
                keyword = it
                if (keyword != null && keyword.isNotEmpty()) {
                    addressViewModel.getAddressPredictions(keyword)
                }
            },
            onSelect = { index ->
                val predict = placeRsp[index];
                onSelect(predict.displayAddress)
                // for google
                // val predict = placeRsp.predictions[index] // update client by useEffect
                // onSelect(predict.description)
                navController.navigate("projects/new/form")
            },
            onBack = {
                navController.navigate("projects/new/form")
            },
            onClear = {
                keyword = ""
            }
        )
    }
}
