package com.ddona.noteapp.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.ddona.noteapp.R
import com.ddona.noteapp.databinding.FragmentEditNoteBinding
import com.ddona.noteapp.model.Notes
import com.ddona.noteapp.vm.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNoteFragment : Fragment() {
//
//    val oldnotes by navArgs<EditNoteFragmentArgs>()
    private lateinit var binding: FragmentEditNoteBinding
    var priority = "1"
    val viewModel: NotesViewModel by viewModels()
    val oldnotes by navArgs<EditNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentEditNoteBinding.inflate(layoutInflater,container,false)

            setHasOptionsMenu(true)

        binding.edtTitle.setText(oldnotes.data.title)
        binding.edtSubtitle.setText(oldnotes.data.subTitle)
        binding.edtNotes.setText(oldnotes.data.notes)

        when(oldnotes.data.priority){
            "1" -> {
                priority = "1"
                binding.pedtGreen.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pedtRed.setImageResource(0)
                binding.pedtYellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pedtYellow.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pedtRed.setImageResource(0)
                binding.pedtGreen.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pedtRed.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pedtGreen.setImageResource(0)
                binding.pedtYellow.setImageResource(0)
            }

        }

        binding.pedtGreen.setOnClickListener {
            priority="1"
            binding.pedtGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pedtRed.setImageResource(0)
            binding.pedtYellow.setImageResource(0)
        }

        binding.pedtYellow.setOnClickListener {
            priority="2"
            binding.pedtYellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pedtRed.setImageResource(0)
           binding.pedtGreen.setImageResource(0)
        }

        binding.pedtRed.setOnClickListener {
            priority="3"
            binding.pedtRed.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pedtGreen.setImageResource(0)
            binding.pedtYellow.setImageResource(0)     }

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it:View?) {
       val title =  binding.edtTitle.text.toString()
        val subTitle =  binding.edtSubtitle.text.toString()
        val notes =  binding.edtNotes.text.toString()
        val d= Date()
        val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data= Notes(
             oldnotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = noteDate.toString(),
            priority = priority)

        viewModel.updateNote(data)

        Toast.makeText(requireContext()," Notes Update Successfully", Toast.LENGTH_SHORT).show()

       Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_notes,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete)
        {
            val bottomSheet:BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldnotes.data.id!!)
                Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
                bottomSheet.dismiss()
            }

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }


}