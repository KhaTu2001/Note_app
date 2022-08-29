package com.ddona.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ddona.noteapp.R
import com.ddona.noteapp.databinding.ItemNotesBinding
import com.ddona.noteapp.model.Notes
import com.ddona.noteapp.ui.fragment.HomeFragmentDirections

class NotesAdapter(requireContext: Context,var noteslist: List<Notes>) :RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    class notesViewHolder(val binding:ItemNotesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
            return notesViewHolder(ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = noteslist[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubTitle.text = data.subTitle
        holder.binding.notesDate.text = data.date

        when(data.priority){
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }

        }
        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }



    override fun getItemCount(): Int = noteslist.size

}