package org.d3if3130.unspoken.model

data class Komentar(
    val id_komentar: String,
    val id_postingan: String,
    val username: String,
    val isi_komentar: String,
    val tanggal: String,
    val tanggal_detail: String
)
