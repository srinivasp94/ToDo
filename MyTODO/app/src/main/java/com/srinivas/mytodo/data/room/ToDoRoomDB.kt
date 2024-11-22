package com.srinivas.mytodo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.srinivas.mytodo.data.model.ToDo
import com.srinivas.mytodo.data.room.dao.TodoDao


@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoRoomDB :RoomDatabase() {
    abstract fun todoDao(): TodoDao
}