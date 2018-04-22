package com.example.muslimbeibytuly.tracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.muslimbeibytuly.tracker.models.ResultHolder
import com.wonderkiln.camerakit.CameraKitImage
import com.wonderkiln.camerakit.CameraView


class CameraActivity : AppCompatActivity() {
    private lateinit var cameraView: CameraView
    lateinit var capturer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        cameraView = findViewById(R.id.camera)
        capturer = findViewById(R.id.capturer)
        capturer.setOnClickListener(
                { _ -> cameraView.captureImage { event -> imageCaptured(event) } }

        )
    }

    private fun imageCaptured(image: CameraKitImage) {
        val jpeg = image.jpeg
        ResultHolder.dispose()
        ResultHolder.image = jpeg
        ResultHolder.nativeCaptureSize = cameraView.captureSize
        val intent = Intent(this, PreviewActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        cameraView.start()
        cameraView.captureImage()
    }

    override fun onPause() {
        cameraView.stop()
        super.onPause()
    }
}
