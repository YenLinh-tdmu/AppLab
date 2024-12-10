package com.example.applab;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Custom_Adapter_Task extends ArrayAdapter<Task> {

    private Activity context;
    private ArrayList<Task> tasks;

    public Custom_Adapter_Task(Activity context, ArrayList<Task> tasks) {
        super(context, R.layout.list_item_task, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    static class ViewHolder {
        TextView textViewTaskName;
        TextView textViewDate;
        TextView textViewTime;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_task, null, true);
            holder = new ViewHolder();
            holder.textViewTaskName = convertView.findViewById(R.id.textViewTaskName);
            holder.textViewDate = convertView.findViewById(R.id.textViewDate);
            holder.textViewTime = convertView.findViewById(R.id.textViewTime);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Task currentTask = tasks.get(position);

        holder.textViewTaskName.setText(currentTask.getTaskName());
        holder.textViewDate.setText(currentTask.getDate());
        holder.textViewTime.setText(currentTask.getTime());

        return convertView;
    }
}
