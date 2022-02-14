package com.mobdeve.s11.dugang.terencedein.mobdeve_mco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.mobdeve.s11.dugang.terencedein.mobdeve_mco.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(binding!!.root)

      binding.buttonPlay!!.setOnClickListener {
         val goToGameActivity = Intent(applicationContext, GameActivity::class.java)
         startActivity(goToGameActivity)

         finish()
      }
   }
}