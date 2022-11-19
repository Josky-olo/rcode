package bitflyday.com.mobile.application.rcode.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import bitflyday.com.mobile.application.rcode.R
import bitflyday.com.mobile.application.rcode.databinding.FragmentRcodeResultBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RcodeResultFragment : Fragment() {

    private var _binding: FragmentRcodeResultBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var barcode: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val safeArgs: RcodeResultFragmentArgs by navArgs()
        barcode = safeArgs.barcode
        _binding = FragmentRcodeResultBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvBarcode.text = barcode
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_RcodeResultFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}