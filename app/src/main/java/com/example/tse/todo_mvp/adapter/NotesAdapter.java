package com.example.tse.todo_mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tse.todo_mvp.R;
import com.example.tse.todo_mvp.model.Note;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TSE on 5/6/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNotes;
    private NoteItemListener mItemListener;
    Context context;

    public NotesAdapter(List<Note> notes, NoteItemListener itemListener) {

        //this.mNotes = notes;
        setList(notes);
        mItemListener = itemListener;
    }


    private void setList(List<Note> notes){
        mNotes = checkNotNull(notes);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.item_note, parent, false);

        return new ViewHolder(noteView, mItemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Note note = mNotes.get(position);

        viewHolder.title.setText(note.getmTitle());
        viewHolder.descripcion.setText(note.getmDescription());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public Note getItem(int position){
        return mNotes.get(position);
    }

    public void replaceData(List<Note> notes){
        setList(notes);
        notifyDataSetChanged();
    }






    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public TextView descripcion;

        public ViewHolder(View itemView, NoteItemListener listener) {
            super(itemView);

            mItemListener = listener;

            title = (TextView) itemView.findViewById(R.id.note_detail_title);
            descripcion = (TextView) itemView.findViewById(R.id.note_detail_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Note note = getItem(position);
            mItemListener.onNoteClick(note);
        }
    }


    public interface NoteItemListener{
        void onNoteClick(Note clickedNote);
    }
}
