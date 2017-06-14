package com.example.tse.todo_mvp.model;

import com.example.tse.todo_mvp.util.ImageFile;
import com.example.tse.todo_mvp.util.ImageFileImpl;

/**
 * Created by TSE on 13/6/2017.
 */

public class Injection {

    public static ImageFile provideImageFile(){
        return new ImageFileImpl();
    }

    public static NotesRepository provideNotesRepository(){
        return NoteRepositories.getInMemoryRepoInstance(new NotesServiceApiImpl());
    }
}
