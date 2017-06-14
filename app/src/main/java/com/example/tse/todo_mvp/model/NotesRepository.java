package com.example.tse.todo_mvp.model;

import java.util.List;

/**
 * Created by TSE on 13/6/2017.
 */

public interface NotesRepository {

    interface LoadNotesCallBack {

        void onNotesLoaded(List<Note> notes);

    }

    interface GetNoteCallback {

        void onNoteLoaded(Note note);

    }

    void getNotes(LoadNotesCallBack callBack);

    void getNote(String noteId, GetNoteCallback callback);

    void saveNote(Note note);

    void refreshData();

}
