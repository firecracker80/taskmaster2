package com.dt_cs.taskmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dt_cs.taskmaster.MainActivity;
import com.dt_cs.taskmaster.R;
import com.dt_cs.taskmaster.TaskDetail;

import java.util.List;

import com.dt_cs.taskmaster.models.Task;

public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter<TaskListRecyclerViewAdapter.TaskListViewHolder> {
    List<Task> tasks;
    Context callingActivity;

    public TaskListRecyclerViewAdapter(List<Task> tasks, Context callingActivity){
        this.tasks = tasks;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View taskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent, false);
        return new TaskListViewHolder(taskFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position){
        TextView taskFragmentNameTextView = holder.itemView.findViewById(R.id.TaskFragmentName);
        String taskName = tasks.get(position).getTitle();
        taskFragmentNameTextView.setText(position + ". " + taskName);
        TextView taskFragTextViewType = holder.itemView.findViewById(R.id.TaskFragmentType);
        String taskType = tasks.get(position).getBody();
        taskFragTextViewType.setText(taskType);

        View taskViewHolder = holder.itemView;
        taskViewHolder.setOnClickListener(view -> {
            Intent goToTaskDetails = new Intent(callingActivity, TaskDetail.class);
            goToTaskDetails.putExtra((MainActivity.TASK_NAME_EXTRA_TAG), taskName);
            callingActivity.startActivity(goToTaskDetails);
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskListViewHolder extends RecyclerView.ViewHolder{
        public TaskListViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
