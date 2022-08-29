package com.ddona.noteapp.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddona.noteapp.R
import com.ddona.noteapp.adapter.NotesAdapter
import com.ddona.noteapp.databinding.FragmentHomeBinding
import com.ddona.noteapp.model.Notes
import com.ddona.noteapp.vm.NotesViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        setHasOptionsMenu(true)

        val staggergGridLayoutManager =
            StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.rcvAllNotes.layoutManager = staggergGridLayoutManager

        viewModel.getNotes().observe(viewLifecycleOwner,{ noteslist ->
            oldMyNotes = noteslist as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(),noteslist)
            binding.rcvAllNotes.adapter = adapter
        })

        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner,{ noteslist ->
                oldMyNotes = noteslist as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),noteslist)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        binding.filterHight.setOnClickListener {
            viewModel.getHightNote().observe(viewLifecycleOwner,{ noteslist ->
                oldMyNotes = noteslist as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),noteslist)
                binding.rcvAllNotes.adapter =adapter
            })
        }

        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNote().observe(viewLifecycleOwner,{ noteslist ->
                oldMyNotes = noteslist as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),noteslist)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        binding.filterLow.setOnClickListener {
            viewModel.getLowNote().observe(viewLifecycleOwner,{ noteslist ->
                oldMyNotes = noteslist as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),noteslist)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }
        return binding.root
    }


}