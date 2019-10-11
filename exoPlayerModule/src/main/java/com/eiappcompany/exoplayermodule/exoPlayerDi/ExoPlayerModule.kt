package com.eiappcompany.exoplayermodule.exoPlayerDi

/**
Created by EiAppCompany
11-10-2019
13:18
 **/

import android.content.Context
import com.eiappcompany.base.baseDi.AppUtilModule
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.hls.DefaultHlsDataSourceFactory
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppUtilModule::class])
class ExoPlayerModule {

    @Provides
    @Singleton
    internal fun provideExoPlayer(): String {
        return "I Provide exo Player Module"
    }

    @Provides
    @Singleton
    internal fun provideDefaultBandwidthMeter(): DefaultBandwidthMeter {
        return DefaultBandwidthMeter()
    }

    @Provides
    @Singleton
    internal fun provideAdaptiveTrackSelectionFactory(): AdaptiveTrackSelection.Factory {
        return AdaptiveTrackSelection.Factory()
    }

    @Provides
    @Singleton
    internal fun provideDefaultTrackSelector(): DefaultTrackSelector {
        return DefaultTrackSelector()
    }


    @Provides
    @Singleton
    internal fun provideDefaultDataSource(context: Context, userAgent: String): DefaultDataSource {
        return DefaultDataSource(context, Util.getUserAgent(context, javaClass.simpleName), true)
    }

    @Provides
    @Singleton
    internal fun provideDefaultDataSourceFactory(
        context: Context
    ): DefaultDataSourceFactory {
        return DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, javaClass.simpleName),
            null
        )
    }


    @Provides
    @Singleton
    internal fun provideDefaultExtractorsFactory(): DefaultExtractorsFactory {
        return DefaultExtractorsFactory()
    }

    @Provides
    @Singleton
    internal fun provideDefaultHlsDataSourceFactory(defaultExtractorsFactory: DefaultDataSourceFactory): DefaultHlsDataSourceFactory {
        return DefaultHlsDataSourceFactory(defaultExtractorsFactory)
    }


    //MediaSource
    @Provides
    @Singleton
    internal fun provideExtractorMediaSourceFactory(
        dataSource: DefaultDataSourceFactory,
        defaultExtractorsFactory: DefaultExtractorsFactory
    ): ExtractorMediaSource.Factory {
        return ExtractorMediaSource.Factory(dataSource).setExtractorsFactory(
            defaultExtractorsFactory
        )
    }

    //MediaSource
    @Provides
    @Singleton
    internal fun provideHlsMediaSourceFactory(defaultExtractorsFactory: DefaultHlsDataSourceFactory): HlsMediaSource.Factory {
        return HlsMediaSource.Factory(defaultExtractorsFactory)
    }

    @Provides
    @Singleton
    internal fun provideSimpleExoPlayer(
        context: Context,
        trackSelector: DefaultTrackSelector
    ): SimpleExoPlayer {
        return ExoPlayerFactory.newSimpleInstance(context, trackSelector)
    }


}
