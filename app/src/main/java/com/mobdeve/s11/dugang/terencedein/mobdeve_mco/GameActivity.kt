package com.mobdeve.s11.dugang.terencedein.mobdeve_mco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.mobdeve.s11.dugang.terencedein.mobdeve_mco.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

   private lateinit var binding: ActivityGameBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityGameBinding.inflate(layoutInflater)
      window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(binding!!.root)
   }
}