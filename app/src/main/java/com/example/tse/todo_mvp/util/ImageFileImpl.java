package com.example.tse.todo_mvp.util;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by TSE on 7/6/2017.
 */

public class ImageFileImpl implements ImageFile {

    File mImageFile;

    @Override
    public void create(String name, String extension) throws IOException {

        // Con esta linea declaro una variable del tipo File, la cual mantendra el directorio de fotos del dispositivo.
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        mImageFile = File.createTempFile(name,extension, storageDir);

    }

    @Override
    public boolean exists() {
        return null != mImageFile && mImageFile.exists();
    }

    @Override
    public void delete() {
        mImageFile = null;
    }

    @Override
    public String getPath() {
        return Uri.fromFile(mImageFile).toString();
    }
}
