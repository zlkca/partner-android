package com.artbird.onsite.ui.auth

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.artbird.onsite.domain.Account
import com.artbird.onsite.domain.Auth
import com.artbird.onsite.domain.Role
import com.artbird.onsite.ui.role.RoleViewModel

@Composable
fun SignupScreen(
    authViewModel: AuthViewModel,
    roleViewModel: RoleViewModel,
    onSubmit: (auth: Auth) -> Unit = {},
    onPageChange: (page: String) -> Unit = {}
    )
{
    val roles by roleViewModel.roles.observeAsState()
    val auth: Auth by authViewModel.auth.observeAsState(Auth("","", "", role= Role("", "")))
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val error by remember { mutableStateOf(mapOf<String, String>())}

    LaunchedEffect(key1 = auth) {
        if(auth.error == "") {
            onSubmit(auth)
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

    fun handleSubmit(username: String, password: String, email:String, phone: String){
            val role = roles!!.find { it.name == "partner"}
            val data = Account(
                id="",
                username = username,
                password = password,
                email = email,
                phone = phone,
                status = "P", // pending
                roleId = role!!.id
            )
            authViewModel.signup(data)
    }

    SignupForm(
        username,
        password,
        email,
        phone,
        error,
        onChange=::handleChange,
        onSubmit=::handleSubmit,
        onPageChange={v -> onPageChange(v)},
    )
}

