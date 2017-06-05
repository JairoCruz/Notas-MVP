package com.example.tse.todo_mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tse.todo_mvp.adapter.NotesAdapter;
import com.example.tse.todo_mvp.model.Note;

/**
 * Created by TSE on 2/6/2017.
 */

public class NotesFragment extends Fragment {

    NotesAdapter mListAdapter;
    private String NAME_CLASSE = NotesFragment.class.getSimpleName();



    // Constructor vacio
    public NotesFragment() {
        // Requiere de un constructor publico vacio.
    }

    public static NotesFragment newInstance(){
        return new NotesFragment();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new NotesAdapter(Note.inicializarData(), mItemListener);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.notes_list);
        recyclerView.setAdapter(mListAdapter);

        int numColumns = getContext().getResources().getInteger(R.integer.num_notes_columns);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        // Configuracion del Floating action button
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_notes);
        fab.setImageResource(R.drawable.ic_add_white_18dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(NAME_CLASSE, "PRESIONAR FLOTATIN ACTION BUTTON");
            }
        });


        // Configuracion del Pull-to-Refresh
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        // Configuro los colores que mostrara el swipeRefresh
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Ahora agrego un listener en el momento que haga swipe
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(NAME_CLASSE, "Estoy refrescando soy swipe");
            }
        });

        return root;
    }






    NotesAdapter.NoteItemListener mItemListener = new NotesAdapter.NoteItemListener() {
        @Override
        public void onNoteClick(Note clickedNote) {
           //Log.i("informacion","Estamos " + clickedNote.getmTitle());
            // Desde la implementacion de este metodo abrire el detalle del elemento seleccionado en el recycler view
        }
    };
}
