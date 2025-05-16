package com.example.memo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.NoteViewHolder> {

    private ArrayList<Memo> memoList;
    private OnMemoDeleteListener deleteListener;

    public interface OnMemoDeleteListener {
        void onMemoDelete(int position);
    }

    public MemoAdapter(ArrayList<Memo> memoList, OnMemoDeleteListener listener) {
        this.memoList = memoList;
        this.deleteListener = listener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Memo memo = memoList.get(position);
        holder.textTitle.setText(memo.getText());
        holder.textDate.setText(memo.getDate());

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteListener != null) {
                    deleteListener.onMemoDelete(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDate;
        ImageButton buttonDelete;

        public NoteViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDate = itemView.findViewById(R.id.textDate);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
