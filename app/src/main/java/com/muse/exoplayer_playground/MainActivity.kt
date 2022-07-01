package com.muse.exoplayer_playground

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerControlView

class MainActivity : ComponentActivity(), Player.Listener {

    private lateinit var player: ExoPlayer
    private var styledPlayerControlView: PlayerControlView ?= null
    private var playerView: PlayerControlView ?= null
    private var progressBar: ProgressBar ?= null
    private var titleTv: TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPlayer()
        addMp3Files()
//        setContent {
//
//        }
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        styledPlayerControlView?.player = player
        player.addListener(this)

    }

    private fun addMp3Files() {
        val mediaItem = MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
        player.addMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)

        when(playbackState){
            Player.STATE_BUFFERING -> {
                progressBar?.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                progressBar?.visibility = View.INVISIBLE
            }
        }
    }

    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
        super.onMediaMetadataChanged(mediaMetadata)

        titleTv?.text = mediaMetadata.title ?: mediaMetadata.displayTitle ?: "title not found"
    }
}
