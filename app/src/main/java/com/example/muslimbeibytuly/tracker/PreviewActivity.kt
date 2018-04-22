package com.example.muslimbeibytuly.tracker

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.example.muslimbeibytuly.tracker.models.ResultHolder


class PreviewActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        imageView = findViewById(R.id.image)
        val jpeg = ResultHolder.image
        if (jpeg != null) {
            imageView.visibility = View.VISIBLE
            val bitmap = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.size)
            if (bitmap == null) {
                finish()
                return
            }
            imageView.setImageBitmap(bitmap)
        }
    }
}
