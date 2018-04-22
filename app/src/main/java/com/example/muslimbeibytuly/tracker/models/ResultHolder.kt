package com.example.muslimbeibytuly.tracker.models

import com.wonderkiln.camerakit.Size

object ResultHolder {

    var image: ByteArray? = null
    var nativeCaptureSize: Size? = null

    fun dispose() {
        image = null
        nativeCaptureSize = null
    }

}