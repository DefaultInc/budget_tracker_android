package com.example.muslimbeibytuly.tracker

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.muslimbeibytuly.tracker.models.ResultHolder


class PreviewActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var uploader: Button
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
        uploader = findViewById(R.id.uploader)
        uploader.setOnClickListener(
                { _ ->
                    parseImage()
                    val intent = Intent(this, MasterActivity::class.java)
                    startActivity(intent)
                }
        )
    }

    fun parseImage() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://receipt-parser-201920.appspot.com/api"
        val stringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                Response.Listener<String> { response ->
                    Log.i("Response is: ", "${response.substring(0, 500)}")
                },
                Response.ErrorListener {
                    Log.i("That didn't work!", "lol")
                }) {

            override fun getBodyContentType(): String {
                val BOUNDARY = "AS24adije32MDJHEM9oMaGnKUXtfHq"
                val MULTIPART_FORMDATA = "multipart/form-data;boundary=" + BOUNDARY
                return MULTIPART_FORMDATA
            }

            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>().apply {
                    put("Content-Type", "multipart/form-data")
                }
                return headers
            }

            override fun getBody(): ByteArray {
                return HashMap<String, String>().apply {
                    put("image", ResultHolder.image.toString())
                }.toString().toByteArray()
            }
        }
        queue.add(stringRequest)
    }
}
