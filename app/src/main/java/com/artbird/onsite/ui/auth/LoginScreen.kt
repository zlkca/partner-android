package com.artbird.onsite.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.unit.dp

import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Auth
import com.artbird.onsite.domain.Credential
import com.artbird.onsite.domain.Role
import com.artbird.onsite.ui.components.Input
import com.artbird.onsite.ui.components.LongButton
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException


@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onSubmit: (auth: Auth) -> Unit = {},
    onPageChange: (page: String) -> Unit = {}
){
    val auth: Auth by authViewModel.auth.observeAsState(
        Auth("","", "", account = Account())
    )

    val authError by authViewModel.error.observeAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(mapOf<String, String>())}


    LaunchedEffect(key1 = auth) {
        if(auth != null) {
            onSubmit(auth)
        }
    }

    LaunchedEffect(key1 = authError) {
        if(authError != null) {
            if(authError!!.code != 200){
                error = mapOf(authError!!.field to authError!!.message)
            }
        }
    }

    fun handleChange(name: String, value: String) {
        when (name){
            "password" -> password = value
            "email" -> email = value
        }
    }

    fun handleSubmit(password: String, email:String,){
        authViewModel.login(Credential(
            email=email,
            password=password)
        )
        // catch
//        UnknownHostException
//
//        SocketTimeoutException
//
//        SSLHandshakeException
//
//        ConnectException
//
//        HttpException
    }

    LoginForm(
        password,
        email,
        error,
        onChange=::handleChange,
        onSubmit=::handleSubmit,
        onPageChange={v ->
            authViewModel.clearError()
            onPageChange(v)
                     },
    )

//
//    Column(
//        Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Top,
////        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
////        val focusManager = LocalFocusManager.current
////        val logo = painterResource(R.drawable.ic_launcher_foreground)
////        Icon(
////            logo,
////            contentDescription = "Shutter Lux",
////            modifier = Modifier.padding(top=30.dp, bottom = 30.dp)
////        )
//        Box(
//            modifier = Modifier.padding(top=30.dp, bottom = 30.dp)
//        )
//
//        Input(
//            value = username,
//            onValueChange = { username = it },
//            label = "Username",
//        )
//
//        if (auth.error != "" ) {
//            Text(
//                text = if(auth.error == "User not found") "User not found" else "",
//                color = Color.Red,
////                style = MaterialTheme.typography.caption,
//                modifier = Modifier.padding(start = 16.dp)
//            )
//        }
//        Input(
//            value = password,
//            onValueChange = { password = it },
//            label = "Password",
//            visualTransformation = PasswordVisualTransformation(),
//        )
//
//        if (auth.error != "" ) {
//            Text(
//                text = if(auth.error == "User not found") "" else "Password not match",
//                color = Color.Red,
////                style = MaterialTheme.typography.caption,
//                modifier = Modifier.padding(start = 16.dp)
//            )
//        }
//
//
//
//
//        Text(
//            modifier = Modifier
//                .padding(top=20.dp, start=8.dp)
//                .clickable(enabled = true) {
//                    onPageChange("signup")
//                },
//            text = "Signup as partner"
//        )
//
//        Column(
//            Modifier
//                .padding(6.dp),
//        ){
//            LongButton(
//                "Sign in",
//                onClick = {
//                    authViewModel.login(Credential(username, password))
//                },
//
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp)
//                    .focusTarget()
//            )
//        }
//
//    }
}
