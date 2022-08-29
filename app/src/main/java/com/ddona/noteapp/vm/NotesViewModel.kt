package com.ddona.noteapp.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ddona.noteapp.db.NotesDatabase
import com.ddona.noteapp.model.Notes
import com.ddona.noteapp.reponsitory.NoteReponsitory

class NotesViewModel(application: Application):AndroidViewModel(application) {
    val reponsitory: NoteReponsitory

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        reponsitory = NoteReponsitory(dao)
    }
    fun addNotes(notes: Notes){
        reponsitory.insertNotes(notes)
    }

    fun getNotes():LiveData<List<Notes>> = reponsitory.getAllNote()

    fun getHightNote():LiveData<List<Notes>> = reponsitory.getHightNote()


    fun getMediumNote():LiveData<List<Notes>> = reponsitory.getMediumNote()


    fun getLowNote():LiveData<List<Notes>> =reponsitory.getLowNote()

    fun deleteNotes(id: Int){
        reponsitory.deleteNotes(id)
    }

    fun updateNote(notes: Notes){
        reponsitory.updateNotes(notes)
    }

}