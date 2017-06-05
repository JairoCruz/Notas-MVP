package com.example.tse.todo_mvp;

import com.example.tse.todo_mvp.model.Note;

import java.util.List;

/**
 * Created by TSE on 5/6/2017.
 */

// Esta interfaz especifica el contrato entre la vista y el presentador

public interface NotesContract {

    interface View {
        void setProgressIndicator(boolean active);
        void showNotes(List<Note> notes);
        void showAddNote();
        void showNoteDetailUI(String noteId);
    }


    interface UserActionsListener {
        void loadNotes(boolean forceUpdate);
        void addNewNote();
        void openNoteDetails(Note requestedNote);
    }


}
