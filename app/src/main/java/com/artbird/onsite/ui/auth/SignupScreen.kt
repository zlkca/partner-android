package com.artbird.onsite.ui.auth

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Auth
import com.artbird.onsite.domain.Role

@Composable
fun SignupScreen(
    authViewModel: AuthViewModel,
    onAfterSubmit: (auth: Auth) -> Unit = {},
    onPageChange: (page: String) -> Unit = {}
    )
{
    val auth: Auth by authViewModel.auth.observeAsState(Auth("","", "", account = Account()))
    val authError by authViewModel.error.observeAsState()


    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(mapOf<String, String>())}

    LaunchedEffect(key1 = auth) {
        if(auth != null) {
            onAfterSubmit(auth)
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
            "username" -> username = value
            "password" -> password = value
            "email" -> email = value
            "phone" -> phone = value
        }
    }

    fun handleSubmit(email:String, phone: String, password: String, ){
            val data = Account(
                id="",
                username = "",
                password = password,
                email = email,
                phone = phone,
                status = "Pending", // pending
                role = Role(name="partner")
            )
            authViewModel.signup(data)
    }

    SignupForm(
        email,
        phone,
        password,
        error,
        onChange=::handleChange,
        onSubmit=::handleSubmit,
        onPageChange={v ->
            authViewModel.clearError()
            onPageChange(v)
                     },
    )
}

