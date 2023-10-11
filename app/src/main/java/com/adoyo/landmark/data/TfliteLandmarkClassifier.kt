package com.adoyo.landmark.data

import android.content.Context
import android.graphics.Bitmap
import com.adoyo.landmark.domain.Classification
import com.adoyo.landmark.domain.LandmarkClassification
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class TfliteLandmarkClassifier(
    private val context: Context,
    private val threshold: Float = 0.5f,
    private val maxResult: Int = 1,
) : LandmarkClassification {

    private var classifier: ImageClassifier? = null

    private fun setupClassifier() {
        val baseOptions = BaseOptions.builder().setNumThreads(2).build()
        val options = ImageClassifier.ImageClassifierOptions.builder().setBaseOptions(baseOptions)
            .setMaxResults(maxResult).setScoreThreshold(threshold).build()

        try {
            classifier = ImageClassifier.createFromFileAndOptions(
                context,
                "",
                options
            )
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

    }

    override fun classify(bitmap: Bitmap, rotation: Int): List<Classification> {

    }

}