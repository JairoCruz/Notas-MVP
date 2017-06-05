package com.example.tse.todo_mvp;

import com.example.tse.todo_mvp.model.Note;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TSE on 5/6/2017.
 */

public class NotesPresenter implements NotesContract.UserActionsListener {

    private final NotesContract.View mNotesView;

    public NotesPresenter(NotesContract.View notesView) {
        mNotesView = checkNotNull(notesView, "notesView cannot be null!!!!");
    }

    @Override
    public void loadNotes(boolean forceUpdate) {

    }

    @Override
    public void addNewNote() {
        mNotesView.showAddNote();
    }

    @Override
    public void openNoteDetails(Note requestedNote) {

    }
}
