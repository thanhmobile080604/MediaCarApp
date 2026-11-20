package com.volvocars.mediasample.dataaccess

import com.volvocars.mediasample.domain.SongRepository

class SongRepositoryImpl(private val playlistFactory: PlaylistFactory) : SongRepository {
    override fun retrievePlaylists() = playlistFactory.createPlaylists()
}
