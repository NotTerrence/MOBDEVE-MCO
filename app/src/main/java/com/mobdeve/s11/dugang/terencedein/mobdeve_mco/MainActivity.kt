package com.mobdeve.s11.dugang.terencedein.mobdeve_mco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.mobdeve.s11.dugang.terencedein.mobdeve_mco.databinding.ActivityMainBinding
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import android.app.Activity

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

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

      binding.btnsignprofile.setOnClickListener {
         var gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)
         startForResult.launch(gotoProfileActivity)
      }

      binding.buttonPlay.setOnClickListener {
         val goToGameActivity = Intent(applicationContext, GameActivity::class.java)
         startActivity(goToGameActivity)

      }
   }
}