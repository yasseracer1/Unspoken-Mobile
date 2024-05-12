package org.d3if3130.unspoken.model

data class User(
    val id: Long = 0L,
    val nama: String,
    val email: String,
    val password: String
)