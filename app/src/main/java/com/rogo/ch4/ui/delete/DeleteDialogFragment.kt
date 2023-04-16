package com.rogo.ch4.ui.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rogo.ch4.R
import com.rogo.ch4.data.local.preferences.DataStoreManager
import com.rogo.ch4.data.repository.LocalRepository
import com.rogo.ch4.databinding.FragmentDeleteDialogBinding
import com.rogo.ch4.utils.viewModelFactory

class DeleteDialogFragment : DialogFragment() {

    private var _binding: FragmentDeleteDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DeleteDialogViewModel by viewModelFactory {
        DeleteDialogViewModel(
            LocalRepository(
                DataStoreManager(requireContext()),
                requireActivity().application
            )
        )
    }
    private val navArgs: DeleteDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.btnDelete.setOnClickListener {
            viewModel.delete(navArgs.noteEntity)
            findNavController().navigate(R.id.homeFragment)
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}