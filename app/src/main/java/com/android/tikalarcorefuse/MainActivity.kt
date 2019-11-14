package com.android.tikalarcorefuse

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (arFragment as ArFragment).setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()

            ModelRenderable.builder()
                .setSource(this, Uri.parse("balloon.sfb"))
                .build()
                .thenAccept{ modelRenderable ->
                    val anchorNode = AnchorNode(anchor)
                    val transformableNode = TransformableNode((arFragment as ArFragment).transformationSystem)
                    transformableNode.setParent(anchorNode)
                    transformableNode.renderable = modelRenderable
                    (arFragment as ArFragment).arSceneView.scene.addChild(anchorNode)
                    transformableNode.select()


                }.exceptionally { throwable ->
                    val dialog = AlertDialog.Builder(this).setMessage("${throwable.message}").show()
                    null
                }
        }

    }
}