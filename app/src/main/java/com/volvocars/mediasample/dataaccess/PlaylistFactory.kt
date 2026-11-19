package com.volvocars.mediasample.dataaccess

import android.content.Context
import com.volvocars.mediasample.R
import com.volvocars.mediasample.common.util.ResourceUriUtil
import com.volvocars.mediasample.domain.Playlist
import com.volvocars.mediasample.domain.Song

private const val PLAYLIST_ID = "volvo-pre-listen-playlist-id"

class PlaylistFactory(
    private val context: Context,
    private val resourceUriUtil: ResourceUriUtil
) {
    fun createPlaylist() = Playlist(
        id = PLAYLIST_ID,
        songs = getSongs(),
    )

    private fun getSongs() = listOf(
        Song(
            id = "premier_song_id",
            title = context.getString(R.string.sample_song_title),
            subtitle = context.getString(R.string.sample_song_subtitle),
            description = context.getString(R.string.sample_song_description),
            mediaArtUri = resourceUriUtil.getUriFor(R.drawable.abba_album_art).toString(),
            mediaUri = resourceUriUtil.getUriFor(R.raw.test_audio_track).toString(),
            artist = context.getString(R.string.sample_artist_name),
        )
        ,
        Song(
            id = "second_song_id",
            title = "Track 2 - Highway Drives",
            subtitle = "Electronic Beats",
            description = "Dynamic energetic beats for highway cruising",
            mediaArtUri = resourceUriUtil.getUriFor(R.drawable.abba_album_art).toString(),
            mediaUri = resourceUriUtil.getUriFor(R.raw.test_audio_track).toString(),
            artist = "Nordic Horizon",
        ),
        Song(
            id = "third_song_id",
            title = "Track 3 - Midnight City",
            subtitle = "Lo-Fi Ambient",
            description = "Chill ambient vibes for nighttime commutes",
            mediaArtUri = resourceUriUtil.getUriFor(R.drawable.abba_album_art).toString(),
            mediaUri = resourceUriUtil.getUriFor(R.raw.test_audio_track).toString(),
            artist = "Scandinavian Echoes",
        ),
        Song(
            id = "fourth_song_id",
            title = "Track 4 - Electric Horizon",
            subtitle = "Synthwave Chill",
            description = "Futuristic synthwave for electric vehicle journeys",
            mediaArtUri = resourceUriUtil.getUriFor(R.drawable.abba_album_art).toString(),
            mediaUri = resourceUriUtil.getUriFor(R.raw.test_audio_track).toString(),
            artist = "Gothenburg Sound",
        ),
    )
}
