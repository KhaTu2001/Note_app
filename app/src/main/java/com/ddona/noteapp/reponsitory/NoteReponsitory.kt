package com.ddona.noteapp.reponsitory

import androidx.lifecycle.LiveData
import com.ddona.noteapp.db.NoteDao
import com.ddona.noteapp.model.Notes

class NoteReponsitory(val dao: NoteDao) {
    fun getAllNote():LiveData<List<Notes>> = dao.getNotes()


    fun getHightNote():LiveData<List<Notes>> = dao.getHightNotes()


    fun getMediumNote():LiveData<List<Notes>> = dao.getMediumNotes()


    fun getLowNote():LiveData<List<Notes>> =dao.getLowNotes()


    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

}