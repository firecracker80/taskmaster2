package com.dt_cs.taskmaster.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class TaskDatabaseConverters {
    @TypeConverter
    public static Date fromTimeStamp(Long value){
        return value == null ? null : date.getTime();
    }

    @TypeConverter
    public static Long dateToTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }
}
