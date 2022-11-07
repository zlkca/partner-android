package com.artbird.onsite.domain

data class PostResponse(
//    val acknowledged: Boolean,
    val insertedId: String
)

data class PutResponse(
//    val acknowledged: Boolean,
    val matchedCount: Int,
    val modifiedCount: Int,
    val upsertedId: String?,
)

data class DeleteResponse(
//    val acknowledged: Boolean,
    val deletedCount: Int
)