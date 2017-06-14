package com.example.tse.todo_mvp.addnote;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.tse.todo_mvp.R;
import com.example.tse.todo_mvp.model.Injection;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkState;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment implements AddNoteContract.View {

    public static final int REQUEST_CODE_IMAGE_CAPTURE = 0x1001;

    private TextView mTitle;
    private TextView mDescripcion;
    private ImageView mImageThumbnail;

    private AddNoteContract.UserActionsListener mActionListener;


    public AddNoteFragment() {
        // Required empty public constructor
    }

    public static AddNoteFragment newInstance() {
        return new AddNoteFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mActionListener = new AddNotePresenter(Injection.provideNotesRepository(), this, Injection.provideImageFile());

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_notes);
        fab.setImageResource(R.drawable.ic_assessment_black_18dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("add", "Hecho");
                mActionListener.saveNote(mTitle.getText().toString(), mDescripcion.getText().toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_add_note, container, false);
        mTitle = (TextView) root.findViewById(R.id.add_note_title);
        mDescripcion = (TextView) root.findViewById(R.id.add_note_description);
        mImageThumbnail = (ImageView) root.findViewById(R.id.add_note_image_thumbnail);

        setHasOptionsMenu(true);
        setRetainInstance(true);
        return root;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.take_picture:
                try {
                    mActionListener.takePicture();
                }catch (IOException e){
                    if (getView() != null) {
                        Snackbar.make(getView(), getString(R.string.take_picture_error), Snackbar.LENGTH_LONG).show();
                    }
                }
                return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_addnote_options, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showEmptyNoteError() {
        Snackbar.make(mTitle, getString(R.string.empty_note_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNotesList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void openCamera(String saveTo) {
        // Abrir la camara para tomar una fotografia.
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Verificar si hay una camapara instalada para manejar en nuestro intent
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null){
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(saveTo));
            startActivityForResult(takePictureIntent, REQUEST_CODE_IMAGE_CAPTURE);
        }else {
            Snackbar.make(mTitle, getString(R.string.cannot_connect_to_camera_message), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showImagePreview(String imageUrl) {
        checkState(!TextUtils.isEmpty(imageUrl), "imageUrl cannot be null or empty!");
        mImageThumbnail.setVisibility(View.VISIBLE);

        //EspressoIdlingResource.increment();
        Glide.with(this)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(mImageThumbnail){
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                    }
                });

    }

    @Override
    public void showImageError() {
        Snackbar.make(mTitle, getString(R.string.cannot_connect_to_camera_message), Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // si una imagen es recivida, mostrar en la ImageView.
        if (REQUEST_CODE_IMAGE_CAPTURE == requestCode && Activity.RESULT_OK == resultCode){
            mActionListener.imageAvailable();
        }else {
            mActionListener.imageCaptureFailed();
        }
    }
}
