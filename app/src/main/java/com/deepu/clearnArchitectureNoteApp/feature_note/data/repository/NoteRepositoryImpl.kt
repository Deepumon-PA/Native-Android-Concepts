package com.deepu.clearnArchitectureNoteApp.feature_note.data.repository

import com.deepu.clearnArchitectureNoteApp.feature_note.data.dataSource.NoteDao
import com.deepu.clearnArchitectureNoteApp.feature_note.domain.model.Note
import com.deepu.clearnArchitectureNoteApp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}