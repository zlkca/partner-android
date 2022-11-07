package com.artbird.onsite.domain

data class gitStage(
    var name: String = "",
    var status: String = "",
    var startTime: String = "",
    var endTime: String = "",
)

data class Case(
    var id: String = "",
    var client: BaseClient = BaseClient(),
    var recommender: BaseAccount = BaseAccount(),
    var stage: Stage = Stage(),
    var stages: List<Stage> = listOf(),
    var notes: String = "",
    var created: String = "",
    var updated: String = "",
)