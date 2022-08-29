package com.ddona.noteapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ddona.noteapp.model.Notes

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ")
    fun getNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority = 3")
    fun getHightNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority = 2")
    fun getMediumNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority = 1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
}