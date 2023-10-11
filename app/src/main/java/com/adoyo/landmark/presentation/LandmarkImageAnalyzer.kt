package com.adoyo.landmark.presentation

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.adoyo.landmark.domain.Classification
import com.adoyo.landmark.domain.LandmarkClassifier

class LandmarkImageAnalyzer(
    private val classifier: LandmarkClassifier,
    private val onResults: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer {

    private var frameSkipConter = 0
    override fun analyze(image: ImageProxy) {
        if (frameSkipConter % 60 == 0) {

            val rotatioDegrees = image.imageInfo.rotationDegrees
            val bitmap = image.toBitmap().centerCrop(321, 321)
            val results = classifier.classify(bitmap, rotatioDegrees)
            onResults(results)
        }
        frameSkipConter++
        image.close()

    }
}