package com.example.tse.todo_mvp;

import com.example.tse.todo_mvp.model.Note;
import com.example.tse.todo_mvp.model.NotesRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TSE on 5/6/2017.
 */

public class NotesPresenter implements NotesContract.UserActionsListener {

    private final NotesContract.View mNotesView;
    private final NotesRepository mNotesRepository;

    public NotesPresenter(NotesRepository notesRepository, NotesContract.View notesView) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null");
        mNotesView = checkNotNull(notesView, "notesView cannot be null!!!!");
    }

    @Override
    public void loadNotes(boolean forceUpdate) {
        mNotesView.setProgressIndicator(true);
        if (forceUpdate) {
            mNotesRepository.refreshData();
        }

        mNotesRepository.getNotes(new NotesRepository.LoadNotesCallBack() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                mNotesView.setProgressIndicator(false);
                mNotesView.showNotes(notes);
            }
        });
    }

    @Override
    public void addNewNote() {
        mNotesView.showAddNote();
    }

    @Override
    public void openNoteDetails(Note requestedNote) {

    }
}
