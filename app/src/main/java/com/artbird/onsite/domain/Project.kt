package com.artbird.onsite.domain

data class Stage(
    var name: String = "",
    var status: String = "",
    var start: String = "",
    var end: String = "",
)

data class Project(
    var _id: String = "",
    var client: BaseClient = BaseClient(),
    var address: Address = Address(),
    var recommender: BaseAccount = BaseAccount(),
    var sales: BaseAccount = BaseAccount(),
    var technician: BaseAccount = BaseAccount(),
    var stages: List<Stage> = listOf(),
    var notes: String = "",
    var created: String = "",
    var updated: String = "",
)

data class BaseProject(
    var _id: String = "",
    var address: Address = Address(),
    var created: String = "",
    var updated: String = "",
)