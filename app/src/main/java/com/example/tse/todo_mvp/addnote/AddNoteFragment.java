package com.example.tse.todo_mvp.addnote;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tse.todo_mvp.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment {

    private TextView mTitle;
    private TextView mDescripcion;
    private ImageView mImageThumbnail;


    public AddNoteFragment() {
        // Required empty public constructor
    }

    public static AddNoteFragment newInstance() {
        return new AddNoteFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_notes);
        fab.setImageResource(R.drawable.ic_assessment_black_18dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("add", "Hecho");
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
               /* try {

                }catch (IOException e){
                    if (getView() != null) {
                        Snackbar.make(getView(), getString(R.string.take_picture_error), Snackbar.LENGTH_LONG).show();
                    }
                }*/
                return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_addnote_options, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
