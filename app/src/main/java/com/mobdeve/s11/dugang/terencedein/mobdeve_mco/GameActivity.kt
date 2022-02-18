package com.mobdeve.s11.dugang.terencedein.mobdeve_mco

import android.app.Activity
import android.app.ActivityManager
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.mobdeve.s11.dugang.terencedein.mobdeve_mco.databinding.ActivityGameBinding
import java.util.*
import java.util.function.Consumer
import java.util.function.Function


class GameActivity : AppCompatActivity() {

   private lateinit var binding: ActivityGameBinding
   private var mode: Mode = Mode.ROCKET
   // object of ArFragment Class
   private var arCam: ArFragment? = null
   private var clickNo: Int = 0

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityGameBinding.inflate(layoutInflater)
      window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(binding!!.root)

      if(checkSystemSupport(this)) {
         arCam = supportFragmentManager.findFragmentById(R.id.arCameraArea) as ArFragment?
         arCam!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
            val anchor: Anchor = hitResult.createAnchor()
            ModelRenderable.builder()
               .setSource(this, R.raw.rocket)
               .setIsFilamentGltf(true)
               .build()
               .thenAccept { modelRenderable: ModelRenderable? ->
                  addModel (
                     anchor,
                     modelRenderable!!
                  )
               }
               .exceptionally { throwable: Throwable ->
                  val builder = AlertDialog.Builder(this)
                  builder.setMessage("Something is not right" + throwable.message).show()
                  null
               }
         }
      }
   }

   fun checkSystemSupport(activity: Activity): Boolean {

      // checking whether the API version of the running Android >= 24
      // that means Android Nougat 7.0
      return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         val openGlVersion: String =
            (Objects.requireNonNull(activity.getSystemService(ACTIVITY_SERVICE)) as ActivityManager).getDeviceConfigurationInfo()
               .getGlEsVersion()

         // checking whether the OpenGL version >= 3.0
         if (openGlVersion.toDouble() >= 3.0) {
            true
         } else {
            Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_SHORT)
               .show()
            activity.finish()
            false
         }
      } else {
         Toast.makeText(activity, "App does not support required Build Version", Toast.LENGTH_SHORT)
            .show()
         activity.finish()
         false
      }
   }

   private fun addModel(anchor: Anchor, modelRenderable: ModelRenderable) {

      // Creating a AnchorNode with a specific anchor
      val anchorNode = AnchorNode(anchor)

      // attaching the anchorNode with the ArFragment
      anchorNode.setParent(arCam!!.arSceneView.scene)
      val transform = TransformableNode(arCam!!.transformationSystem)

      // attaching the anchorNode with the TransformableNode
      transform.setParent(anchorNode)

      // attaching the 3d model with the TransformableNode that is
      // already attached with the node
      transform.renderable = modelRenderable
      transform.select()
   }

   fun onRadioButtonClicked(view: View) {
      when (view.id) {
         R.id.radioRocket -> mode = Mode.ROCKET
         R.id.radioMachineGun -> mode = Mode.MACHINEGUN
         else -> mode = Mode.ENEMY
      }
   }
}