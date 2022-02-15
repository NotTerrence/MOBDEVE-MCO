package com.mobdeve.s11.dugang.terencedein.mobdeve_mco

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.dugang.terencedein.mobdeve_mco.databinding.ActivityMainBinding
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import android.app.Activity

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
   private lateinit var bgMusic: MediaPlayer

   var startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
         result:ActivityResult ->

      if(result.resultCode == Activity.RESULT_OK){
         var bundle = result.data!!.extras

         Log.i("RESULT", bundle!!.getString("username", "NO NAME PASSED"))
         Log.i("RESULT", bundle!!.getString("firstname", "NO NAME PASSED"))
         Log.i("RESULT", bundle!!.getString("lastname", "NO NAME PASSED"))
         Log.i("RESULT", bundle!!.getString("email", "NO NAME PASSED"))
         Log.i("RESULT", bundle!!.getString("birthdate", "NO NAME PASSED"))

         var username = bundle.getString("username")
         binding.username.text = username
      }
      else{
         Snackbar.make(binding!!.root,
            "UNSUCESSFUL",
            Snackbar.LENGTH_SHORT).show()
      }
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding!!.root)

      binding = ActivityMainBinding.inflate(layoutInflater)
      window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(binding!!.root)
      bgMusic = MediaPlayer.create(this, R.raw.bensound)
      bgMusic.isLooping = true

      binding.btnsignprofile.setOnClickListener {
         var gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)
         startForResult.launch(gotoProfileActivity)
      }

      binding.buttonPlay.setOnClickListener {
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