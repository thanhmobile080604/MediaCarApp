package com.volvocars.mediasample.mediaplayback

import android.support.v4.media.MediaMetadataCompat
import com.volvocars.mediasample.common.logging.logd
import com.volvocars.mediasample.domain.Playlist
import com.volvocars.mediasample.domain.SongRepository

/**
 * Represents the library to browse media in. The structure is as follows:
 * [Root/]: Playlist Root
 *    |
 *    |__ Song 1
 *    |
 *    |__ Song 2
 */
class MediaLibrary(mediaItemFactory: MediaItemFactory, songRepository: SongRepository) {
    private val playlists: List<Playlist> = songRepository.retrievePlaylists()
    private val library = mutableMapOf<String, List<MediaMetadataCompat>>()

    init {
        library[BROWSABLE_ROOT_ID] = playlists.map { playlist ->
            mediaItemFactory.createBrowsableMediaItem(playlist.id, playlist.title)
        }

        playlists.forEach { playlist ->
            library[playlist.id] = playlist.songs.mapIndexed { songIndex, song ->
                mediaItemFactory.createPlayableMediaItem(
                    song = song,
                    playlistSize = playlist.songs.size,
                    songNumber = songIndex + 1
                )
            }
        }
    }

    fun getPlaylistById(id: String): List<MediaMetadataCompat>? {
        logd("need $id")
        return library[id]
    }

    fun getSongIndex(id: String): Int {
        return getAllSongs().indexOfFirst { it.id == id }
    }

    fun getAllSongs(): List<MediaMetadataCompat> {
        return playlists.flatMap { library[it.id] ?: emptyList() }
    }
}

const val BROWSABLE_ROOT_ID = "root/"
