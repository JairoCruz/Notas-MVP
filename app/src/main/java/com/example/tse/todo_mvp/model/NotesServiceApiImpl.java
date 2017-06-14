package com.example.tse.todo_mvp.model;

import android.os.Handler;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by TSE on 13/6/2017.
 */

public class NotesServiceApiImpl implements NotesServiceApi {

    private static final int SERVICE_LATENCY_IN_MILLIS = 2000;
    private static final ArrayMap<String, Note> NOTES_SERVICE_DATA = NotesServiceApiEndpoint.loadPersistedNotes();


    @Override
    public void getAllNotes(final NotesServiceCallback<List<Note>> callback) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Note> notes = new ArrayList<>(NOTES_SERVICE_DATA.values());
                callback.onLoaded(notes);
            }
        }, SERVICE_LATENCY_IN_MILLIS);

    }

    @Override
    public void getNote(final String noteId, final NotesServiceCallback<Note> callback) {
        Note note = NOTES_SERVICE_DATA.get(noteId);
        callback.onLoaded(note);
    }

    @Override
    public void saveNote(Note note) {
        NOTES_SERVICE_DATA.put(note.getmId(), note);
    }
}
