package com.example.tse.todo_mvp.model;

import android.support.annotation.VisibleForTesting;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TSE on 13/6/2017.
 */

// Implementacion concreta para cargar las notas de una fuente de datos.

public class InMemoryNotesRepository implements NotesRepository {

    private final NotesServiceApi mNotesServiceApi;

    @VisibleForTesting
    List<Note> mCachedNotes;

    public InMemoryNotesRepository(NotesServiceApi notesServiceApi) {
        mNotesServiceApi = notesServiceApi;
    }

    @Override
    public void getNotes(final LoadNotesCallBack callBack) {

        checkNotNull(callBack);

        // Load from API only if needed
        if (mCachedNotes == null){
            mNotesServiceApi.getAllNotes(new NotesServiceApi.NotesServiceCallback<List<Note>>() {
                @Override
                public void onLoaded(List<Note> notes) {
                    mCachedNotes = ImmutableList.copyOf(notes);
                    callBack.onNotesLoaded(mCachedNotes);
                }
            });
        }else {
            callBack.onNotesLoaded(mCachedNotes);
        }
    }

    @Override
    public void getNote(final String noteId, final GetNoteCallback callback) {

        checkNotNull(noteId);
        checkNotNull(callback);

        // Cargar notas emparejando el id siempre directamente de la API.
        mNotesServiceApi.getNote(noteId, new NotesServiceApi.NotesServiceCallback<Note>() {
            @Override
            public void onLoaded(Note notes) {
                callback.onNoteLoaded(notes);
            }
        });

    }

    @Override
    public void saveNote(Note note) {

        checkNotNull(note);
        mNotesServiceApi.saveNote(note);
        refreshData();

    }

    @Override
    public void refreshData() {
        mCachedNotes = null;
    }
}
