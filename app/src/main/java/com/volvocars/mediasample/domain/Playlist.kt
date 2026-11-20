package com.volvocars.mediasample.domain

data class Playlist(
    val id: String,
    val title: String,
    val songs: List<Song>,
)
