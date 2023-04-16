package com.rogo.ch4.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rogo.ch4.R
import com.rogo.ch4.data.local.database.entity.NoteEntity
import com.rogo.ch4.data.local.preferences.DataStoreManager
import com.rogo.ch4.data.repository.LocalRepository
import com.rogo.ch4.databinding.FragmentEditDialogBinding
import com.rogo.ch4.utils.viewModelFactory

class EditDialogFragment : DialogFragment() {

    private var _binding: FragmentEditDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditDialogViewModel by viewModelFactory {
        EditDialogViewModel(
            LocalRepository(
                DataStoreManager(requireContext()),
                requireActivity().application
            )
        )
    }
    private val navArgs: EditDialogFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.btnEdit.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val desc = binding.etDesc.text.toString().trim()
            viewModel.edit(NoteEntity(navArgs.id, title, desc))
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}