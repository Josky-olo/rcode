package bitflyday.com.mobile.application.rcode

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import bitflyday.com.mobile.application.rcode.presentation.BarcodeListener
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer(private val barcodeListener: BarcodeListener) : ImageAnalysis.Analyzer {

    @SuppressLint("UnsafeExperimentalUsageError", "UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        val scanner = BarcodeScanning.getClient()
        image.image?.let {
            val imageInput = InputImage.fromMediaImage(it, image.imageInfo.rotationDegrees)

            scanner.process(imageInput)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        barcodeListener(barcode.rawValue ?: "")
                    }
                }
                .addOnFailureListener {

                }
                .addOnCompleteListener {
                    image.close()
                }
        }
    }
}