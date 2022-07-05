package com.muse.exoplayer_playground

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util

class MainActivity : ComponentActivity(), Player.Listener {

    private lateinit var player: ExoPlayer
    private var playerView: PlayerView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        setupPlayer()
        addMp3Files()
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.video_view)
        playerView?.player = player
        player.addListener(this)
    }


    private fun addMp3Files() {
        //"https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"
        val mediaItem = MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
        player.addMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    public override fun onStart() {
        super.onStart()
    }

//    override fun onPlaybackStateChanged(playbackState: Int) {
//        super.onPlaybackStateChanged(playbackState)
//
//        when(playbackState){
//            Player.STATE_BUFFERING -> {
//                progressBar?.visibility = View.VISIBLE
//            }
//            Player.STATE_READY -> {
//                progressBar?.visibility = View.INVISIBLE
//            }
//        }
//    }

}
