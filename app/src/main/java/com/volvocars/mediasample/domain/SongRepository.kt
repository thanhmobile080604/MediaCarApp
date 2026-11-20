package com.volvocars.mediasample.domain

interface SongRepository {
    fun retrievePlaylists(): List<Playlist>
}
