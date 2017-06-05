package com.example.tse.todo_mvp.model;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by TSE on 5/6/2017.
 */

public final class Note {
    private final String mId;
    private final String mTitle;
    private final String mDescription;
    private final String mImageUrl;


    public Note(String title, String description) {
        this(title, description, null);
    }

    public Note(String mTitle, String mDescription, String mImageUrl) {
        mId = UUID.randomUUID().toString();
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImageUrl = mImageUrl;
    }

    public String getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }


    private static ArrayList<Note> notes;

    public static   ArrayList inicializarData(){
        notes = new ArrayList<>();
        notes.add(new Note("jairo","feo"));
        notes.add(new Note("jair","feo"));
        notes.add(new Note("jaio","feo"));
        notes.add(new Note("jahiro","feo"));
        notes.add(new Note("jaigro","feo"));
        notes.add(new Note("jairo","feo"));
        notes.add(new Note("jaigro","feo"));
        notes.add(new Note("jagiro","feo"));
        notes.add(new Note("jagiro","feo"));
        return notes;
    }
}
