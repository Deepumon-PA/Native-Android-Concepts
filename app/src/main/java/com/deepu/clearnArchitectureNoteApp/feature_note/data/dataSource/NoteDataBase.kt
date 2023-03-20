package com.deepu.clearnArchitectureNoteApp.feature_note.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deepu.clearnArchitectureNoteApp.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}