package com.ddona.noteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ddona.noteapp.model.Notes

@Database(entities = [Notes::class], exportSchema = false, version = 1)
abstract class NotesDatabase:RoomDatabase() {

    abstract fun myNotesDao():NoteDao

    companion object{
        @Volatile
        var INSTANCE:NotesDatabase?=null

        fun getDatabaseInstance(context: Context):NotesDatabase {
            var tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstace = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,"Notes")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = roomDatabaseInstace
                return roomDatabaseInstace
            }
        }
    }
}