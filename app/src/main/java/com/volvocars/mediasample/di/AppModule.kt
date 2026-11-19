package com.volvocars.mediasample.di

import android.content.Context
import android.support.v4.media.session.MediaSessionCompat
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.volvocars.mediasample.common.util.ResourceUriUtil
import com.volvocars.mediasample.dataaccess.PlaylistFactory
import com.volvocars.mediasample.dataaccess.SongRepositoryImpl
import com.volvocars.mediasample.domain.SongRepository
import com.volvocars.mediasample.mediaplayback.MediaItemFactory
import com.volvocars.mediasample.mediaplayback.MediaLibrary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResourceUriUtil(@ApplicationContext context: Context): ResourceUriUtil {
        return ResourceUriUtil(context)
    }

    @Provides
    @Singleton
    fun providePlaylistFactory(
        @ApplicationContext context: Context,
        resourceUriUtil: ResourceUriUtil
    ): PlaylistFactory {
        return PlaylistFactory(context, resourceUriUtil)
    }

    @Provides
    @Singleton
    fun provideSongRepository(playlistFactory: PlaylistFactory): SongRepository {
        return SongRepositoryImpl(playlistFactory)
    }

    @Provides
    @Singleton
    fun provideMediaItemFactory(@ApplicationContext context: Context): MediaItemFactory {
        return MediaItemFactory(context)
    }

    @Provides
    @Singleton
    fun provideMediaLibrary(
        mediaItemFactory: MediaItemFactory,
        songRepository: SongRepository
    ): MediaLibrary {
        return MediaLibrary(mediaItemFactory, songRepository)
    }

    @Provides
    @Singleton
    fun provideAudioAttributes(): AudioAttributes {
        return AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    @Provides
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ): Player {
        return SimpleExoPlayer.Builder(context).build().apply {
            setAudioAttributes(audioAttributes, true)
        }
    }

    @Provides
    fun provideMediaSessionCompat(@ApplicationContext context: Context): MediaSessionCompat {
        return MediaSessionCompat(context, "MusicService")
    }
}
