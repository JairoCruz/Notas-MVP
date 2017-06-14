package com.example.tse.todo_mvp.model;


import android.support.v4.util.ArrayMap;

/**
 * Created by TSE on 13/6/2017.
 */

public final class NotesServiceApiEndpoint {


    static {
        DATA = new ArrayMap(2);
        addNote("Oh yes!", "I demand trial by Unit testing", null);
        addNote("Espresso", "UI Testing for android", null);
    }

    private final static ArrayMap<String, Note> DATA;

    private static void addNote(String title, String description, String imageUrl){
        Note newNote = new Note(title, description, imageUrl);
        DATA.put(newNote.getmId(), newNote);
    }


    public static ArrayMap<String, Note> loadPersistedNotes(){
        return DATA;
    }
}
