package com.mobdeve.s11.dugang.terencedein.mobdeve_mco

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.dugang.terencedein.mobdeve_mco.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

   lateinit var binding: ActivityProfileBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityProfileBinding.inflate(layoutInflater)
      window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(binding!!.root)

      binding.btnsaveprofile.setOnClickListener(){
         var bundle = Bundle()
         bundle.putString("username", binding.textusername.text.toString())
         bundle.putString("firstname", binding.textfirstname.text.toString())
         bundle.putString("lastname", binding.textlastname.text.toString())
         bundle.putString("email", binding.textemail.text.toString())
         bundle.putString("birthdate", binding.textbirthdate.text.toString())
         var resultIntent = Intent()
         resultIntent.putExtras(bundle)

         setResult(RESULT_OK, resultIntent)
         finish()
      }
   }

   override fun onSaveInstanceState(outState: Bundle){
      outState?.run {
         putString("username", binding!!.textusername.text.toString())
         putString("firstname", binding!!.textfirstname.text.toString())
         putString("lastname", binding!!.textlastname.text.toString())
         putString("email", binding!!.textemail.text.toString())
         putString("birthdate", binding!!.textbirthdate.text.toString())
      }
      super.onSaveInstanceState(outState)
   }

   override fun onRestoreInstanceState(savedInstanceState: Bundle) {
      super.onRestoreInstanceState(savedInstanceState)

      savedInstanceState.run {
         binding!!.textusername.setText(getString("username", ""))
         binding!!.textfirstname.setText(getString("firstname", ""))
         binding!!.textlastname.setText(getString("lastname", ""))
         binding!!.textemail.setText(getString("email", ""))
         binding!!.textbirthdate.setText(getString("birthdate", ""))
      }
   }
}