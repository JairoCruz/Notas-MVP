package com.example.tse.todo_mvp.addnote;

import java.io.IOException;

/**
 * Created by TSE on 7/6/2017.
 */

public interface AddNoteContract {

    interface View {
        void showEmptyNoteError();
        void showNotesList();
        void openCamera(String saveTo);
        void showImagePreview(String uri);
        void showImageError();
    }


    interface UserActionsListener {
        void saveNote(String title, String description);
        void takePicture() throws IOException;
        void imageAvailable();
        void imageCaptureFailed();
    }
}
