package com.artbird.onsite.ui.auth

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import com.artbird.onsite.ui.components.*
import com.artbird.onsite.ui.theme.SLTheme


@Composable
fun SignupForm(
    username: String = "",
    password: String = "",
    email: String = "",
    phone: String = "",
    error: Map<String, String> = mapOf<String, String>(),
    onChange: (name: String, value: String) -> Unit,
    onSubmit: (username: String, password: String, email:String, phone: String) -> Unit,
    onPageChange: (page: String) -> Unit = {},
)
{
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {

        Box(
            modifier = Modifier.padding(top=30.dp, bottom = 30.dp)
        )

        Input(
            value = username,
            onValueChange = { onChange("username", it) },
            label = "Username",
        )

        if(error["username"] == "Username exists"){
            Text(
                text = "Username exists, please try another",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }else if(error["username"] == "Username empty"){
            Text(
                text = "Please enter a username",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Input(
            value = password,
            onValueChange = { onChange("password", it) },
            label = "Password",
            visualTransformation = PasswordVisualTransformation(),
        )

        if (error["password"] == "Password empty") {
            Text(
                text = "Please enter a password",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Input(
            value = email,
            onValueChange = { onChange("email", it) },
            label = "Email",
        )

        if(error["email"] == "Email exists"){
            Text(
                text = "Email exists, please try another",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }else if (error["email"] == "Email empty") {
            Text(
                text = "Please enter an email",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Input(
            value = phone,
            onValueChange = { onChange("phone", it) },
            label = "Phone number",
        )

        if (error["phone"] == "Phone empty") {
            Text(
                text = "Please enter a phone number",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Text(
            color = MaterialTheme.colorScheme.onBackground,
            text = "Already have an account? Sign in now",
            modifier = Modifier
                .padding(top = 20.dp, start = 8.dp)
                .clickable(enabled = true) {
                    onPageChange("login")
                }
        )

        Column(
            Modifier
                .padding(6.dp),
        ){
            LongButton(
                "Create Partner Account",
                onClick = {
                    onSubmit(
                        username,
                        password,
                        email,
                        phone
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .focusTarget()
            )
        }

    }
}

@Preview(
    name="Light Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(showBackground = true)
@Composable
fun PreviewFormActionBar(){
    SLTheme {
        SignupForm(
            onChange = {name, value -> },
            onSubmit = {username, password, email, phone -> }
        )
    }
}
