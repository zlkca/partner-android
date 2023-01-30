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
    val auth: Auth by authViewModel.auth.observeAsState(Auth("","", "", account = Account()))
    val authError by authViewModel.error.observeAsState()


    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(mapOf<String, String>())}

    LaunchedEffect(key1 = auth) {
//        if(auth) {
            onSubmit(auth)
//        }
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
            val role = roles!!.find { it.name == "partner"}
            val data = Account(
                id="",
                username = "",
                password = password,
                email = email,
                phone = phone,
                status = "Pending", // pending
                role = role!!
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

