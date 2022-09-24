package com.dt_cs.taskmaster.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.dt_cs.taskmaster.dao.TaskDao;
import com.dt_cs.taskmaster.models.Task;

@TypeConverters({TaskDatabaseConverters.class})
@Database(entities == {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
