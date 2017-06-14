package com.example.tse.todo_mvp.model;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TSE on 13/6/2017.
 */

public class NoteRepositories {

    private NoteRepositories(){

    }

    private static NotesRepository repository = null;

    public synchronized static NotesRepository getInMemoryRepoInstance(NotesServiceApi notesServiceApi){
        checkNotNull(notesServiceApi);
        if (null == repository){
            repository = new InMemoryNotesRepository(notesServiceApi);
        }
        return repository;
    }
}
