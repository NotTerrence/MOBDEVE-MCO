package com.mobdeve.s11.dugang.terencedein.mobdeve_mco

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.dugang.terencedein.mobdeve_mco.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
   private lateinit var bgMusic: MediaPlayer

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(binding!!.root)
      bgMusic = MediaPlayer.create(this, R.raw.bensound)
      bgMusic.isLooping = true

      binding.buttonPlay!!.setOnClickListener {
         val goToGameActivity = Intent(applicationContext, GameActivity::class.java)
         startActivity(goToGameActivity)
      }

      binding.buttonQuit!!.setOnClickListener {
         val intent = Intent(Intent.ACTION_MAIN)
         intent.addCategory(Intent.CATEGORY_HOME)
         intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
         startActivity(intent)
      }

      binding.togglebuttonMusic!!.isChecked = true
      binding.togglebuttonMusic!!.setOnCheckedChangeListener { _, isChecked ->
         if (isChecked) {
            bgMusic.start()
         } else {
            bgMusic.pause()
         }
      }
   }

   override fun onStart() {
      super.onStart()
      bgMusic.start()

   }

   override fun onResume() {
      super.onResume()
      binding.togglebuttonMusic!!.setOnCheckedChangeListener { _, isChecked ->
         if (isChecked) {
            bgMusic.start()
         } else {
            bgMusic.pause()
         }
      }
   }

   override fun onPause() {
      super.onPause()
      binding.togglebuttonMusic!!.setOnCheckedChangeListener { _, isChecked ->
         if (isChecked) {
            bgMusic.start()
         } else {
            bgMusic.pause()
         }
      }
      bgMusic.pause()
   }
}