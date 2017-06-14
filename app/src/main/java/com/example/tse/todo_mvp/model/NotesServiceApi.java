package com.example.tse.todo_mvp.model;

import java.util.List;

/**
 * Created by TSE on 13/6/2017.
 */

public interface NotesServiceApi {


    interface NotesServiceCallback<T> {

        void onLoaded(T notes);

    }

    void getAllNotes(NotesServiceCallback<List<Note>> callback);

    void getNote(String noteId, NotesServiceCallback<Note> callback);

    void saveNote(Note note);
}
