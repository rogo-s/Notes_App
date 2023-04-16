package com.rogo.ch4.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rogo.ch4.R
import com.rogo.ch4.data.local.database.entity.NoteEntity
import com.rogo.ch4.data.local.preferences.DataStoreManager
import com.rogo.ch4.data.repository.LocalRepository
import com.rogo.ch4.databinding.FragmentHomeBinding
import com.rogo.ch4.utils.viewModelFactory

class HomeFragment : Fragment(), HomeAdapter.Listener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModelFactory {
        HomeViewModel(
            LocalRepository(
                DataStoreManager(requireContext()),
                requireActivity().application
            )
        )
    }
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddNote.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addDialogFragment)
        }
        viewModel.getNotesAsc()
        viewModel.notes.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.apply {
                    rvNote.isVisible = true
                    empty.isVisible = false
                }
                homeAdapter.differ.submitList(it)
            } else {
                binding.apply {
                    rvNote.isVisible = false
                    empty.isVisible = true
                }
            }
        }
        homeAdapter = HomeAdapter(this)
        binding.rvNote.apply {
            adapter = homeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
        binding.btnFilter.setOnClickListener {

        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_item, menu);
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.ascending -> {
//
//
//                return true
//            }
//            R.id.descending -> {
//
//
//                return true
//            }
//            else -> false
//        }
//        return false
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun editListener(noteEntity: NoteEntity) {
        val directions = HomeFragmentDirections.actionHomeFragmentToEditDialogFragment()
        directions.id = noteEntity.id!!
        findNavController().navigate(directions)
    }

    override fun deleteListener(noteEntity: NoteEntity) {
        val directions = HomeFragmentDirections.actionHomeFragmentToDeleteDialogFragment(noteEntity)
        findNavController().navigate(directions)
    }

}