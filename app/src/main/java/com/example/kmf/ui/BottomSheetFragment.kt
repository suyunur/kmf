package com.example.kmf.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.kmf.R
import com.example.kmf.databinding.BottomSheetBinding
import com.example.kmf.vm.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val HEIGHT = 200

@DelicateCoroutinesApi
class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetBinding

    private val viewModule: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.bind(inflater.inflate(R.layout.bottom_sheet, container))

        binding.button.setOnClickListener {
            viewModule.register(
                binding.login.text.toString(),
                binding.name.text.toString(),
                binding.surname.text.toString(),
                binding.mail.text.toString(),
                binding.password.text.toString(),
                binding.phone.text.toString(),
                binding.status.text.toString().toInt()
            )

            UserInfoFragment().show(parentFragmentManager.beginTransaction(), "dialog")

            dismiss()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val density = requireContext().resources.displayMetrics.density

        dialog.let {
            val bottomSheet =
                it?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

            behavior.peekHeight = (HEIGHT * density).toInt()
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

}