package com.rogo.ch4.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.rogo.ch4.R
import com.rogo.ch4.data.local.database.entity.NoteEntity
import com.rogo.ch4.data.local.preferences.DataStoreManager
import com.rogo.ch4.data.repository.LocalRepository
import com.rogo.ch4.databinding.FragmentAddDialogBinding
import com.rogo.ch4.utils.viewModelFactory

class AddDialogFragment : DialogFragment() {

    private var _binding: FragmentAddDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddDialogViewModel by viewModelFactory {
        AddDialogViewModel(
            LocalRepository(
                DataStoreManager(requireContext()),
                requireActivity().application
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.btnAdd.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val desc = binding.etDesc.text.toString().trim()
            viewModel.addNotes(NoteEntity(title = title, description = desc))
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}