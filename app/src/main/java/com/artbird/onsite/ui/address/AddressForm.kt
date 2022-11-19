package com.artbird.onsite.ui.address

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Address
import com.artbird.onsite.domain.Client2
import com.artbird.onsite.ui.components.FormActionBar
import com.artbird.onsite.ui.components.Input
//import com.artbird.onsite.ui.components.Select
import com.artbird.onsite.ui.theme.SLTheme


@Composable
fun AddressForm(
    address: Address,
    error: Map<String, String> = mapOf<String, String>(),
    onChange: (fieldName: String, value: String) -> Unit = {f,v -> },
) {
//    val verticalScrollState = rememberScrollState()
//
    Column(
//        modifier = Modifier.padding(12.dp)
//        .verticalScroll(verticalScrollState)
    )
    {
//        FormActionBar(
//            onCancel = { navController.navigate("clients") },
//            onSave,
//        )

        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        ) {
            Input(
                value = address.unitNumber,
                onValueChange = { onChange("unitNumber", it) },
                label = "Unit Number",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Input(
                value = address.streetNumber,
                onValueChange = { onChange("streetNumber", it) },
                label = "Street Number",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }

        Input(
            value = address.streetName,
            onValueChange = { onChange("streetName", it) },
            label = "Street Name",
        )
        if (error["streetName"] != "" ) {
            Text(
                text = if(error["streetName"] == "Street name not found") "Street name not found" else "",
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        ) {
            Input(
                value = address.city,
                onValueChange = { onChange("city", it) },
                label = "City",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )


            Input(
                value = address.province,
                onValueChange = { onChange("province", it) },
                label = "Province",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
//            Select(
//                value = address.province,
//                onValueChange = { onChange("province", it) },
//                label = "Province",
//                options = provinceOptions,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .padding(end = 8.dp),
//            )
        }
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        ) {
            Input(
                value = address.country,
                onValueChange = { onChange("country", it) },
                label = "Country",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Input(
                value = address.postcode,
                onValueChange = { onChange("postcode", it) },
                label = "Postal Code",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )


        }
    }
}

@Preview(
    name="Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewAddressForm(){
    val address= Address("2", "", "235", "Front St", "Toronto", "ON", "Canada","L3R 0C7")

    SLTheme {
        AddressForm(
            address,
        )
    }
}