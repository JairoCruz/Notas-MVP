package com.example.tse.todo_mvp.notes;

import com.example.tse.todo_mvp.NotesContract;
import com.example.tse.todo_mvp.NotesPresenter;
import com.example.tse.todo_mvp.model.Note;
import com.example.tse.todo_mvp.model.NotesRepository;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by TSE on 14/6/2017.
 */

public class NotesPresenterTest {

    private static List<Note> NOTES = Lists.newArrayList(new Note("Title1","Description1"),
            new Note("Title2","Description"));

    private static List<Note> EMPTY_NOTES = new ArrayList<>(0);

    @Mock
    private NotesRepository mNotesRepository;

    @Mock
    private NotesContract.View mNotesView;

    @Captor
    private ArgumentCaptor<NotesRepository.LoadNotesCallBack> mLoadNotesCallbackCaptor;

    private NotesPresenter mNotesPresenter;

    @Before
    public void setupNotesPresenter(){
        MockitoAnnotations.initMocks(this);

        mNotesPresenter = new NotesPresenter(mNotesRepository, mNotesView);
    }

    @Test
    public void loadNotesFromRepositoryAndLoadIntoView(){
        mNotesPresenter.loadNotes(true);

        verify(mNotesRepository).getNotes(mLoadNotesCallbackCaptor.capture());
        mLoadNotesCallbackCaptor.getValue().onNotesLoaded(NOTES);

        verify(mNotesView).setProgressIndicator(false);
        verify(mNotesView).showNotes(NOTES);
    }

    @Test
    public void clickOnFab_ShowsAddsNoteUI(){
        //fail("Implement in step 6");

        // Cuando agregamos una nueva nota
        mNotesPresenter.addNewNote();

        // Luego la UI add note es mostrada
        verify(mNotesView).showAddNote();
    }

   /* @Test
    public void clickOnNote_ShowsDetailUi(){
        Note requestedNote = new Note("Details Requested", "For this note");

        mNotesPresenter.openNoteDetails(requestedNote);

        verify(mNotesView).showNoteDetailUI(any(String.class));
    }
*/
}
