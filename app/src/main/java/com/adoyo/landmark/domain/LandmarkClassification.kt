package com.adoyo.landmark.domain

import android.graphics.Bitmap

interface LandmarkClassification {

    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}