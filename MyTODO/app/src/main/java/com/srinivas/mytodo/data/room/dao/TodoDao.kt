package com.srinivas.mytodo.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srinivas.mytodo.data.model.ToDo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: ToDo)

    @Query("SELECT * FROM todo_table")
    suspend fun getAllTodos(): List<ToDo>

    @Delete
    suspend fun deleteTodo(todo: ToDo)
}