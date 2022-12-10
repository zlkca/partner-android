package com.artbird.onsite.domain

import com.artbird.onsite.domain.Account

data class Stage(
    var name: String = "",
    var status: String = "",
    var start: String = "",
    var end: String = "",
)

data class Project(
    var _id: String = "",
    var client: Account = Account(),
    var address: Address = Address(),
    var recommender: Account = Account(),
    var sales: Account = Account(),
    var technician: Account = Account(),
    var stages: List<Stage> = listOf(),
    var notes: String = "",
    var status: String = "in progress",
    var created: String = "",
    var updated: String = "",
)

data class BaseProject(
    var _id: String = "",
    var address: Address = Address(),
    var created: String = "",
    var updated: String = "",
)