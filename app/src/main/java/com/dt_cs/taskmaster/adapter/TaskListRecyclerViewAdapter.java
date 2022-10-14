package com.dt_cs.taskmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;
import com.dt_cs.taskmaster.activities.MainActivity;
import com.dt_cs.taskmaster.R;
import com.dt_cs.taskmaster.activities.TaskDetail;

import java.util.List;

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
        taskFragmentNameTextView.setText((position + 1) + ". " + taskName);

        TextView taskFragmentTextViewBody = holder.itemView.findViewById(R.id.TaskFragmentBody);
        String taskBody = tasks.get(position).getBody();
        taskFragmentTextViewBody.setText(taskBody);

        TextView taskFragmentTextViewState = holder.itemView.findViewById(R.id.TaskFragmentStatus);
//        String taskStatus = tasks.get(position).getStatus().toString();
        taskFragmentTextViewState.setText("taskStatus");

        View taskViewHolder = holder.itemView;
        taskViewHolder.setOnClickListener(view -> {
            Intent goToTaskDetailPage = new Intent(callingActivity, TaskDetail.class);
            goToTaskDetailPage.putExtra(MainActivity.TASK_NAME_EXTRA_TAG, taskName);
            goToTaskDetailPage.putExtra(MainActivity.TASK_BODY_EXTRA_TAG, taskBody);
            goToTaskDetailPage.putExtra(MainActivity.TASK_STATUS_EXTRA_TAG, "taskStatus");
            callingActivity.startActivity(goToTaskDetailPage);
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
