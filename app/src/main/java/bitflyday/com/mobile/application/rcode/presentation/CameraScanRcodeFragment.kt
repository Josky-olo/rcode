package bitflyday.com.mobile.application.rcode.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.hardware.camera2.CameraMetadata
import android.hardware.camera2.CaptureRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.camera2.interop.Camera2Interop
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import bitflyday.com.mobile.application.rcode.BarcodeAnalyzer
import bitflyday.com.mobile.application.rcode.R
import bitflyday.com.mobile.application.rcode.data.datasource.Barcode
import bitflyday.com.mobile.application.rcode.databinding.FragmentCameraScanRcodeBinding
import bitflyday.com.mobile.application.rcode.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

typealias BarcodeListener = (barcode: String) -> Unit

@AndroidEntryPoint
class CameraScanRcodeFragment : Fragment() {

    private var camera: Camera? = null
    private var _binding: FragmentCameraScanRcodeBinding? = null
    private val binding get() = _binding!!
    private val rCodeViewModel: RCodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCameraScanRcodeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (allPermissionsGranted()) {
            setupCameraProvider()
        } else {
            requestPermissions(REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
        initObserver()
    }

    private fun initObserver() {
        rCodeViewModel.activeBarcode.observe(viewLifecycleOwner) {
            launchAndRepeatWithViewLifecycle {
                launch {
                    val barcodeData = Barcode()
                    barcodeData.barcodeText = it
                    rCodeViewModel.addBarcodeData(barcodeData).apply {
                        if (findNavController().currentDestination?.id == R.id.CameraScanRcodeFragment) {
                            findNavController().navigate(
                                CameraScanRcodeFragmentDirections.actionCameraScanRCodeFragmentToRcodeResultFragment(this ?: 0)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setupCameraProvider() {
        ProcessCameraProvider.getInstance(requireContext()).also { provider ->
            provider.addListener({
                bindPreview(provider.get())
            }, ContextCompat.getMainExecutor(requireContext()))
        }
    }


    private fun bindPreview(cameraProvider: ProcessCameraProvider) {
        cameraProvider.unbindAll()
        val preview: Preview = Preview.Builder().build()
        val barcodeAnalysis = cameraExtender().also { it ->
            it.setAnalyzer(Executors.newSingleThreadExecutor(), BarcodeAnalyzer { barcode ->
                rCodeViewModel.setActiveBarcode(barcode)
                return@BarcodeAnalyzer
            })
        }
        val cameraSelector: CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, barcodeAnalysis)
        camera?.let {
            preview.setSurfaceProvider(binding.previewView.surfaceProvider)
        }
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun cameraExtender(): ImageAnalysis {
        val builder = ImageAnalysis.Builder()
        val camera2InterOp = Camera2Interop.Extender(builder)
        camera2InterOp.setCaptureRequestOption(CaptureRequest.CONTROL_EFFECT_MODE, CameraMetadata.CONTROL_EFFECT_MODE_OFF)
        camera2InterOp.setCaptureRequestOption(CaptureRequest.CONTROL_AWB_MODE, CameraMetadata.CONTROL_AWB_MODE_OFF)
        return builder.build()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}