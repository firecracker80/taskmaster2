package com.dt_cs.taskmaster.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dt_cs.taskmaster.models.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    public void insertTask(Task task);

    @Query("SELECT * FROM Task")
    public List<Task> findAll();

    @Query("SELECT * FROM Task WHERE status = :status")
    public List<Task> findAllByType(Task.Status status);

    @Query("SELECT * FROM Task ORDER BY title ASC")
    public List<Task> findAllSortedByTitle();

    @Query("SELECT * FROM Task WHERE id = :id")
    Task findByAnId(long id);
}
