package com.example.tse.todo_mvp.addnote;

import com.example.tse.todo_mvp.model.Note;
import com.example.tse.todo_mvp.model.NotesRepository;
import com.example.tse.todo_mvp.util.ImageFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TSE on 7/6/2017.
 */

public class AddNotePresenter implements AddNoteContract.UserActionsListener {

    private final AddNoteContract.View mAddNoteView;
    private final NotesRepository mNotesRepository;
    private final ImageFile mImageFile;

    public AddNotePresenter(NotesRepository notesRepository, AddNoteContract.View addNoteView, ImageFile imageFile) {
        mNotesRepository = checkNotNull(notesRepository);
        mAddNoteView = checkNotNull(addNoteView);
        mImageFile = imageFile;
    }

    @Override
    public void saveNote(String title, String description) {
        String imageUrl = null;
        if (mImageFile.exists()){
            imageUrl = mImageFile.getPath();
        }

        Note newNote = new Note(title, description, imageUrl);
        if (newNote.isEmpty()){
            mAddNoteView.showEmptyNoteError();
        }else {
            mAddNoteView.showNotesList();
        }
    }

    @Override
    public void takePicture() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        mImageFile.create(imageFileName, ".jpg");
        mAddNoteView.openCamera(mImageFile.getPath());
    }

    @Override
    public void imageAvailable() {
        if (mImageFile.exists()){
            mAddNoteView.showImagePreview(mImageFile.getPath());

        }else {
            imageCaptureFailed();
        }
    }

    @Override
    public void imageCaptureFailed() {
        mImageFile.delete();
        mAddNoteView.showImageError();
    }
}
