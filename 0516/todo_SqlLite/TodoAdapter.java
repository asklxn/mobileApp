package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {
    Context context;
    ArrayList<TodoItem> items;
    LayoutInflater inflater;

    public TodoAdapter(Context context, ArrayList<TodoItem> items) {
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder {
        TextView taskText, dateText;
        Button deleteButton;
    }

    public interface OnDeleteListener {
        void onDelete(int position);
    }

    private OnDeleteListener onDeleteListener;

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.onDeleteListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.todo_item, parent, false);
            holder = new ViewHolder();
            holder.taskText = convertView.findViewById(R.id.textTask);
            holder.dateText = convertView.findViewById(R.id.textDate);
            holder.deleteButton = convertView.findViewById(R.id.buttonDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TodoItem item = items.get(position);
        holder.taskText.setText(item.getTask());
        holder.dateText.setText(item.getDate());

        holder.deleteButton.setOnClickListener(v -> {
            if (onDeleteListener != null) {
                onDeleteListener.onDelete(position);
            }
        });

        return convertView;
    }

}
